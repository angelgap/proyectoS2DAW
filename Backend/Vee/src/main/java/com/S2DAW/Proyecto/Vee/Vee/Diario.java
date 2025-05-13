package com.S2DAW.Proyecto.Vee.Vee;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "DIARIO")
public class Diario {
    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "FECHA", nullable = false)
    private LocalDate fecha;

    @Column(name = "TEXT", length = 4000)
    private String text;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "USUARIO_ID", nullable = false)
    private Usuario usuario;

    @Column(name = "TITULO", length = 20)
    private String titulo;

    @OneToMany(mappedBy = "diario")
    private Set<Comentario> comentarios = new LinkedHashSet<>();

    @OneToMany(mappedBy = "diario")
    private Set<Imagen> imagens = new LinkedHashSet<>();

    public Set<Imagen> getImagens() {
        return imagens;
    }

    public void setImagens(Set<Imagen> imagens) {
        this.imagens = imagens;
    }

    public Set<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(Set<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

}