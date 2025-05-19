package com.S2DAW.Proyecto.Vee.Vee.service;

import com.S2DAW.Proyecto.Vee.Vee.dao.ComentarioRepository;
import com.S2DAW.Proyecto.Vee.Vee.dao.DiarioRepository;
import com.S2DAW.Proyecto.Vee.Vee.dao.ImagenRepository;
import com.S2DAW.Proyecto.Vee.Vee.dao.UsuarioRepository;
import com.S2DAW.Proyecto.Vee.Vee.dto.ImagenDto;
import com.S2DAW.Proyecto.Vee.Vee.entity.Imagen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImagenServiceImpl implements ImagenService {

   private final ImagenRepository imagenRepository;
    private final ComentarioRepository comentarioRepository;
    private final UsuarioRepository usuarioRepository;
    private final DiarioRepository diarioRepository;


    @Autowired
   public ImagenServiceImpl(ImagenRepository imagenRepository, ComentarioRepository comentarioRepository, UsuarioRepository usuarioRepository, DiarioRepository diarioRepository) {
       this.imagenRepository = imagenRepository;
        this.comentarioRepository = comentarioRepository;
        this.usuarioRepository = usuarioRepository;
        this.diarioRepository = diarioRepository;
    }



    @Override
    public List<ImagenDto> findAll() {
        List<Imagen> imagenes = imagenRepository.findAll();
        List<ImagenDto> listaDto = imagenes.stream()
                .map(imagen -> new ImagenDto(imagen.getId(),imagen.getUrl(),imagen.getTitulo(),imagen.getUsuario().getId(),imagen.getDiario().getId(),imagen.getComentario().getId()))
                .toList();
        return listaDto;
    }


    @Override
    public void save(ImagenDto imagenDto) {
    Imagen imagen = new Imagen();
    imagen.setId(imagenDto.getId());
    imagen.setUrl(imagenDto.getUrl());
    imagen.setTitulo(imagenDto.getTitulo());
    imagen.setComentario(comentarioRepository.getById(Long.valueOf(imagenDto.getComentarioId())));
    imagen.setUsuario(usuarioRepository.getById(Long.valueOf(imagenDto.getUsuarioId())));
    imagen.setDiario(diarioRepository.getById(Long.valueOf(imagenDto.getDiarioId())));
    imagenRepository.save(imagen);
    }


    @Override
    public void delete(Long id) {
    imagenRepository.deleteById(id);
    }
}
