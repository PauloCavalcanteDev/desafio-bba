package br.com.devpaulo.desafiobba.core.usecase.cliente;

import br.com.devpaulo.desafiobba.core.domain.cliente.Cliente;
import br.com.devpaulo.desafiobba.core.exception.ClienteNotFoundException;
import br.com.devpaulo.desafiobba.core.exception.CpfInvalidoException;

public interface ClienteUseCase {

    Cliente consultarDadosCliente(String cpf) throws ClienteNotFoundException, CpfInvalidoException;

}
