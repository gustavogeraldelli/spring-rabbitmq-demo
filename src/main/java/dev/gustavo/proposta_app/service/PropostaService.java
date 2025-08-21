package dev.gustavo.proposta_app.service;

import dev.gustavo.proposta_app.dto.PropostaRequestDTO;
import dev.gustavo.proposta_app.dto.PropostaResponseDTO;
import dev.gustavo.proposta_app.entity.Proposta;
import dev.gustavo.proposta_app.mapper.PropostaMapper;
import dev.gustavo.proposta_app.repository.PropostaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PropostaService {

    private final PropostaRepository propostaRepository;
    private final NotificationService notificationService;

    public PropostaResponseDTO criar(PropostaRequestDTO propostaRequestDTO) {
        var p = propostaRepository.save(PropostaMapper.INSTANCE.toProposta(propostaRequestDTO));

        notificarRabbitMQ(p);

        return PropostaMapper.INSTANCE.toPropostaResponseDTO(p);
    }

    private void notificarRabbitMQ(Proposta proposta) {
        notificationService.notificar(proposta, "proposta-pendente.ex");
    }

//    public PropostaResponseDTO buscarProposta(Long id) {
//        var p = propostaRepository.findById(id);
//        return null;
//    }

    public List<PropostaResponseDTO> listar() {
        return PropostaMapper.INSTANCE.toPropostaResponseDTOList(propostaRepository.findAll());
    }

}
