package dev.gustavo.proposta_app.listener;

import dev.gustavo.proposta_app.entity.Proposta;
import dev.gustavo.proposta_app.mapper.PropostaMapper;
import dev.gustavo.proposta_app.repository.PropostaRepository;
import dev.gustavo.proposta_app.service.WebSocketService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PropostaConcluidaListener {

    private final PropostaRepository propostaRepository;
    private final WebSocketService webSocketService;

    @RabbitListener(queues = "proposta-concluida.ms-proposta")
    private void propostaConcluida(Proposta proposta) {
        propostaRepository.save(proposta);

        webSocketService.ws(PropostaMapper.INSTANCE.toPropostaResponseDTO(proposta));
    }

}
