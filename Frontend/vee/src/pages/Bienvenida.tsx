import { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import { useUsuario } from "../context/UsuarioContext";

export default function Bienvenida() {
  const [modo, setModo] = useState<"login" | "registro">("login");
  const [nombre, setNombre] = useState("");
  const [email, setEmail] = useState("");
  const [pass, setPass] = useState("");
  const [error, setError] = useState("");

  const { iniciarSesion } = useUsuario();
  const navigate = useNavigate();

  const manejarEnvio = async (e: React.FormEvent) => {
    e.preventDefault();
    setError("");

    try {
      if (modo === "registro") {
        await axios.post("http://localhost:8080/usuarios", {
          nombre,
          email,
          pass,
        });
        alert("Registro exitoso. Ahora inicia sesión.");
        setModo("login");
      } else {
        const res = await axios.post("http://localhost:8080/login", {
          nombre,
          pass,
        });
        iniciarSesion(res.data);
        navigate("/home");
      }
    } catch (err) {
      console.error(err);
      setError("Credenciales incorrectas o error en el servidor.");
    }
  };

  return (
    <div className="min-h-screen flex flex-col items-center justify-center bg-gray-100 px-4">
      <div className="max-w-md w-full bg-white p-6 rounded shadow">
        <h1 className="text-2xl font-bold mb-4 text-center">
          {modo === "login" ? "Iniciar Sesión" : "Registro de Usuario"}
        </h1>

        <form onSubmit={manejarEnvio} className="space-y-4">
          <input
            type="text"
            placeholder="Nombre de usuario"
            value={nombre}
            onChange={(e) => setNombre(e.target.value)}
            required
            className="w-full border p-2 rounded"
          />

          {modo === "registro" && (
            <input
              type="email"
              placeholder="Correo electrónico"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
              className="w-full border p-2 rounded"
            />
          )}

          <input
            type="password"
            placeholder="Contraseña"
            value={pass}
            onChange={(e) => setPass(e.target.value)}
            required
            className="w-full border p-2 rounded"
          />

          {error && <p className="text-red-500 text-sm">{error}</p>}

          <button
            type="submit"
            className="w-full bg-yellow-400 hover:bg-yellow-500 text-white font-bold py-2 px-4 rounded"
          >
            {modo === "login" ? "Entrar" : "Registrarse"}
          </button>
        </form>

        <p className="mt-4 text-center text-sm">
          {modo === "login" ? "¿No tienes cuenta?" : "¿Ya tienes cuenta?"}{" "}
          <button
            onClick={() => setModo(modo === "login" ? "registro" : "login")}
            className="text-blue-500 hover:underline"
          >
            {modo === "login" ? "Regístrate aquí" : "Inicia sesión"}
          </button>
        </p>
      </div>
    </div>
  );
}
