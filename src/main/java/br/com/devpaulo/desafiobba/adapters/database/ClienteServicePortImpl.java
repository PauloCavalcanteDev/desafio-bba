package br.com.devpaulo.desafiobba.adapters.database;

import br.com.devpaulo.desafiobba.core.domain.cliente.Cliente;
import br.com.devpaulo.desafiobba.core.exception.ClienteNotFoundException;
import br.com.devpaulo.desafiobba.infra.database.ClienteRepository;
import br.com.devpaulo.desafiobba.ports.ClienteServicePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class ClienteServicePortImpl implements ClienteServicePort {

    private final ClienteRepository repository;

    @Override
    public Cliente findByCpf(String cpf) throws ClienteNotFoundException {
        log.info("Iniciando Busca na Base de dados.");
        var cliente = repository.findById(cpf);
        return cliente
                .orElseThrow(() -> new ClienteNotFoundException("Cliente Não Encontrado"));
    }
}
