package com.S2DAW.Proyecto.Vee.Vee.service;

import com.S2DAW.Proyecto.Vee.Vee.dto.UsuarioDto;

import java.util.List;

public interface UsuarioSevice {

    List<UsuarioDto> findAll();

    UsuarioDto findById(Long id);

    List<UsuarioDto> findByUsername(String username);

    void save(UsuarioDto usuarioDto);

    void save(UsuarioDto usuarioDto, Long id);

    void delete(Long id);

    UsuarioDto findByNombreAndPass(String nombre, String pass);
}
