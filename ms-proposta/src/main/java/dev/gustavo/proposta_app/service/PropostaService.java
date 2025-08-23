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
    private final RabbitMQService rabbitMQService;

    public PropostaResponseDTO criar(PropostaRequestDTO propostaRequestDTO) {
        var p = propostaRepository.save(PropostaMapper.INSTANCE.toProposta(propostaRequestDTO));

        notificarRabbitMQ(p);

        return PropostaMapper.INSTANCE.toPropostaResponseDTO(p);
    }

    private void notificarRabbitMQ(Proposta proposta) {
        try {
            rabbitMQService.notificar(proposta, "proposta-pendente.ex");
        }
        catch (RuntimeException e) {
            // flag para indicar se foi enviado ao rabbitmq ou nao
            // pode ser feito depois um procedimento de tempos em tempos
            // para recuperar os registros que nao foram enviados
            // para que sejam enviados para o processamento
            proposta.setIntegrada(false);
            propostaRepository.save(proposta);
        }
    }

//    public PropostaResponseDTO buscarProposta(Long id) {
//        var p = propostaRepository.findById(id);
//        return null;
//    }

    public List<PropostaResponseDTO> listar() {
        return PropostaMapper.INSTANCE.toPropostaResponseDTOList(propostaRepository.findAll());
    }

}
