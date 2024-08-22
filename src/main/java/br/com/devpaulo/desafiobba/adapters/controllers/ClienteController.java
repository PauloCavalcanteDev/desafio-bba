package br.com.devpaulo.desafiobba.adapters.controllers;

import br.com.devpaulo.desafiobba.core.domain.endereco.Endereco;
import br.com.devpaulo.desafiobba.core.exception.ClienteNotFoundException;
import br.com.devpaulo.desafiobba.core.exception.EnderecoNotFoundException;
import br.com.devpaulo.desafiobba.core.usecase.AlterarEnderecoUseCase;
import br.com.devpaulo.desafiobba.core.usecase.ConsultarClienteUseCase;
import br.com.devpaulo.desafiobba.infra.api.viacep.dto.EnderecoDto;
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
    public ResponseEntity<?> getClientes(@PathVariable("cpf") String cpf) {
        log.info("Iniciando Busca de dados do cliente Cpf -> {} !", cpf);
        try {
            return ResponseEntity.ok(clienteUseCase.consultarClientePorCpf(cpf));
        } catch (ClienteNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente Não Encontrado.");
        }

    }

    @PutMapping("/{cpf}/enderecos/{enderecoId}")
    public ResponseEntity<Void> atualizarEndereco(
            @PathVariable String cpf,
            @PathVariable UUID enderecoId,
            @RequestBody EnderecoDto novoEndereco) {

        Endereco enderecoAtualizado = null;
        try {
            enderecoAtualizado = alterarEnderecoUseCase.execute(cpf, enderecoId, novoEndereco);
        } catch (EnderecoNotFoundException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.noContent().build();
    }

}