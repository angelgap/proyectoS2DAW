import { Imagen } from "./Imagen";

export interface Comentario {
  id: number;
  texto: string;
  usuarioId: number;
  diarioId: number;
  imagen?: Imagen | null;
}
