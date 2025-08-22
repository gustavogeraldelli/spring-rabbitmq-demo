package dev.gustavo.analise_credito.service;

import dev.gustavo.analise_credito.domain.Proposta;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RabbitMQService {

    private final RabbitTemplate rabbitTemplate;

    public void publicar(String eschange, Proposta proposta) {
        rabbitTemplate.convertAndSend(eschange, "", proposta);
    }

}
