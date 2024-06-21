package com.urubu.web.endpoint;

import com.urubu.model.UserDto;
import com.urubu.model.auth.UserRegisterDto;
import com.urubu.service.UserService;
import jakarta.ws.rs.Path;
import org.springframework.beans.factory.annotation.Autowired;

@Path("/api/v1/user")
public class UsuarioController {

    private final UserService usuarioService;

    @Autowired
    public UsuarioController(UserService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Path("/register")
    public UserDto register(UserRegisterDto registerDto) {
        return usuarioService.registerUser(registerDto);
    }

}
