package com.S2DAW.Proyecto.Vee.Vee.dao;

import com.S2DAW.Proyecto.Vee.Vee.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}