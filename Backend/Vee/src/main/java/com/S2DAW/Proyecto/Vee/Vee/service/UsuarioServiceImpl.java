package com.S2DAW.Proyecto.Vee.Vee.service;

import com.S2DAW.Proyecto.Vee.Vee.dao.UsuarioRepository;
import com.S2DAW.Proyecto.Vee.Vee.dto.UsuarioDto;
import com.S2DAW.Proyecto.Vee.Vee.entity.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.S2DAW.Proyecto.Vee.Vee.dto.DiarioDto.convertirADiarioDto;

@Service
public class UsuarioServiceImpl implements UsuarioSevice {
    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<UsuarioDto> findAll() {
        List<Usuario> usuarios = usuarioRepository.findAll();

        List<UsuarioDto> usuariosDto = usuarios.stream()
                .map(usuario -> new UsuarioDto(usuario.getId(), usuario.getNombre(), usuario.getEmail(), usuario.getPass(), convertirADiarioDto(usuario.getDiarios())))
                .toList();
        return usuariosDto;

    }

    @Override
    public UsuarioDto findById(Long id) {
        Optional<Usuario> result = usuarioRepository.findById(id);

        Usuario usuario = null;

        if (result.isPresent()) {
            usuario = result.get();
        }
        return new UsuarioDto(usuario);
    }

    @Override
    public List<UsuarioDto> findByUsername(String username) {
        List<Usuario> result  = usuarioRepository.findByNombreContainingIgnoreCase(username);

        List<UsuarioDto> usuariosDto = result.stream()
                .map(usuario -> new UsuarioDto(usuario.getId(), usuario.getNombre(), usuario.getEmail(), usuario.getPass(), convertirADiarioDto(usuario.getDiarios()))).toList();


        return usuariosDto.size() > 0 ? usuariosDto : null;

    }

    @Override
    public void save(UsuarioDto usuarioDto) {
        Usuario usuario = new Usuario();
        usuario.setId(usuarioDto.getId());
        usuario.setNombre(usuarioDto.getNombre());
        usuario.setEmail(usuarioDto.getEmail());
        usuario.setPass(usuarioDto.getPass());
        usuarioRepository.save(usuario);
    }

    @Override
    public void save(UsuarioDto usuarioDto, Long id) {
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre(usuarioDto.getNombre());
        usuario.setEmail(usuarioDto.getEmail());
        usuario.setPass(usuarioDto.getPass());
        usuarioRepository.save(usuario);
    }

    @Override
    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }

    public UsuarioDto findByNombreAndPass(String nombre, String pass) {
        return usuarioRepository.findByNombreAndPass(nombre, pass)
                .map(UsuarioDto::new)
                .orElse(null);
    }

}
