package com.S2DAW.Proyecto.Vee.Vee.rest;

import com.S2DAW.Proyecto.Vee.Vee.service.ComentarioService;
import com.S2DAW.Proyecto.Vee.Vee.service.DiarioService;
import com.S2DAW.Proyecto.Vee.Vee.service.ImagenService;
import com.S2DAW.Proyecto.Vee.Vee.service.UsuarioSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

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
}
