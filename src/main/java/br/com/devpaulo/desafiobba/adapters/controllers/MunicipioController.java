package br.com.devpaulo.desafiobba.adapters.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/municipios")
public class MunicipioController {

    @GetMapping("/{estado}")
    public ResponseEntity<String> getMunicipios(@PathVariable("estado") String estado) {
        return ResponseEntity.ok("API Municipios  Running! " + estado);
    }
}
