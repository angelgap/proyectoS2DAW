package com.S2DAW.Proyecto.Vee.Vee.service;

import com.S2DAW.Proyecto.Vee.Vee.dto.DiarioDto;

import java.util.List;

public interface DiarioService {

    List<DiarioDto> findAll();

    DiarioDto findById(Long id);

    void save(DiarioDto diarioDto);

    void save(DiarioDto diarioDto, Long id);

    void delete(Long id);
}
