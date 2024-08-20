package br.com.devpaulo.desafiobba.adapters.dataservice;

import br.com.devpaulo.desafiobba.core.domain.cliente.Cliente;
import br.com.devpaulo.desafiobba.infra.database.ClienteRepository;
import br.com.devpaulo.desafiobba.ports.ConsultarClientPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConsultarClientPortImpl implements ConsultarClientPort {

    private final ClienteRepository repository;

    @Override
    public Optional<Cliente> buscarClientePorCpf(String cpf) {
        return repository.findById(cpf);
    }
}
