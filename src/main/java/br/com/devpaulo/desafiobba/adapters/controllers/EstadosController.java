package br.com.devpaulo.desafiobba.adapters.controllers;

import br.com.devpaulo.desafiobba.core.dto.EstadoDto;
import br.com.devpaulo.desafiobba.core.exception.IbgeClientException;
import br.com.devpaulo.desafiobba.core.usecase.ConsultarEstadosUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/v1/estados")
@RequiredArgsConstructor
@Slf4j
public class EstadosController {

    private final ConsultarEstadosUseCase consultarEstadosUseCase;

    @GetMapping
    public ResponseEntity<List<EstadoDto>> getEstados() {
        log.info("Listando Estados Brasileiros!");
        try {
            return ResponseEntity.ok(consultarEstadosUseCase.execute());
        } catch (IbgeClientException ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

}

