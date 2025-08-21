package dev.gustavo.proposta_app.service;

import dev.gustavo.proposta_app.dto.PropostaResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class NotificationService {

    private final RabbitTemplate rabbitTemplate;

    public void notificar(PropostaResponseDTO dto, String exchange) {
        rabbitTemplate.convertAndSend(exchange, "", dto);
    }

}
