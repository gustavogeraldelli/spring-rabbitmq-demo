package dev.gustavo.notificacao_ms.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Proposta {

    private Long id;
    private Double valorSolicitado;
    private Integer prazo;
    private Boolean aprovada;
    private Boolean integrada;
    private String observacao;
    private Usuario usuario;

}
