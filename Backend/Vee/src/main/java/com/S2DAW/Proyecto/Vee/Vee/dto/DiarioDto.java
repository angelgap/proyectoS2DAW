package com.S2DAW.Proyecto.Vee.Vee.dto;

import com.S2DAW.Proyecto.Vee.Vee.entity.Comentario;
import com.S2DAW.Proyecto.Vee.Vee.entity.Diario;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for {@link com.S2DAW.Proyecto.Vee.Vee.entity.Diario}
 */
public class DiarioDto implements Serializable {
    private  Long id;
    private  LocalDate fecha;
    private  String text;
    private  Long usuarioId;
    private  String titulo;
    private  Set<ComentarioDto> comentarios;
    private  Set<ImagenDto> imagens;

    public DiarioDto(Long id, LocalDate fecha, String text, Long usuarioId, String titulo, Set<ComentarioDto> comentarios, Set<ImagenDto> imagens) {
        this.fecha = fecha;
        this.text = text;
        this.usuarioId = usuarioId;
        this.titulo = titulo;
        this.comentarios = comentarios;
        this.imagens = imagens;
    }
    public DiarioDto(Diario d){
        this.id = d.getId();
        this.fecha = d.getFecha();
        this.text = d.getText();
        this.usuarioId = d.getUsuario().getId();
        this.titulo = d.getTitulo();
        this.comentarios = ComentarioDto.convertirAComentarioDto(d.getComentarios());
        this.imagens = ImagenDto.convertirAImagenDto(d.getImagens());
    }
    public DiarioDto(){}
    public Long getId() {
        return id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getText() {
        return text;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public String getTitulo() {
        return titulo;
    }

    public Set<ComentarioDto> getComentarios() {
        return comentarios;
    }

    public Set<ImagenDto> getImagens() {
        return imagens;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiarioDto entity = (DiarioDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.fecha, entity.fecha) &&
                Objects.equals(this.text, entity.text) &&
                Objects.equals(this.usuarioId, entity.usuarioId) &&
                Objects.equals(this.titulo, entity.titulo) &&
                Objects.equals(this.comentarios, entity.comentarios) &&
                Objects.equals(this.imagens, entity.imagens);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fecha, text, usuarioId, titulo, comentarios, imagens);
    }

    public static Set<DiarioDto> convertirADiarioDto(Set<Diario> diarios) {
        Set<DiarioDto> diariosDtoSet = new HashSet<>();


        for (Diario diario : diarios) {
            DiarioDto diarioDto = new DiarioDto(diario);
            diariosDtoSet.add(diarioDto);
        }

        return diariosDtoSet;
    }
    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "fecha = " + fecha + ", " +
                "text = " + text + ", " +
                "usuarioId = " + usuarioId + ", " +
                "titulo = " + titulo + ", " +
                "comentarios = " + comentarios + ", " +
                "imagens = " + imagens + ")";
    }
}