package br.com.devpaulo.desafiobba.adapters.controllers.cliente;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/clients")
public class ClienteController {

    @GetMapping("/{cpfClient}")
    public ResponseEntity<String> realizarTransferencia(@PathVariable("cpfClient") String cpf) {
        return ResponseEntity.ok("CPF RECEBIDO -> " + cpf);

    }
}
