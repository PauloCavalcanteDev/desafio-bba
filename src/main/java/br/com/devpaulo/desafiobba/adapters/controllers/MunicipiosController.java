package br.com.devpaulo.desafiobba.adapters.controllers;


import br.com.devpaulo.desafiobba.core.dto.MunicipioDto;
import br.com.devpaulo.desafiobba.core.usecase.ConsultarMunicipiosUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/municipios")
@RequiredArgsConstructor
public class MunicipiosController {

    private final ConsultarMunicipiosUseCase useCase;

    @GetMapping("/{estado}")
    public ResponseEntity<List<MunicipioDto>> getMunicipios(@PathVariable("estado") String estado) {
        return ResponseEntity.ok(useCase.execute(estado));
    }
}
