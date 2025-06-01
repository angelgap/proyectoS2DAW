package com.S2DAW.Proyecto.Vee.Vee.dto;

import com.S2DAW.Proyecto.Vee.Vee.entity.Usuario;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for {@link Usuario}
 */
public class UsuarioDto implements Serializable {
    private  Long id;
    private  String nombre;
    private  String email;
    private  String pass;
    private  Set<DiarioDto> diarios;

    public UsuarioDto(Long id, String nombre, String email, String pass, Set<DiarioDto> diarios) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.pass = pass;
        this.diarios = diarios;
    }
    public UsuarioDto(Usuario usuario) {
        this.id = usuario.getId();
        this.nombre = usuario.getNombre();
        this.email = usuario.getEmail();
        this.pass = usuario.getPass();
        this.diarios = DiarioDto.convertirADiarioDto(usuario.getDiarios());
    }
    public UsuarioDto() {
    }
    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getPass() {
        return pass;
    }

    public Set<DiarioDto> getDiarios() {
        return diarios;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioDto entity = (UsuarioDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.nombre, entity.nombre) &&
                Objects.equals(this.email, entity.email) &&
                Objects.equals(this.pass, entity.pass) &&
                Objects.equals(this.diarios, entity.diarios);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, email, pass, diarios);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "nombre = " + nombre + ", " +
                "email = " + email + ", " +
                "pass = " + pass + ", " +
                "diarios = " + diarios + ")";
    }
}