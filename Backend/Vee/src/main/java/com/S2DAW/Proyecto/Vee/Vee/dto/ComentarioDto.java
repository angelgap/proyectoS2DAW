package com.S2DAW.Proyecto.Vee.Vee.dto;

import com.S2DAW.Proyecto.Vee.Vee.Comentario;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for {@link com.S2DAW.Proyecto.Vee.Vee.Comentario}
 */
public class ComentarioDto implements Serializable {
    private  Long id;
    private  String texto;
    private  ImagenDto imagen;

    public ComentarioDto(Long id, String texto, ImagenDto imagen) {
        this.id = id;
        this.texto = texto;
        this.imagen = imagen;
    }

    public ComentarioDto(Comentario comentario) {
        this.id = comentario.getId();
        this.texto = comentario.getTexto();
        this.imagen = new ImagenDto(comentario.getImagen());
    }

    public ComentarioDto() {}

    public Long getId() {
        return id;
    }

    public String getTexto() {
        return texto;
    }

    public ImagenDto getImagen() {
        return imagen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComentarioDto entity = (ComentarioDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.texto, entity.texto) &&
                Objects.equals(this.imagen, entity.imagen);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, texto, imagen);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "texto = " + texto + ", " +
                "imagen = " + imagen + ")";
    }
    public static Set<ComentarioDto> convertirAComentarioDto(Set<Comentario> comentarios) {
        Set<ComentarioDto> comentariosDtoSet = new HashSet<>();


        for (Comentario comentario : comentarios) {
            ComentarioDto comentarioDto = new ComentarioDto(comentario);
            comentariosDtoSet.add(comentarioDto);
        }

        return comentariosDtoSet;
    }
}