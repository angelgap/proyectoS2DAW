import { createContext, useContext, useState, useEffect } from 'react';
import type { ReactNode } from 'react';
import type { Usuario } from '../types/Usuario';

interface ContextoUsuario {
  usuario: Usuario | null;
  iniciarSesion: (usuario: Usuario) => void;
  cerrarSesion: () => void;
}

const UsuarioContext = createContext<ContextoUsuario>({
  usuario: null,
  iniciarSesion: () => {},
  cerrarSesion: () => {},
});

export const ProveedorUsuario = ({ children }: { children: ReactNode }) => {
  const [usuario, setUsuario] = useState<Usuario | null>(() => {
    const guardado = localStorage.getItem('usuario');
    return guardado ? JSON.parse(guardado) : null;
  });

  const iniciarSesion = (usuario: Usuario) => {
    localStorage.setItem('usuario', JSON.stringify(usuario));
    setUsuario(usuario);
  };

  const cerrarSesion = () => {
    localStorage.removeItem('usuario');
    setUsuario(null);
  };

  return (
    <UsuarioContext.Provider value={{ usuario, iniciarSesion, cerrarSesion }}>
      {children}
    </UsuarioContext.Provider>
  );
};

export const useUsuario = () => useContext(UsuarioContext);
