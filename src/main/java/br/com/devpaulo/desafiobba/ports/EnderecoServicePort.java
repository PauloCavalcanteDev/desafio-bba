package br.com.devpaulo.desafiobba.ports;

import br.com.devpaulo.desafiobba.adapters.api.viacep.dto.EnderecoDto;

public interface EnderecoServicePort {

    EnderecoDto consultarenderecoPorCep(String cep);

}
