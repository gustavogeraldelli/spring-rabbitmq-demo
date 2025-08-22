package dev.gustavo.analise_credito.service.strategy.impl;

import dev.gustavo.analise_credito.service.strategy.CalculoPonto;
import dev.gustavo.analise_credito.domain.Proposta;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@Order(1)
public class NomeNegativadoImpl implements CalculoPonto {

    @Override
    public int calcular(Proposta proposta) {
        if (nomeNegativado()) throw new RuntimeException("Nome negativado");
        return 100;
    }

    // mock serviço externo /serasa
    private boolean nomeNegativado() {
        return new Random().nextBoolean();
    }

}
