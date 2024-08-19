package br.com.devpaulo.desafiobba.adapters.database;

import br.com.devpaulo.desafiobba.core.domain.cliente.Cliente;
import br.com.devpaulo.desafiobba.ports.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ClienteRepositoryImpl implements ClienteRepository {

    public Cliente findByCpf(String cpf) {
        return null;
    }
}
