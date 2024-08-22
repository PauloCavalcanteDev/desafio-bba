package br.com.devpaulo.desafiobba.core.usecase;

import br.com.devpaulo.desafiobba.core.domain.endereco.Endereco;
import br.com.devpaulo.desafiobba.core.exception.EnderecoNotFoundException;
import br.com.devpaulo.desafiobba.infra.api.viacep.dto.EnderecoDto;

import java.util.UUID;

public interface AlterarEnderecoUseCase {

    Endereco execute(String cpf, UUID enderecoId, EnderecoDto novoEndereco) throws EnderecoNotFoundException;
}
