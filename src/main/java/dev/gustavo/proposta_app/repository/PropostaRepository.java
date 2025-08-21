package dev.gustavo.proposta_app.repository;

import dev.gustavo.proposta_app.entity.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {
}
