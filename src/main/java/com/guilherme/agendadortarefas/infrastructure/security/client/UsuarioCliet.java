package com.guilherme.agendadortarefas.infrastructure.security.client;

import com.guilherme.agendadortarefas.business.dto.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "usuario", url = "${usuario.url}")
public interface UsuarioCliet {

    @GetMapping
    UsuarioDTO buscaUsuarioPorEmail(@RequestParam("email") String email,
                                    @RequestHeader("Authorization") String token);
}
