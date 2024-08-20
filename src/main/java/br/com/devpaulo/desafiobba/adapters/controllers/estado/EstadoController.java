package br.com.devpaulo.desafiobba.adapters.controllers.estado;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/estados")
@RequiredArgsConstructor
@Slf4j
public class EstadoController {

    @GetMapping
    public ResponseEntity<String> getEstados() {
        return ResponseEntity.ok("API Estados Running!");
    }

}

