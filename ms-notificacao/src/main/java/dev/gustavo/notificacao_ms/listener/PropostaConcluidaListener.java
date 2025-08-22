package dev.gustavo.notificacao_ms.listener;

import dev.gustavo.notificacao_ms.domain.Proposta;
import dev.gustavo.notificacao_ms.service.SNSService;
import dev.gustavo.notificacao_ms.utils.Mensagens;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PropostaConcluidaListener {

    private final SNSService snsService;

    @RabbitListener(queues = "${rabbitmq.queue.proposta.concluida}")
    public void propostaPendente(Proposta proposta) {
        String msg = "";
        if (proposta.getAprovada()) msg = Mensagens.PROPOSTA_APROVADA;
        else msg = Mensagens.PROPOSTA_NEGADA;
        String fmt = String.format(msg, proposta.getUsuario().getNome());
        snsService.notificar(proposta.getUsuario().getTelefone(), fmt);
    }

}
