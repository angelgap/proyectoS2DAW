package com.S2DAW.Proyecto.Vee.Vee.service;

import com.S2DAW.Proyecto.Vee.Vee.dao.ComentarioRepository;
import com.S2DAW.Proyecto.Vee.Vee.dao.DiarioRepository;
import com.S2DAW.Proyecto.Vee.Vee.dao.UsuarioRepository;
import com.S2DAW.Proyecto.Vee.Vee.dto.DiarioDto;
import com.S2DAW.Proyecto.Vee.Vee.entity.Diario;
import com.S2DAW.Proyecto.Vee.Vee.entity.Imagen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.S2DAW.Proyecto.Vee.Vee.dto.ComentarioDto.convertirAComentarioDto;
import static com.S2DAW.Proyecto.Vee.Vee.dto.ImagenDto.convertirAImagenDto;

@Service
public class DiarioServiceImpl implements DiarioService {

    private final ComentarioRepository comentarioRepository;
    private final DiarioRepository diarioRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public DiarioServiceImpl(ComentarioRepository comentarioRepository, DiarioRepository diarioRepository, UsuarioRepository usuarioRepository) {
        this.comentarioRepository = comentarioRepository;
        this.diarioRepository = diarioRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<DiarioDto> findAll() {
        List<Diario> diarios = diarioRepository.findAll();
        List<DiarioDto> listaDto = diarios.stream().
                map(diario -> new DiarioDto(diario.getId(),diario.getFecha(), diario.getText(), diario.getUsuario().getId(), diario.getTitulo(), convertirAComentarioDto( diario.getComentarios()), convertirAImagenDto(diario.getImagens())))
                .toList();
        return listaDto;

    }



    @Override
    public void save(DiarioDto diarioDto) {
        Diario diario = new Diario();

        diario.setFecha(LocalDate.now()); // <-- así lo genera el backend
        diario.setText(diarioDto.getText());
        diario.setTitulo(diarioDto.getTitulo());
        diario.setUsuario(usuarioRepository.getById(diarioDto.getUsuarioId()));
       /* diario.setImagens(diarioDto.getImagens().stream()
                .map(imagenDto -> new Imagen(imagenDto.getId(), imagenDto.getUrl(), imagenDto.getTitulo()))
                .collect(Collectors.toSet()));*/
        diarioRepository.save(diario);
    }



    @Override
    public void save(DiarioDto diarioDto, Long id) {
        Diario diario = new Diario();
        diario.setId(id);
        diario.setText(diarioDto.getText());
        diario.setTitulo(diarioDto.getTitulo());
        diario.setUsuario(usuarioRepository.getById(diarioDto.getUsuarioId()));
        diario.setFecha(diarioDto.getFecha());
        diarioRepository.save(diario);

    }

    @Override
    public void delete(Long id) {
        Diario diario = diarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Diario no encontrado"));
        diarioRepository.delete(diario);
    }

}
