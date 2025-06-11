import { useEffect, useState } from "react";
import axios from "axios";
import { Usuario } from "../types/Usuario";
import DiarioCard from "../components/DiarioCard";
import { Link, useNavigate } from "react-router-dom";
import { useUsuario } from "../context/UsuarioContext";

export default function Search() {
  const [usuarios, setUsuarios] = useState<Usuario[]>([]);
  const [query, setQuery] = useState("");
  const { cerrarSesion } = useUsuario();
  const navigate = useNavigate();

  const cargarUsuarios = async () => {
    try {
      const res = await axios.get("http://localhost:8080/usuarios");
      setUsuarios(res.data);
    } catch (error) {
      console.error("Error al cargar usuarios:", error);
    }
  };

  const buscarPorNombre = async (e: React.FormEvent) => {
    e.preventDefault();
    if (!query) return cargarUsuarios();
    try {
      const res = await axios.get(`http://localhost:8080/usuarios/nombre/${query}`);
      setUsuarios(res.data);
    } catch (error) {
      console.error("Error al buscar usuarios por nombre:", error);
    }
  };

  const handleLogout = () => {
    cerrarSesion();
    navigate("/");
  };

  useEffect(() => {
    cargarUsuarios();
  }, []);

  return (
    <div className="bg-gray-100 min-h-screen">
      {/* NAVBAR */}
      <nav className="bg-white shadow p-4 flex justify-between items-center">
        <span className="text-lg font-bold text-gray-800">VEE</span>
        <div className="flex items-center gap-4">
          <Link to="/home" title="Ir a inicio">🏠</Link>
          <button
            onClick={handleLogout}
            className="bg-red-500 text-white px-3 py-1 rounded hover:bg-red-600"
          >
            Cerrar sesión
          </button>
        </div>
      </nav>

      {/* CONTENIDO */}
      <div className="p-6">
        <h2 className="text-2xl font-bold mb-4">Buscar usuarios</h2>

        <form onSubmit={buscarPorNombre} className="mb-10 ">
          <input
            type="text"
            value={query}
            onChange={(e) => setQuery(e.target.value)}
            placeholder="Introduce un nombre..."
            className="border p-2 rounded w-full mb-2"
          />
          <button
            type="submit"
            className="bg-yellow-400 hover:bg-yellow-500 text-white px-6 py-2 mt-6 rounded"
          >
            Buscar
          </button>
        </form>

        {usuarios.length === 0 ? (
          <p className="text-gray-600">No se han encontrado usuarios.</p>
        ) : (
          <div className="grid grid-cols-1 gap-10">
            {usuarios.map((usuario) => (
              <div key={usuario.id}>
                {/* Nombre fuera de caja */}
                <h3 className="text-3xl font-bold text-gray-900 mb-4">{usuario.nombre}</h3>

                {usuario.diarios?.length ? (
                  <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
                    {usuario.diarios.map((diario) => (
                      <DiarioCard key={diario.id} diario={diario} showCommentButton />
                    ))}
                  </div>
                ) : (
                  <p className="text-sm text-gray-600 mb-6">Este usuario no tiene diarios.</p>
                )}
              </div>
            ))}
          </div>
        )}
      </div>
    </div>
  );
}
