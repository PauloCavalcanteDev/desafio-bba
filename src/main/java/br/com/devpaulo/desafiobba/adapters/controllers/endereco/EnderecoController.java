package br.com.devpaulo.desafiobba.adapters.controllers.endereco;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/enderecos")
public class EnderecoController {
    @GetMapping("/{cep}")
    public ResponseEntity<String> realizarTransferencia(@PathVariable("cep") String cpf) {
        return ResponseEntity.ok("Controller Endereco is running!");

    }

}
