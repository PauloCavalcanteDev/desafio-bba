package br.com.devpaulo.desafiobba.ports;

import br.com.devpaulo.desafiobba.core.domain.endereco.Endereco;
import br.com.devpaulo.desafiobba.core.exception.EnderecoNotFoundException;
import br.com.devpaulo.desafiobba.infra.api.viacep.dto.EnderecoDto;

import java.util.UUID;

public interface EnderecoPort {
    EnderecoDto consultarenderecoPorCep(String cep);

    Endereco atulizarEndereco(UUID enderecoId, EnderecoDto novoEndereco) throws EnderecoNotFoundException;
}
