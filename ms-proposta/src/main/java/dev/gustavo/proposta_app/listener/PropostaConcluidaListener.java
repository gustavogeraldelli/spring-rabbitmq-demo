package dev.gustavo.proposta_app.listener;

import dev.gustavo.proposta_app.entity.Proposta;
import dev.gustavo.proposta_app.repository.PropostaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PropostaConcluidaListener {

    private final PropostaRepository propostaRepository;

    @RabbitListener(queues = "proposta-concluida.ms-proposta")
    private void propostaConcluida(Proposta proposta) {
        propostaRepository.save(proposta);
    }

}
