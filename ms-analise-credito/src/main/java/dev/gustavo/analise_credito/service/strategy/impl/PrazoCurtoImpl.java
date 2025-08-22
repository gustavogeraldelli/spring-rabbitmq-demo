package dev.gustavo.analise_credito.service.strategy.impl;

import dev.gustavo.analise_credito.domain.Proposta;
import dev.gustavo.analise_credito.service.strategy.CalculoPonto;
import org.springframework.stereotype.Component;

@Component
public class PrazoCurtoImpl implements CalculoPonto {

    @Override
    public int calcular(Proposta proposta) {
        return proposta.getPrazo() <= 6 ? 0 : 50 ;
    }

}
