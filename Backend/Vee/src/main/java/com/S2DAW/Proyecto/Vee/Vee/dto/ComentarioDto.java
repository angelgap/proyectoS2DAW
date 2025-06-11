package com.S2DAW.Proyecto.Vee.Vee.dto;

import com.S2DAW.Proyecto.Vee.Vee.entity.Comentario;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for {@link com.S2DAW.Proyecto.Vee.Vee.entity.Comentario}
 */
public class ComentarioDto implements Serializable {
    private  Long id;
    private  String texto;
    private  ImagenDto imagen;
    private  Long diarioId;
    private  Long usuarioId;
    private  String usuarioNombre;


    public ComentarioDto(Long id, String texto, ImagenDto imagen, Long diarioId, Long usuarioId, String usuarioNombre) {
        this.id = id;
        this.texto = texto;
        this.imagen = imagen;
        this.diarioId = diarioId;
        this.usuarioId = usuarioId;
        this.usuarioNombre = usuarioNombre;
    }

    public ComentarioDto(Comentario c) {
        this.id =c.getId();
        this.texto = c.getTexto();
        this.imagen = (c.getImagen() != null) ? new ImagenDto(c.getImagen()) : null;
        this.diarioId = c.getDiario().getId();
        this.usuarioId = c.getUsuario().getId();
        this.usuarioNombre = c.getUsuario().getNombre();
    }
public ComentarioDto(){}


    public Long getId() {
        return id;
    }

    public String getTexto() {
        return texto;
    }

    public ImagenDto getImagen() {
        return imagen;
    }

    public Long getDiarioId() {
        return diarioId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }
    public String getUsuarioNombre(){
        return usuarioNombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComentarioDto entity = (ComentarioDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.texto, entity.texto) &&
                Objects.equals(this.imagen, entity.imagen) &&
                Objects.equals(this.diarioId, entity.diarioId) &&
                Objects.equals(this.usuarioId, entity.usuarioId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, texto, imagen, diarioId, usuarioId);
    }

    public static Set<ComentarioDto> convertirAComentarioDto(Set<Comentario> comentarios) {
        Set<ComentarioDto> comentariosDtoSet = new HashSet<>();


        for (Comentario comentario : comentarios) {
            ComentarioDto comentarioDto = new ComentarioDto(comentario);
            comentariosDtoSet.add(comentarioDto);
        }

        return comentariosDtoSet;
    }
    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "texto = " + texto + ", " +
                "imagen = " + imagen + ", " +
                "diarioId = " + diarioId + ", " +
                "usuarioId = " + usuarioId + ", "+
                "usuarioNombre = " + usuarioNombre +")";
    }
}