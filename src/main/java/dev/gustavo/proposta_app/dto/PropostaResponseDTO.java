package dev.gustavo.proposta_app.dto;

public record PropostaResponseDTO(
        Long id,
        String nome,
        String sobrenome,
        String telefone,
        String cpf,
        Double renda,
        Double valorSolicitado,
        int prazo,
        Boolean aprovado,
        String observacao
) {
}
