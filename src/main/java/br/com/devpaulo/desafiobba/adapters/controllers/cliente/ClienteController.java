package br.com.devpaulo.desafiobba.adapters.controllers.cliente;

import br.com.devpaulo.desafiobba.core.usecase.ConsultarClienteUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/clientes")
@RequiredArgsConstructor
@Slf4j
public class ClienteController {

    private final ConsultarClienteUseCase clienteUseCase;

    @GetMapping("/{cpf}")
    public ResponseEntity<?> getClientes(@PathVariable("cpf")  String cpf) {
        log.info("Iniciando Colsulta Dados cliente  Cpf ->   {} !", cpf);
        return ResponseEntity.ok(clienteUseCase.consultarClientePorCpf(cpf));
    }

}