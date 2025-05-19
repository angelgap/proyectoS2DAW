package com.S2DAW.Proyecto.Vee.Vee.service;

import com.S2DAW.Proyecto.Vee.Vee.dto.ImagenDto;
import com.S2DAW.Proyecto.Vee.Vee.entity.Imagen;

import java.util.List;

public interface ImagenService {

    List<ImagenDto> findAll();

    void save(ImagenDto imagen);


    void delete(Long id);
}
