package dev.gustavo.proposta_app.dto;

public record PropostaRequestDTO(
        String nome,
        String sobrenome,
        String telefone,
        String cpf,
        Double renda,
        Double valorSolicitado,
        int prazo
) {
}
