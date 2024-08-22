package br.com.devpaulo.desafiobba.adapters.ports;

import br.com.devpaulo.desafiobba.core.domain.cliente.Cliente;

import java.util.Optional;

public interface ConsultarClientPort {

    Optional<Cliente> buscarClientePorCpf(String cpf);
}
