package com.S2DAW.Proyecto.Vee.Vee.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EsAmigoId implements Serializable {
    private static final long serialVersionUID = -1671060631035114660L;
    @Column(name = "USUARIO_ID", nullable = false)
    private Long usuarioId;

    @Column(name = "USUARIO_ID1", nullable = false)
    private Long usuarioId1;

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getUsuarioId1() {
        return usuarioId1;
    }

    public void setUsuarioId1(Long usuarioId1) {
        this.usuarioId1 = usuarioId1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EsAmigoId entity = (EsAmigoId) o;
        return Objects.equals(this.usuarioId1, entity.usuarioId1) &&
                Objects.equals(this.usuarioId, entity.usuarioId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuarioId1, usuarioId);
    }

}