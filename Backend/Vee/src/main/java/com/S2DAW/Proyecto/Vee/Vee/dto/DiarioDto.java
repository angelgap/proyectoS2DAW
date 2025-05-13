package com.S2DAW.Proyecto.Vee.Vee.dto;

import com.S2DAW.Proyecto.Vee.Vee.Diario;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for {@link com.S2DAW.Proyecto.Vee.Vee.Diario}
 */
public class DiarioDto implements Serializable {
    private  Long id;
    private  LocalDate fecha;
    private  String text;
    private  String titulo;
    private  Set<ComentarioDto> comentarios;
    private  Set<ImagenDto> imagens;

    public DiarioDto(Long id, LocalDate fecha, String text, String titulo, Set<ComentarioDto> comentarios, Set<ImagenDto> imagens) {
        this.id = id;
        this.fecha = fecha;
        this.text = text;
        this.titulo = titulo;
        this.comentarios = comentarios;
        this.imagens = imagens;
    }

  public DiarioDto(Diario diario) {
        this.id = diario.getId();
        this.fecha = diario.getFecha();
        this.text = diario.getText();
        this.titulo = diario.getTitulo();
        this.comentarios = ComentarioDto.convertirAComentarioDto(diario.getComentarios());
        this.imagens = ImagenDto.convertirAImagenDto(diario.getImagens());
  }

    public Long getId() {
        return id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getText() {
        return text;
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
                Objects.equals(this.titulo, entity.titulo) &&
                Objects.equals(this.comentarios, entity.comentarios) &&
                Objects.equals(this.imagens, entity.imagens);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fecha, text, titulo, comentarios, imagens);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "fecha = " + fecha + ", " +
                "text = " + text + ", " +
                "titulo = " + titulo + ", " +
                "comentarios = " + comentarios + ", " +
                "imagens = " + imagens + ")";
    }
    public static Set<DiarioDto> convertirADiarioDto(Set<Diario> diarios) {
        Set<DiarioDto> diariosDtoSet = new HashSet<>();
        for ( Diario diario : diarios) {
            DiarioDto diarioDto = new DiarioDto(diario);
            diariosDtoSet.add(diarioDto);
        }
        return diariosDtoSet;
    }
}