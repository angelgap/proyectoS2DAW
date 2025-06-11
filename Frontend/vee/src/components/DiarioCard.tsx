// src/components/DiarioCard.tsx
import { useState } from "react";
import { Diario } from "../types/Diario";
import EditorComentario from "./EditorComentario";

interface Props {
  diario: Diario;
  onEdit?: () => void;
  onDelete?: () => void;
  showCommentButton?: boolean;
}

export default function DiarioCard({ diario, onEdit, onDelete, showCommentButton }: Props) {
  const [mostrarModalComentario, setMostrarModalComentario] = useState(false);

  const esDeHoy =
    new Date(diario.fecha).toISOString().split("T")[0] ===
    new Date().toISOString().split("T")[0];

  const handleComentarioGuardado = () => {
    setMostrarModalComentario(false);
    // Aquí podrías recargar los diarios o comentarios si lo deseas
  };

  return (
    <div className="bg-white rounded-xl shadow p-4 flex flex-col justify-between">
      <div>
        <h2 className="text-lg font-semibold text-gray-800 mb-1">
          {diario.titulo}
        </h2>

        <div
          className="prose prose-sm max-w-none text-sm text-gray-700"
          dangerouslySetInnerHTML={{ __html: diario.text }}
        />

        {diario.imagenes?.length > 0 && (
          <div className="mt-3">
            {diario.imagenes.map((img) => (
              <img
                key={img.id}
                src={img.url}
                alt=""
                className="w-full max-h-64 object-cover rounded mt-2"
              />
            ))}
          </div>
        )}

        {diario.comentarios?.length > 0 && (
  <div className="mt-4 text-sm text-gray-700">
    <p className="font-semibold mb-1">Comentarios:</p>
    {diario.comentarios.map((comentario) => (
      <div key={comentario.id} className="border-t pt-2 mt-2">
        {comentario.usuarioNombre && (
          <p className="text-xs text-gray-500 mb-1">
            <span className="font-medium text-gray-700">Usuario:</span>{" "}
            {comentario.usuarioNombre}
          </p>
        )}

        <div
          className="prose prose-sm max-w-none text-gray-800"
          dangerouslySetInnerHTML={{ __html: comentario.texto }}
        />

        {comentario.imagen && (
          <img
            src={comentario.imagen.url}
            alt=""
            className="w-full max-h-40 object-cover rounded mt-2"
          />
        )}
      </div>
    ))}
  </div>
)}

      </div>

      <div className="mt-4 flex justify-end gap-2 flex-wrap">
        {esDeHoy && onEdit && (
          <button
            onClick={onEdit}
            className="bg-yellow-400 hover:bg-yellow-500 text-black font-semibold px-4 py-2 rounded"
          >
            Editar
          </button>
        )}
        {onDelete && (
          <button
            onClick={onDelete}
            className="bg-red-500 hover:bg-red-600 text-white font-semibold px-4 py-2 rounded"
          >
            Eliminar
          </button>
        )}
        {showCommentButton && (
          <button
            onClick={() => setMostrarModalComentario(true)}
            className="bg-yellow-400 hover:bg-yellow-500 text-black font-semibold px-4 py-2 rounded"
          >
            Comentar
          </button>
        )}
      </div>

      {mostrarModalComentario && (
        <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50 ">
          <div className="bg-white w-[95%] max-h-[90%] overflow-y-auto rounded-xl p-4">
            <EditorComentario
              diarioId={diario.id}
              onSave={handleComentarioGuardado}
              onClose={() => setMostrarModalComentario(false)}
            />
          </div>
        </div>
      )}
    </div>
  );
}
