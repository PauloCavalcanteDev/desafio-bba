package br.com.devpaulo.desafiobba.core.usecase.cliente;

import br.com.devpaulo.desafiobba.core.domain.cliente.Cliente;

public interface ClienteUseCase {
    Cliente consultarClinte(String cpf) ;
}
