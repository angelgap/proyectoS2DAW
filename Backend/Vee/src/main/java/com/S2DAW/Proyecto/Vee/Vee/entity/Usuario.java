package com.S2DAW.Proyecto.Vee.Vee.entity;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "USUARIO")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "NOMBRE", length = 20)
    private String nombre;

    @Column(name = "EMAIL", nullable = false, length = 320)
    private String email;

    @Column(name = "PASS", nullable = false, length = 4000)
    private String pass;

    @OneToMany(mappedBy = "usuario")
    private Set<Comentario> comentarios = new LinkedHashSet<>();

    @OneToMany(mappedBy = "usuario")
    private Set<Diario> diarios = new LinkedHashSet<>();

    @OneToMany(mappedBy = "usuario")
    private Set<Imagen> imagens = new LinkedHashSet<>();

    public Set<Imagen> getImagens() {
        return imagens;
    }

    public void setImagens(Set<Imagen> imagens) {
        this.imagens = imagens;
    }

    public Set<Diario> getDiarios() {
        return diarios;
    }

    public void setDiarios(Set<Diario> diarios) {
        this.diarios = diarios;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

}