import axios from "axios";
import type { Usuario } from "../types/Usuario";
import type { Diario } from "../types/Diario";

const API_URL = "http://localhost:8080";

// ✅ Crear nuevo usuario
export const registrarUsuario = (usuario: Omit<Usuario, "id">) =>
  axios.post(`${API_URL}/usuarios`, usuario);

// ✅ Login
export const loginUsuario = (nombre: string, pass: string) =>
  axios.post<Usuario>(`${API_URL}/login`, { nombre, pass });

// ✅ Obtener diarios de un usuario
export const getDiariosUsuario = (usuarioId: number) =>
  axios.get<Diario[]>(`${API_URL}/usuarios/${usuarioId}/diarios`);

// ✅ Crear nuevo diario
export const crearDiario = (usuarioId: number, diario: Omit<Diario, "id">) =>
  axios.post(`${API_URL}/usuarios/${usuarioId}/diarios`, diario);

// Puedes ir añadiendo aquí más funciones, como editar, comentar, subir imágenes, etc.
