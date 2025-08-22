package dev.gustavo.analise_credito.listener;

import dev.gustavo.analise_credito.domain.Proposta;
import dev.gustavo.analise_credito.service.AnaliseCreditoService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PropostaPendenteListener {

    private final AnaliseCreditoService analiseCreditoService;

    // proposta-pendente.ms-analisar-credito
    @RabbitListener(queues = "${rabbitmq.queue.proposta.pendente}")
    public void analisarPropostaPendente(Proposta proposta) {
        analiseCreditoService.analisar(proposta);
    }
}
