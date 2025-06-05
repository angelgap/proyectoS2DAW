import { useEffect, useState } from "react";
import axios from "axios";
import { useUsuario } from "../context/UsuarioContext";
import { Diario } from "../types/Diario";
import EditorDiario from "../components/EditorDiario";

export default function Home() {
  const { usuario } = useUsuario();
  const [diarios, setDiarios] = useState<Diario[]>([]);
  const [cargando, setCargando] = useState(true);
  const [diarioEnEdicion, setDiarioEnEdicion] = useState<Diario | undefined>();

  useEffect(() => {
    cargarDiarios();
  }, [usuario]);

  const cargarDiarios = async () => {
    if (!usuario) return;
    setCargando(true);
    try {
      const res = await axios.get(`http://localhost:8080/usuarios/id/${usuario.id}`);
      setDiarios(res.data.diarios || []);
    } catch (error) {
      console.error("Error al cargar diarios:", error);
    } finally {
      setCargando(false);
    }
  };

  const handleEliminarDiario = async (id: number) => {
    try {
      await axios.delete(`http://localhost:8080/diarios/${id}`);
      setDiarios(diarios.filter((d) => d.id !== id));
    } catch (error) {
      console.error("Error al eliminar diario:", error);
    }
  };

  return (
    <div className="bg-gray-100 min-h-screen p-4 md:p-8">
      <h1 className="text-center text-2xl font-bold text-gray-800 mb-6">
        Bienvenido, {usuario?.nombre}
      </h1>

      {/* Editor siempre visible */}
      <div className="mb-8">
        <EditorDiario
          diario={diarioEnEdicion}
          onSave={() => {
            setDiarioEnEdicion(undefined);
            cargarDiarios();
          }}
        />
      </div>

      {/* Lista de diarios */}
      {cargando ? (
        <p className="text-center text-gray-600">Cargando diarios...</p>
      ) : diarios.length === 0 ? (
        <p className="text-center text-gray-600">No hay diarios aún. ¡Crea uno!</p>
      ) : (
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
          {diarios.map((diario) => (
            <div
              key={diario.id}
              className="bg-white rounded-xl shadow p-4 flex flex-col justify-between"
            >
              <div>
                <h2 className="text-lg font-semibold text-gray-800 mb-1">
                  {diario.titulo}
                </h2>
                <div
                  className="prose prose-sm max-w-none text-sm text-gray-700"
                  dangerouslySetInnerHTML={{ __html: diario.text }}
                />
                {diario.imagenes && diario.imagenes.length > 0 && (
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
                {diario.comentarios && diario.comentarios.length > 0 && (
                  <div className="mt-4 text-sm text-gray-700">
                    <p className="font-semibold mb-1">Comentarios:</p>
                    {diario.comentarios.map((comentario) => (
                      <div key={comentario.id} className="border-t pt-2 mt-2">
                        <p className="text-gray-800">{comentario.texto}</p>
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

              <div className="mt-4 flex justify-end gap-2">
                {new Date(diario.fecha).toISOString().split("T")[0] ===
                  new Date().toISOString().split("T")[0] && (
                  <button
                    onClick={() => setDiarioEnEdicion(diario)}
                    className="bg-yellow-400 hover:bg-yellow-500 text-black font-semibold px-4 py-2 rounded"
                  >
                    Editar
                  </button>
                )}
                <button
                  onClick={() => handleEliminarDiario(diario.id)}
                  className="bg-red-500 hover:bg-red-600 text-white font-semibold px-4 py-2 rounded"
                >
                  Eliminar
                </button>
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
}
