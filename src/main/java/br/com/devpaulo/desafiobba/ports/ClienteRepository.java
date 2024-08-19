package br.com.devpaulo.desafiobba.ports;

import br.com.devpaulo.desafiobba.core.domain.cliente.Cliente;

public interface ClienteRepository {

    Cliente findByCpf(String cpf);
}
