import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';
import Bienvenida from './pages/Bienvenida';
import Home from './pages/Home';
import { useUsuario, ProveedorUsuario } from './context/UsuarioContext';

function AppRoutes() {
  const { usuario } = useUsuario();

  return (
    <Routes>
      <Route path="/" element={<Bienvenida />} />
      <Route path="/home" element={usuario ? <Home /> : <Navigate to="/" />} />
    </Routes>
  );
}

export default function App() {
  return (
    <ProveedorUsuario>
      <BrowserRouter>
        <AppRoutes />
      </BrowserRouter>
    </ProveedorUsuario>
  );
}
