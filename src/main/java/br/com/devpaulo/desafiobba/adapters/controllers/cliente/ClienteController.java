package br.com.devpaulo.desafiobba.adapters.controllers.cliente;

import br.com.devpaulo.desafiobba.core.exception.ClienteNotFoundException;
import br.com.devpaulo.desafiobba.core.usecase.ConsultarClienteUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/clientes")
@RequiredArgsConstructor
@Log4j2
public class ClienteController {

    private final ConsultarClienteUseCase clienteUseCase;

    @GetMapping("/{cpf}")
    public ResponseEntity<?> getClientes(@PathVariable("cpf") String cpf) {
        log.info("Iniciando Busca de dados do cliente Cpf -> {} !", cpf);
        try {
            return ResponseEntity.ok(clienteUseCase.consultarClientePorCpf(cpf));
        } catch (ClienteNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente Não Encontrado.");
        }

    }

}