package com.S2DAW.Proyecto.Vee.Vee.service;

import com.S2DAW.Proyecto.Vee.Vee.dto.UsuarioDto;

import java.util.List;

public interface UsuarioSevice {

    List<UsuarioDto> findAll();

    UsuarioDto findById(Long id);

    void save(UsuarioDto usuarioDto);

    void save(UsuarioDto usuarioDto, Long id);

    void delete(Long id);
}
