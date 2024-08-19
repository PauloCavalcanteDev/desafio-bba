package br.com.devpaulo.desafiobba.adapters.controllers.cliente;

import br.com.devpaulo.desafiobba.core.domain.cliente.Cliente;
import br.com.devpaulo.desafiobba.core.usecase.cliente.ClienteUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/clients")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteUseCase clienteUseCase;

    @GetMapping("/{cpfClient}")
    public ResponseEntity<String> realizarTransferencia(@PathVariable("cpfClient") String cpf) {
        return ResponseEntity.ok("Controller Cliente is runnng!");

    }
}
