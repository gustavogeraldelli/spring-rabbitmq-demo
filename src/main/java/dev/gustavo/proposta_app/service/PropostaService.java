package dev.gustavo.proposta_app.service;

import dev.gustavo.proposta_app.dto.PropostaRequestDTO;
import dev.gustavo.proposta_app.dto.PropostaResponseDTO;
import dev.gustavo.proposta_app.mapper.PropostaMapper;
import dev.gustavo.proposta_app.repository.PropostaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PropostaService {

    private final PropostaRepository propostaRepository;

    public PropostaResponseDTO criar(PropostaRequestDTO propostaRequestDTO) {
        var p = propostaRepository.save(PropostaMapper.INSTANCE.toProposta(propostaRequestDTO));
        return PropostaMapper.INSTANCE.toPropostaResponseDTO(p);
    }

//    public PropostaResponseDTO buscarProposta(Long id) {
//        var p = propostaRepository.findById(id);
//        return null;
//    }

    public List<PropostaResponseDTO> listar() {
        return PropostaMapper.INSTANCE.toPropostaResponseDTOList(propostaRepository.findAll());
    }

}
