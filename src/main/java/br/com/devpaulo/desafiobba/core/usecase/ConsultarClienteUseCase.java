package br.com.devpaulo.desafiobba.core.usecase;

import br.com.devpaulo.desafiobba.core.dto.ClienteDto;
import br.com.devpaulo.desafiobba.core.exception.ClienteNotFoundException;

public interface ConsultarClienteUseCase {

    ClienteDto consultarClientePorCpf(String Cpf) throws ClienteNotFoundException;
}
