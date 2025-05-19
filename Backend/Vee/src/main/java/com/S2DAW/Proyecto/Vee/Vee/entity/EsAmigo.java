package com.S2DAW.Proyecto.Vee.Vee.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "ES_AMIGO")
public class EsAmigo {
    @EmbeddedId
    private EsAmigoId id;

    @MapsId("usuarioId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "USUARIO_ID", nullable = false)
    private Usuario usuario;

    @MapsId("usuarioId1")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "USUARIO_ID1", nullable = false)
    private Usuario usuarioId1;

    public EsAmigoId getId() {
        return id;
    }

    public void setId(EsAmigoId id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuarioId1() {
        return usuarioId1;
    }

    public void setUsuarioId1(Usuario usuarioId1) {
        this.usuarioId1 = usuarioId1;
    }

}