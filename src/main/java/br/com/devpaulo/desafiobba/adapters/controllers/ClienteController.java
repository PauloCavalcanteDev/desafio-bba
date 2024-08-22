package br.com.devpaulo.desafiobba.adapters.controllers;

import br.com.devpaulo.desafiobba.core.exception.ClienteNotFoundException;
import br.com.devpaulo.desafiobba.core.exception.EnderecoNotFoundException;
import br.com.devpaulo.desafiobba.core.usecase.AlterarEnderecoUseCase;
import br.com.devpaulo.desafiobba.core.usecase.ConsultarClienteUseCase;
import br.com.devpaulo.desafiobba.infra.api.viacep.dto.EnderecoDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/clientes")
@RequiredArgsConstructor
@Log4j2
public class ClienteController {

    private final ConsultarClienteUseCase clienteUseCase;
    private final AlterarEnderecoUseCase alterarEnderecoUseCase;

    @GetMapping("/{cpf}")
    public ResponseEntity<?> getClientes(@PathVariable("cpf") @Valid @Size(min = 11, max = 11) String cpf) {

        log.info("Iniciando Busca de dados do cliente Cpf -> {} !", cpf);
        try {
            return ResponseEntity.ok(clienteUseCase.consultarClientePorCpf(cpf));
        } catch (ClienteNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente Não Encontrado.");
        }

    }

    @PutMapping("/{cpf}/enderecos/{enderecoId}")
    public ResponseEntity<?> atualizarEndereco(@PathVariable @Valid @Size(min = 11, max = 11) String cpf,
                                               @PathVariable UUID enderecoId,
                                               @RequestBody @Valid EnderecoDto novoEndereco) {

        log.info("Novo Endereco Recebido: {} - Cliente {}", novoEndereco, cpf);
        try {
            alterarEnderecoUseCase.execute(cpf, enderecoId, novoEndereco);
            return ResponseEntity.noContent().build();

        } catch (EnderecoNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereço Não Encontrado.");
        } catch (ClienteNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente Não Encontrado.");

        }
    }

}