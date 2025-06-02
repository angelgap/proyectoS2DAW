import { Comentario } from "./Comentario";
import { Imagen } from "./Imagen";

export interface Diario {
  id: number;
  titulo: string;
  text: string;
  fecha: string;
  usuarioId: number;
  comentarios: Comentario[];
  imagenes: Imagen[];
}
