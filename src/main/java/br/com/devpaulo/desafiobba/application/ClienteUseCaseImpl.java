package br.com.devpaulo.desafiobba.application;

import br.com.devpaulo.desafiobba.core.domain.cliente.Cliente;
import br.com.devpaulo.desafiobba.core.usecase.cliente.ClienteUseCase;
import br.com.devpaulo.desafiobba.ports.ClienteRepository;
import org.springframework.stereotype.Service;

@Service
public class ClienteUseCaseImpl implements ClienteUseCase {

    private final ClienteRepository repository;

    public ClienteUseCaseImpl(ClienteRepository repository) {
        this.repository = repository;
    }

    @Override
    public Cliente consultarCliente(String cpf) {
        return repository.findByCpf(cpf);
    }
}
