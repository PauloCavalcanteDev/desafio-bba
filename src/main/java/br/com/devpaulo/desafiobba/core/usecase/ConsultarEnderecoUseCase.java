package br.com.devpaulo.desafiobba.core.usecase;

import br.com.devpaulo.desafiobba.core.exception.EnderecoNotFoundException;
import br.com.devpaulo.desafiobba.infra.api.viacep.dto.EnderecoDto;

public interface ConsultarEnderecoUseCase {

    EnderecoDto buscarEnderecoPorCep(String cep) throws EnderecoNotFoundException;
}
