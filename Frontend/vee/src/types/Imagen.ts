export interface Imagen {
  id: number;
  url: string;
  titulo?: string | null;
  usuarioId: number;
  diarioId?: number | null;
  comentarioId?: number | null;
}
