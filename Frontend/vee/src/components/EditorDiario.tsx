// EditorQuillSimple.tsx
import React, { useEffect, useRef, useState } from "react";
import ReactQuill from "react-quill";
import "react-quill/dist/quill.snow.css";
import axios from "axios";
import { useUsuario } from "../context/UsuarioContext";
import { Diario } from "../types/Diario";

interface Props {
  diario?: Diario;
  onSave: () => void;
  onClose?: () => void;
}

export default function EditorDiario({ diario, onSave, onClose }: Props) {
  const { usuario } = useUsuario();
  const [titulo, setTitulo] = useState("");
  const [contenido, setContenido] = useState("");
  const [fecha, setFecha] = useState<string | null>(null);
  const [imagenes, setImagenes] = useState<string[]>([]); // <-- guardar las imágenes del diario
  const quillRef = useRef<ReactQuill | null>(null);

  useEffect(() => {
    setTitulo(diario?.titulo || "");
    setContenido(diario?.text || "");
    setFecha(diario?.fecha || "");
    //setImagenes(diario?.imagenes.map(img => img.url) || []); // <-- cargar las imágenes del diario
    // <-- guardar la fecha del diario

  }, [diario?.id]);

  const handleGuardar = async () => {
    if (!usuario) return;
    const datos = {
      id: diario?.id,
      titulo,
      text: contenido,
      fecha: fecha || new Date().toISOString().split("T")[0],
      //imagenes, // <-- enviar las imágenes del diario
      usuarioId: usuario.id,
    };
    try {
      if (diario) {
        await axios.put(`http://localhost:8080/diarios/${diario.id}`, datos);
      } else {
        await axios.post("http://localhost:8080/diarios", datos);
      }
      setTitulo("");
      setContenido("");
      setImagenes([]); // <-- limpiar las imágenes al guardar
      onSave();
    } catch (error) {
      console.error("Error guardando diario:", error);
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
      <input
        type="text"
        value={titulo}
        onChange={(e) => setTitulo(e.target.value)}
        placeholder="Título"
        className="w-full mb-4 border px-3 py-2 rounded"
      />
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
          className="px-4 py-2 bg-blue-500 text-white rounded hover:bg-blue-600"
        >
          {diario ? "Guardar cambios" : "Crear diario"}
        </button>
      </div>
    </div>
  );
}
