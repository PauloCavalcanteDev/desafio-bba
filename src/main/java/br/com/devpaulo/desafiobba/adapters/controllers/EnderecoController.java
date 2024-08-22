package br.com.devpaulo.desafiobba.adapters.controllers;

import br.com.devpaulo.desafiobba.core.exception.EnderecoNotFoundException;
import br.com.devpaulo.desafiobba.core.usecase.ConsultarEnderecoUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/enderecos")
@RequiredArgsConstructor
@Log4j2
public class EnderecoController {

    private final ConsultarEnderecoUseCase consultarEnderecoUseCase;


    @GetMapping("/{cep}")
    public ResponseEntity<?> getEndereco(@PathVariable("cep") String cep) {
        log.info("Iniciando Busca de Endereco pelo cep -> {} !", cep);
        try {
            return ResponseEntity.ok(consultarEnderecoUseCase.buscarEnderecoPorCep(cep));
        } catch (EnderecoNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }


}
