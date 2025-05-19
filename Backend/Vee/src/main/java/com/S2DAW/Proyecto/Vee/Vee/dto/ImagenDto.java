package com.S2DAW.Proyecto.Vee.Vee.dto;

import com.S2DAW.Proyecto.Vee.Vee.entity.Imagen;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for {@link Imagen}
 */
public class ImagenDto implements Serializable {
    private  Long id;
    private  String url;
    private String titulo;
    private  Long usuarioId;
    private  Long diarioId;
    private  Long comentarioId;

    public ImagenDto(Long id, String url, String titulo, Long usuarioId, Long diarioId, Long comentarioId) {
        this.id = id;
        this.url = url;
        this.titulo = titulo;
        this.usuarioId = usuarioId;
        this.diarioId = diarioId;
        this.comentarioId = comentarioId;
    }

      public ImagenDto() {}
    public ImagenDto(Imagen imagen) {
        this.id = imagen.getId();
        this.url = imagen.getUrl();
        this.comentarioId = imagen.getComentario().getId();
        this.usuarioId = imagen.getUsuario().getId();
        this.diarioId = imagen.getDiario().getId();
    }



    public Long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getTitulo() {
        return titulo;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public Long getDiarioId() {
        return diarioId;
    }

    public Long getComentarioId() {
        return comentarioId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImagenDto entity = (ImagenDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.url, entity.url) &&
                Objects.equals(this.titulo, entity.titulo) &&
                Objects.equals(this.usuarioId, entity.usuarioId) &&
                Objects.equals(this.diarioId, entity.diarioId) &&
                Objects.equals(this.comentarioId, entity.comentarioId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url, titulo, usuarioId, diarioId, comentarioId);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "url = " + url + ", " +
                "titulo = " + titulo + ", " +
                "usuarioId = " + usuarioId + ", " +
                "diarioId = " + diarioId + ", " +
                "comentarioId = " + comentarioId + ")";
    }

    public static Set<ImagenDto> convertirAImagenDto(Set<Imagen> imagenes) {
        Set<ImagenDto> imagenesDtoSet = new HashSet<>();
        for (Imagen imagen : imagenes) {
            ImagenDto imagenDto = new ImagenDto(imagen);
            imagenesDtoSet.add(imagenDto);
        }
        return imagenesDtoSet;
    }
    public static ImagenDto convertirAImagenDto(Imagen imagen) {

        ImagenDto imagenDto = new ImagenDto(imagen);


        return imagenDto;
    }
}