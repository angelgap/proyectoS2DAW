package com.S2DAW.Proyecto.Vee.Vee.dao;

import com.S2DAW.Proyecto.Vee.Vee.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
}