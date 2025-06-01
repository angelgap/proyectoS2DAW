import { useLocation } from "react-router-dom";
import { Usuario } from "../types/Usuario";

function Home() {
  const location = useLocation();
  const usuario = location.state?.usuario as Usuario;

  return (
    <div style={{ padding: 20 }}>
      <h1>Hola, {usuario?.nombre}</h1>
      <p>Correo: {usuario?.email}</p>
    </div>
  );
}

export default Home;
