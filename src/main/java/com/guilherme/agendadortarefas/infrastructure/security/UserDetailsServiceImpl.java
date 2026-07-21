package com.guilherme.agendadortarefas.infrastructure.security;

import com.guilherme.agendadortarefas.business.dto.UsuarioDTO;
import com.guilherme.agendadortarefas.infrastructure.security.client.UsuarioCliet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl {

    @Autowired
    private UsuarioCliet cliet;

    public  UserDetails carregaDadosUsuario(String email, String token){

        UsuarioDTO usuarioDTO = cliet.buscaUsuarioPorEmail(email, token);
        return User
                .withUsername(usuarioDTO.getEmail())
                .password(usuarioDTO.getSenha())
                .build();
    }
}
