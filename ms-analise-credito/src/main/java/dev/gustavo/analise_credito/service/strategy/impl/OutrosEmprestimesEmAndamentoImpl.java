package dev.gustavo.analise_credito.service.strategy.impl;

import dev.gustavo.analise_credito.domain.Proposta;
import dev.gustavo.analise_credito.service.strategy.CalculoPonto;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class OutrosEmprestimesEmAndamentoImpl implements CalculoPonto {

    @Override
    public int calcular(Proposta proposta) {
        return outrosEmprestimes() ? 50 : 100;
    }

    // mock serviço externo
    private boolean outrosEmprestimes() {
        return new Random().nextBoolean();
    }
}
