package br.com.devpaulo.desafiobba.core.usecase;

import br.com.devpaulo.desafiobba.core.domain.endereco.Endereco;
import br.com.devpaulo.desafiobba.core.exception.EnderecoNotFoundException;
import br.com.devpaulo.desafiobba.infra.api.viacep.dto.EnderecoDto;
import br.com.devpaulo.desafiobba.adapters.ports.EnderecoPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class AlterarEnderecoUseCaseImpl implements AlterarEnderecoUseCase {

    private final EnderecoPort port;

    @Override
    public Endereco execute(String cpf, UUID enderecoId, EnderecoDto novoEndereco) throws EnderecoNotFoundException {
        //TODO implantar Lógica para gararntir que cliente passado existe
        return port.atulizarEndereco(enderecoId,novoEndereco);
    }
}
