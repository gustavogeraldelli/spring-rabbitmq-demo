package dev.gustavo.analise_credito.service;

import dev.gustavo.analise_credito.domain.Proposta;
import dev.gustavo.analise_credito.service.strategy.CalculoPonto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnaliseCreditoService {

    private final List<CalculoPonto> calculoPontos;
    private final RabbitMQService rabbitMQService;

    @Value("${rabbitmq.exchange.proposta.pendente}")
    private String exchange;

    public void analisar(Proposta proposta) {
        try {
            boolean res = calculoPontos.stream().
                    mapToInt(impl -> impl.calcular(proposta))
                    .sum() > 400;
            proposta.setAprovada(res);
        }
        catch (RuntimeException e) {
            proposta.setAprovada(false);
            proposta.setObservacao(e.getMessage());
        }
        rabbitMQService.publicar(exchange, proposta);
    }

}
