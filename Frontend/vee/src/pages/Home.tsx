import { useEffect, useState } from "react";
import axios from "axios";
import { useUsuario } from "../context/UsuarioContext";
import { Diario } from "../types/Diario";
import EditorDiario from "../components/EditorDiario";
import DiarioCard from "../components/DiarioCard";
import { Link, useNavigate } from "react-router-dom";

export default function Home() {
  const { usuario, cerrarSesion } = useUsuario();
  const navigate = useNavigate();
  const [diarios, setDiarios] = useState<Diario[]>([]);
  const [cargando, setCargando] = useState(true);
  const [diarioEnEdicion, setDiarioEnEdicion] = useState<Diario | undefined>();
  const [mostrarModal, setMostrarModal] = useState(false);

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

  const handleLogout = () => {
    cerrarSesion();
    navigate("/");
  };

  const handleCrear = () => {
    setDiarioEnEdicion(undefined);
    setMostrarModal(true);
  };

  const handleEditar = (diario: Diario) => {
    setDiarioEnEdicion(diario);
    setMostrarModal(true);
  };

  const handleGuardar = () => {
    setMostrarModal(false);
    setDiarioEnEdicion(undefined);
    cargarDiarios();
  };

  return (
    <div className="bg-gray-100 min-h-screen">
      {/* NAVBAR */}
      <nav className="bg-white shadow p-4 flex justify-between items-center">
        <span className="text-lg font-bold text-gray-800">VEE</span>
        <div className="flex items-center gap-4">
          <Link to="/search" title="Buscar">🔍</Link>
          <button
            onClick={handleLogout}
            className="bg-red-500 text-white px-3 py-1 rounded hover:bg-red-600"
          >
            Cerrar sesión
          </button>
        </div>
      </nav>

      <div className="p-4 md:p-8">
        <h1 className="text-center text-2xl font-bold text-gray-800 mb-6">
          Bienvenido, {usuario?.nombre}
        </h1>

        {/* EDITOR VISIBLE EN ESCRITORIO */}
        <div className="hidden md:block mb-8">
          <EditorDiario
            diario={diarioEnEdicion}
            onSave={handleGuardar}
          />
        </div>

        {/* LISTA DE DIARIOS */}
        {cargando ? (
          <p className="text-center text-gray-600">Cargando diarios...</p>
        ) : diarios.length === 0 ? (
          <p className="text-center text-gray-600">No hay diarios aún. ¡Crea uno!</p>
        ) : (
          <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
            {diarios.map((diario) => (
              <DiarioCard
                key={diario.id}
                diario={diario}
                onEdit={() => handleEditar(diario)}
                onDelete={() => handleEliminarDiario(diario.id)}
              />
            ))}
          </div>
        )}
      </div>

      {/* BOTÓN FLOTANTE SOLO EN MÓVIL */}
      <button
        onClick={handleCrear}
        className="md:hidden fixed bottom-6 right-6 bg-yellow-400 hover:bg-yellow-500 text-black text-3xl rounded-full w-14 h-14 flex items-center justify-center shadow-lg"
        title="Crear nuevo diario"
      >+
      </button>

      {/* MODAL PARA CREAR O EDITAR EN MÓVIL */}
      {mostrarModal && (
        <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50 md:hidden">
          <div className="bg-white w-[95%] max-h-[90%] overflow-y-auto rounded-xl p-4">
            <EditorDiario
              diario={diarioEnEdicion}
              onSave={handleGuardar}
            />
            <button
              onClick={() => setMostrarModal(false)}
              className="mt-4 w-full bg-gray-300 text-gray-800 font-semibold py-2 rounded hover:bg-gray-400"
            >
              Cancelar
            </button>
          </div>
        </div>
      )}
    </div>
  );
}
