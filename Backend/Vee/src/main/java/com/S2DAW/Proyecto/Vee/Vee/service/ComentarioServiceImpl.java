package com.S2DAW.Proyecto.Vee.Vee.service;

import com.S2DAW.Proyecto.Vee.Vee.dao.ComentarioRepository;
import com.S2DAW.Proyecto.Vee.Vee.dto.ComentarioDto;
import com.S2DAW.Proyecto.Vee.Vee.dto.ImagenDto;
import com.S2DAW.Proyecto.Vee.Vee.entity.Comentario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.S2DAW.Proyecto.Vee.Vee.dto.ImagenDto.convertirAImagenDto;
import static java.util.stream.Collectors.toList;

@Service
public class ComentarioServiceImpl implements ComentarioService {

    private ComentarioRepository comentariosRepository;

    @Autowired
    public ComentarioServiceImpl(ComentarioRepository comentariosRepository) {
        this.comentariosRepository = comentariosRepository;
    }

    @Override
    public List<ComentarioDto> findAll() {
        List<Comentario> comentarios = comentariosRepository.findAll();

        List<ComentarioDto> listadoDTO = comentarios.stream().map(comentario -> new ComentarioDto(comentario.getId(),comentario.getTexto(), convertirAImagenDto(comentario.getImagen()),comentario.getDiario().getId(),comentario.getUsuario().getId()))
                .toList();

        return listadoDTO;
    }

    @Override
    public void save(ComentarioDto comentarioDto) {

    }

    @Override
    public void save(ComentarioDto comentarioDto, Long id) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
