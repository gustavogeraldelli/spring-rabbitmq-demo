package dev.gustavo.proposta_app.dto;

public record PropostaResponseDTO(
        Long id,
        String nome,
        String sobrenome,
        String telefone,
        String cpf,
        Double renda,
        String valorSolicitadoFmt,
        int prazo,
        Boolean aprovada,
        String observacao
) {
}
