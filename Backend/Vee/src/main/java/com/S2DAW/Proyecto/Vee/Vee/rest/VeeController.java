package com.S2DAW.Proyecto.Vee.Vee.rest;

import com.S2DAW.Proyecto.Vee.Vee.dto.ComentarioDto;
import com.S2DAW.Proyecto.Vee.Vee.dto.DiarioDto;
import com.S2DAW.Proyecto.Vee.Vee.dto.ImagenDto;
import com.S2DAW.Proyecto.Vee.Vee.dto.UsuarioDto;
import com.S2DAW.Proyecto.Vee.Vee.service.ComentarioService;
import com.S2DAW.Proyecto.Vee.Vee.service.DiarioService;
import com.S2DAW.Proyecto.Vee.Vee.service.ImagenService;
import com.S2DAW.Proyecto.Vee.Vee.service.UsuarioSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VeeController {

    ImagenService imagenService;
    ComentarioService comentarioService;
    DiarioService diarioService;
    UsuarioSevice usuarioSevice;

    @Autowired
    public VeeController(ImagenService imagenService, ComentarioService comentarioService, DiarioService diarioService, UsuarioSevice usuarioSevice) {
        this.imagenService = imagenService;
        this.comentarioService = comentarioService;
        this.diarioService = diarioService;
        this.usuarioSevice = usuarioSevice;
    }

    @GetMapping("/usuarios")
    public List<UsuarioDto> usuarios(){return usuarioSevice.findAll();}

    @GetMapping("/usuarios/{id}")
    public UsuarioDto usuario(@PathVariable Long id) {
        UsuarioDto usuario = usuarioSevice.findById(id);
        if (usuario == null) {
            return null;
        }
        return usuario;
    }
    @GetMapping("/usuarios/{username}")
    public List<UsuarioDto> usuarioByUsername(@PathVariable String username) {
        return usuarioSevice.findByUsername(username);
    }
    @GetMapping("/diarios")
    public List< DiarioDto> diarios(){return diarioService.findAll();}

    @GetMapping("/comentarios")
    public List<ComentarioDto> comentarios(){return comentarioService.findAll();}

    @GetMapping("/imagenes")
    public List<ImagenDto> imagenes(){return imagenService.findAll();}

    @PostMapping("/usuarios")
    public void saveUsuario(@RequestBody UsuarioDto usuarioDto){usuarioSevice.save(usuarioDto);}

    @PostMapping("/diarios")
    public void saveDiario(@RequestBody DiarioDto diarioDto){diarioService.save(diarioDto);}

    @PostMapping("/comentarios")
    public void saveComentario(@RequestBody ComentarioDto comentarioDto){comentarioService.save(comentarioDto);}

    @PostMapping("/imagenes")
    public void saveImagen(@RequestBody ImagenDto imagenDto){imagenService.save(imagenDto);}

    @PutMapping("/usuarios/{id}")
    public void updateUsuario(@PathVariable Long id , @RequestBody UsuarioDto usuarioDto){
        usuarioSevice.save(usuarioDto,id);
    }
    @PutMapping("/diarios/{id}")
    public void updateDiario(@PathVariable Long id , @RequestBody DiarioDto diarioDto){
        diarioService.save(diarioDto,id);
    }
    @PutMapping("/comentarios/{id}")
    public void updateComentario(@PathVariable Long id , @RequestBody ComentarioDto comentarioDto){
        comentarioService.save(comentarioDto,id);
    }
    @DeleteMapping("/usuarios/{id}")
    public void deleteUsuario(@PathVariable Long id ){
        usuarioSevice.delete(id);
    }
    @DeleteMapping("/diarios/{id}")
    public void deleteDiario(@PathVariable Long id ){
        diarioService.delete(id);
    }
    @DeleteMapping("/comentarios/{id}")
    public void deleteComentario(@PathVariable Long id ){
        comentarioService.deleteById(id);
    }
    @DeleteMapping("/imagenes/{id}")
    public void deleteImagen(@PathVariable Long id ){
        imagenService.delete(id);
    }



}
