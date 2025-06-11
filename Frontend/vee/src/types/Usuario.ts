import { Diario } from "./Diario";

export interface Usuario {
  id: number;
  nombre: string;
  email: string;
  pass: string;
    diarios?: Diario[];

}
