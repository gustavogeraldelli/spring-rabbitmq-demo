package dev.gustavo.notificacao_ms.listener;

import dev.gustavo.notificacao_ms.domain.Proposta;
import dev.gustavo.notificacao_ms.service.SNSService;
import dev.gustavo.notificacao_ms.utils.Mensagens;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PropostaPendenteListener {

    private final SNSService snsService;

    @RabbitListener(queues = "${rabbitmq.queue.proposta.pendente}")
    public void propostaPendente(Proposta proposta) {
        String msg = String.format(Mensagens.PROPOSTA_EM_ANALISE, proposta.getUsuario().getNome());
        snsService.notificar(proposta.getUsuario().getTelefone(), msg);
    }

}
