package dev.gustavo.proposta_app.controller;

import dev.gustavo.proposta_app.dto.PropostaRequestDTO;
import dev.gustavo.proposta_app.dto.PropostaResponseDTO;
import dev.gustavo.proposta_app.service.PropostaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/proposta")
public class PropostaController {

    private final PropostaService propostaService;

    @PostMapping
    public ResponseEntity<PropostaResponseDTO> criar(@RequestBody PropostaRequestDTO propostaRequestDTO) {
        //return ResponseEntity.status(HttpStatus.CREATED).body(propostaService.criar(propostaRequestDTO));
        var response = propostaService.criar(propostaRequestDTO);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}").buildAndExpand(response.id()).toUri()) // Location header
                .body(response);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<PropostaResponseDTO> buscar(@PathVariable Long id) {
//        return ResponseEntity.ok(propostaService.buscarProposta(id));
//    }

    @GetMapping
    public ResponseEntity<List<PropostaResponseDTO>> listar() {
        return ResponseEntity.ok(propostaService.listar());
    }

}
