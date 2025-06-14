package com.S2DAW.Proyecto.Vee.Vee.dao;

import com.S2DAW.Proyecto.Vee.Vee.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    List<Usuario> findByNombreContainingIgnoreCase(String nombre);
    Optional<Usuario> findByNombreAndPass(String nombre, String pass);

}