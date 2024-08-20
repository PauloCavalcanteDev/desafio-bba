package br.com.devpaulo.desafiobba.ports;

import br.com.devpaulo.desafiobba.core.domain.cliente.Cliente;
import br.com.devpaulo.desafiobba.core.exception.ClienteNotFoundException;

import java.util.Optional;

public interface ClienteServicePort {

    Cliente findByCpf(String cpf) throws ClienteNotFoundException;
}
