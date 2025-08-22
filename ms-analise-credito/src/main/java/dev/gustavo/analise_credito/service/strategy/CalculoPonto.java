package dev.gustavo.analise_credito.service.strategy;

import dev.gustavo.analise_credito.domain.Proposta;

public interface CalculoPonto {
    int calcular(Proposta proposta);
}
