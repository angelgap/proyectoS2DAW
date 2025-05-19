package com.S2DAW.Proyecto.Vee.Vee.service;

import com.S2DAW.Proyecto.Vee.Vee.dto.ComentarioDto;

import java.util.List;

public interface ComentarioService {
    List<ComentarioDto> findAll();


    void save(ComentarioDto comentarioDto);

    void save(ComentarioDto comentarioDto, Long id);

    void deleteById(Long id);
}
