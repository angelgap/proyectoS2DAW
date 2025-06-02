// EditorDiario.tsx
import React, { useEffect, useRef, useState } from "react";
import ReactQuill from "react-quill";
import "react-quill/dist/quill.snow.css";
import { Diario } from "../types/Diario";
import axios from "axios";
import { useUsuario } from "../context/UsuarioContext";

interface Props {
  diario?: Diario;
  onClose?: () => void;
  onSave: () => void;
}

export default function EditorDiario({ diario, onClose, onSave }: Props) {
  const { usuario } = useUsuario();
  const [titulo, setTitulo] = useState("");
  const [texto, setTexto] = useState("");
  const quillRef = useRef<ReactQuill | null>(null);

  useEffect(() => {
    setTitulo(diario?.titulo || "");
    setTexto(diario?.text || "");
  }, [diario?.id]);

  const handleSubmit = async () => {
    if (!usuario) return;
    const nuevoDiario = {
      id: diario?.id,
      titulo,
      text: texto,
      fecha: new Date().toISOString().split("T")[0],
      usuarioId: usuario.id,
    };
    try {
      if (diario) {
        await axios.put(`http://localhost:8080/diarios/${diario.id}`, nuevoDiario);
      } else {
        await axios.post("http://localhost:8080/diarios", nuevoDiario);
      }
      onSave();
      setTitulo("");
      setTexto("");
    } catch (error) {
      console.error("Error al guardar diario:", error);
    }
  };

  const handleImageUpload = async () => {
    const input = document.createElement("input");
    input.setAttribute("type", "file");
    input.setAttribute("accept", "image/*");
    input.click();

    input.onchange = async () => {
      const file = input.files?.[0];
      if (!file || !usuario) return;

      const formData = new FormData();
      formData.append("file", file);
      formData.append("usuarioId", usuario.id.toString());
      if (diario?.id) formData.append("diarioId", diario.id.toString());

      try {
        const res = await axios.post("http://localhost:8080/imagenes/upload", formData, {
          headers: { "Content-Type": "multipart/form-data" },
        });
        const imageUrl = res.data.url;
        const editor = quillRef.current?.getEditor();
        const range = editor?.getSelection();
        if (range) {
          editor?.insertEmbed(range.index, "image", imageUrl);
        }
      } catch (error) {
        console.error("Error al subir imagen:", error);
      }
    };
  };

  const modules = {
    toolbar: {
      container: [
        [{ header: [1, 2, false] }],
        ["bold", "italic", "underline"],
        ["link", "image"],
        [{ list: "ordered" }, { list: "bullet" }],
      ],
      handlers: {
        image: handleImageUpload,
      },
    },
  };

  return (
    <div className="bg-white rounded-xl shadow p-4 w-full">
      <input
        type="text"
        value={titulo}
        onChange={(e) => setTitulo(e.target.value)}
        placeholder="Título"
        className="w-full border rounded px-3 py-2 mb-4"
      />
      <div className="quill-container">
        <ReactQuill
          key={diario?.id || "nuevo"}
          ref={(el) => {
            quillRef.current = el;
          }}
          value={texto}
          onChange={setTexto}
          modules={modules}
          theme="snow"
          className="mb-4"
        />
      </div>
      <div className="flex justify-end gap-2">
        {onClose && (
          <button
            onClick={onClose}
            className="px-4 py-2 bg-gray-300 hover:bg-gray-400 rounded"
          >
            Cancelar
          </button>
        )}
        <button
          onClick={handleSubmit}
          className="px-4 py-2 bg-blue-500 text-white font-semibold rounded hover:bg-blue-600"
        >
          {diario ? "Guardar Cambios" : "Crear Diario"}
        </button>
      </div>
    </div>
  );
}
