package dev.gustavo.proposta_app.scheduler;

import dev.gustavo.proposta_app.repository.PropostaRepository;
import dev.gustavo.proposta_app.service.RabbitMQService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Component
public class PropostasNaoIntegradas {

    private final PropostaRepository propostaRepository;
    private final RabbitMQService rabbitMQService;

    private final Logger logger = LoggerFactory.getLogger(PropostasNaoIntegradas.class);

    // tentar enviar novamente para a fila
    @Scheduled(fixedDelay = 30, timeUnit = TimeUnit.SECONDS)
    public void buscarPropostasQueNaoForamIntegradas() {
        propostaRepository.findByIntegradaIsFalse().forEach(proposta -> {
           try {
               rabbitMQService.notificar(proposta, "proposta-pendente.ex");
               proposta.setIntegrada(true);
               propostaRepository.save(proposta);
           }
           catch (RuntimeException e) {
               logger.error(e.getMessage());
           }
        });
    }

}
