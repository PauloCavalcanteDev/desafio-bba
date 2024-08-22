package br.com.devpaulo.desafiobba.adapters.ports.impl;

import br.com.devpaulo.desafiobba.core.domain.cliente.Cliente;
import br.com.devpaulo.desafiobba.infra.database.ClienteRepository;
import br.com.devpaulo.desafiobba.adapters.ports.ConsultarClientPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class ConsultarClientPortImpl implements ConsultarClientPort {

    private final ClienteRepository repository;

    @Override
    public Optional<Cliente> buscarClientePorCpf(String cpf) {
        log.info("Iniciando Busca na base de dados!");
        var cliente = repository.findById(cpf);
        log.info("Busca Finalizada.");
        return cliente;
    }
}
