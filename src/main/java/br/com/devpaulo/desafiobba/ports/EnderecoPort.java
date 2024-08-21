package br.com.devpaulo.desafiobba.ports;

import br.com.devpaulo.desafiobba.infra.api.viacep.dto.EnderecoDto;

public interface EnderecoPort {
    EnderecoDto consultarenderecoPorCep(String cep);
}
