package br.com.devpaulo.desafiobba.adapters.controllers.cliente;

import br.com.devpaulo.desafiobba.core.domain.cliente.Cliente;
import br.com.devpaulo.desafiobba.core.exception.ClienteNotFoundException;
import br.com.devpaulo.desafiobba.core.exception.CpfInvalidoException;
import br.com.devpaulo.desafiobba.core.usecase.cliente.ClienteUseCase;
import lombok.RequiredArgsConstructor;
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
@Slf4j
public class ClienteController {

    private final ClienteUseCase clienteUseCase;

    @GetMapping("/{cpfClient}")
    public ResponseEntity<?> getClientes(@PathVariable("cpfClient") String cpf) {
        log.info("Iniciando Colsulta Dados cliente  Cpf ->   {} !", cpf);
        try {
            Cliente cliente = clienteUseCase.consultarDadosCliente(cpf);
            log.info("Cliente Encontrado: {}", cliente.toString());
            return ResponseEntity.ok(cliente);
        } catch (ClienteNotFoundException e) {
            log.error("Cliente Não Encontrado!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(String.format("Cliente não encontrado para o CPF: %s", cpf));
        } catch (CpfInvalidoException ex) {
            log.error("CPF Inválido");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ex.getMessage());
        }
    }

}