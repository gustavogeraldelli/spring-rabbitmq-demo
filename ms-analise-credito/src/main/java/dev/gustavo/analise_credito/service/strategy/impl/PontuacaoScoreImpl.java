package dev.gustavo.analise_credito.service.strategy.impl;

import dev.gustavo.analise_credito.domain.Proposta;
import dev.gustavo.analise_credito.service.strategy.CalculoPonto;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@Order(2)
public class PontuacaoScoreImpl implements CalculoPonto {

    @Override
    public int calcular(Proposta proposta) {
        int s = score();
        if (s <= 300) throw new RuntimeException("Score baixo");
        else if (s <= 600) return 100;
        return 150;
    }

    private int score() {
        return new Random().nextInt(0, 100);
    }

}
