package dev.gustavo.notificacao_ms.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;

@RequiredArgsConstructor
@Service
public class SNSService {

    private final SnsClient snsClient;

    public void notificar(String telefone, String mensagem) {
        PublishRequest publishRequest = PublishRequest.builder()
                .message(mensagem)
                .phoneNumber(telefone)
                .build();
        snsClient.publish(publishRequest); //tem response
    }

}
