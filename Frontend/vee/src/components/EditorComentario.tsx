import React, { useRef, useState } from "react";
import ReactQuill from "react-quill";
import "react-quill/dist/quill.snow.css";
import axios from "axios";
import { useUsuario } from "../context/UsuarioContext";

interface Props {
  diarioId: number;
  onSave: () => void;
  onClose?: () => void;
}

export default function EditorComentarioQuillSimple({ diarioId, onSave, onClose }: Props) {
  const { usuario } = useUsuario();
  const [contenido, setContenido] = useState("");
  const quillRef = useRef<ReactQuill | null>(null);

  const handleGuardar = async () => {
    if (!usuario || !contenido.trim()) return;

    const datos = {
      texto: contenido,
      usuarioId: usuario.id,
      diarioId: diarioId,
    };

    try {
      await axios.post("http://localhost:8080/comentarios", datos);
      setContenido("");
      onSave();
    } catch (error) {
      console.error("Error al guardar comentario:", error);
    }
  };

  const modules = {
    toolbar: [
      [{ header: [1, 2, false] }],
      ["bold", "italic", "underline"],
      [{ list: "ordered" }, { list: "bullet" }],
      ["link", "image"],
    ],
  };

  return (
    <div className="bg-white shadow rounded-xl p-4 w-full">
      <ReactQuill
        ref={quillRef}
        value={contenido}
        onChange={setContenido}
        modules={modules}
        theme="snow"
        className="mb-4"
      />
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
          onClick={handleGuardar}
          className="px-4 py-2 bg-green-500 text-white rounded hover:bg-green-600"
        >
          Comentar
        </button>
      </div>
    </div>
  );
}
