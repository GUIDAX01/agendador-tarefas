package com.guilherme.agendadortarefas.business;

import com.guilherme.agendadortarefas.business.dto.TarefasDTO;
import com.guilherme.agendadortarefas.business.mapper.TarefasConverter;
import com.guilherme.agendadortarefas.infrastructure.entity.TarefasEntity;
import com.guilherme.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.guilherme.agendadortarefas.infrastructure.repository.TarefasRepository;
import com.guilherme.agendadortarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasRepository tarefasRepository;
    private final TarefasConverter tarefaConverter;
    private final JwtUtil jwtUtil;

    public TarefasDTO gravarTarefa(String token, TarefasDTO dto){

        String email = jwtUtil.extrairEmailToken(token.substring(7));
        dto.setDataCriacao(LocalDateTime.now());
        dto.setStatusNotificacaoEnum(StatusNotificacaoEnum.PENDENTE);
        dto.setEmailUsuario(email);
        TarefasEntity entity = tarefaConverter.paraTarefaEntity(dto);

        return tarefaConverter.paraTarefasDTO(
                tarefasRepository.save(entity)
        );
    }
}
