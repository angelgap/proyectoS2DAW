package com.S2DAW.Proyecto.Vee.Vee.service;

import com.S2DAW.Proyecto.Vee.Vee.dao.ComentarioRepository;
import com.S2DAW.Proyecto.Vee.Vee.dao.DiarioRepository;
import com.S2DAW.Proyecto.Vee.Vee.dao.ImagenRepository;
import com.S2DAW.Proyecto.Vee.Vee.dao.UsuarioRepository;
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

    private final DiarioRepository diarioRepository;
    private final ImagenRepository imagenRepository;
    private final UsuarioRepository usuarioRepository;
    private ComentarioRepository comentariosRepository;

    @Autowired
    public ComentarioServiceImpl(ComentarioRepository comentariosRepository, DiarioRepository diarioRepository, ImagenRepository imagenRepository, UsuarioRepository usuarioRepository) {
        this.comentariosRepository = comentariosRepository;
        this.diarioRepository = diarioRepository;
        this.imagenRepository = imagenRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<ComentarioDto> findAll() {
        List<Comentario> comentarios = comentariosRepository.findAll();

        List<ComentarioDto> listadoDTO = comentarios.stream().map(comentario -> new ComentarioDto(comentario.getId(), comentario.getTexto(), convertirAImagenDto(comentario.getImagen()), comentario.getDiario().getId(), comentario.getUsuario().getId(),comentario.getUsuario().getNombre())).toList();

        return listadoDTO;
    }

    @Override
    public void save(ComentarioDto comentarioDto) {
        Comentario comentario = new Comentario();
        comentario.setTexto(comentarioDto.getTexto());
        comentario.setUsuario(usuarioRepository.getById(comentarioDto.getUsuarioId()));
        if (comentarioDto.getImagen() != null) {
            Long imagenId = comentarioDto.getImagen().getId();
        }

        comentario.setDiario(diarioRepository.getById(comentarioDto.getDiarioId()));
        comentariosRepository.save(comentario);

    }

    @Override
    public void save(ComentarioDto comentarioDto, Long id) {
    Comentario comentario = new Comentario();
    comentario.setId(id);
    comentario.setTexto(comentarioDto.getTexto());
    comentario.setUsuario(usuarioRepository.getById(comentarioDto.getUsuarioId()));
    comentario.setImagen(imagenRepository.getById(comentarioDto.getImagen().getId()));
    comentario.setDiario(diarioRepository.getById(comentarioDto.getDiarioId()));
    comentariosRepository.save(comentario);
    }

    @Override
    public void deleteById(Long id) {
    comentariosRepository.deleteById(id);
    }
}
