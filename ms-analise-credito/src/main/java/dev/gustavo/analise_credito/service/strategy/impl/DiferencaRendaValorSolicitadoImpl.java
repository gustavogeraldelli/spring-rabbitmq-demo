package dev.gustavo.analise_credito.service.strategy.impl;

import dev.gustavo.analise_credito.domain.Proposta;
import dev.gustavo.analise_credito.service.strategy.CalculoPonto;
import org.springframework.stereotype.Component;

@Component
public class DiferencaRendaValorSolicitadoImpl implements CalculoPonto {

    @Override
    public int calcular(Proposta proposta) {
        return proposta.getUsuario().getRenda() > proposta.getValorSolicitado()
                ? 100 : 50;
    }

}
