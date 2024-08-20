package br.com.devpaulo.desafiobba.core.usecase;

import br.com.devpaulo.desafiobba.core.dto.ClienteDto;

public interface ConsultarClienteUseCase {

    ClienteDto consultarClientePorCpf(String Cpf);
}
