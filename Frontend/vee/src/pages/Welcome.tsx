import { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import { Usuario } from "../types/Usuario";

function Welcome() {
  const navigate = useNavigate();
  const [nombre, setNombre] = useState("");
  const [email, setEmail] = useState("");
  const [pass, setPass] = useState("");

  const handleRegistro = async () => {
    try {
      await axios.post("http://localhost:8080/usuarios", {
        nombre,
        email,
        pass,
      });
      alert("Registrado correctamente. Inicia sesión.");
    } catch (err) {
      alert("Error en el registro.");
    }
  };

  const handleLogin = async () => {
    try {
      const res = await axios.post("http://localhost:8080/login", {
        nombre,
        pass,
      });
      const usuario: Usuario = res.data;
      navigate("/home", { state: { usuario } });
    } catch (err) {
      alert("Credenciales incorrectas.");
    }
  };

  return (
    <div style={{ padding: 20 }}>
      <h2>Bienvenido a VEE</h2>
      <input value={nombre} onChange={e => setNombre(e.target.value)} placeholder="Nombre" /><br />
      <input value={email} onChange={e => setEmail(e.target.value)} placeholder="Correo" /><br />
      <input type="password" value={pass} onChange={e => setPass(e.target.value)} placeholder="Contraseña" /><br />
      <button onClick={handleRegistro}>Registrarse</button>
      <button onClick={handleLogin}>Iniciar sesión</button>
    </div>
  );
}

export default Welcome;
