package dev.gustavo.proposta_app.service;

import dev.gustavo.proposta_app.dto.PropostaResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class WebSocketService {

    private final SimpMessagingTemplate template;

    public void ws(PropostaResponseDTO propostaResponseDTO) {
        template.convertAndSend("/propostas", propostaResponseDTO);
    }

}
