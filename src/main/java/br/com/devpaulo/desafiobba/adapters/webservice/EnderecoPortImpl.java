package br.com.devpaulo.desafiobba.adapters.webservice;

import br.com.devpaulo.desafiobba.infra.api.viacep.client.ViacepClient;
import br.com.devpaulo.desafiobba.infra.api.viacep.dto.EnderecoDto;
import br.com.devpaulo.desafiobba.ports.EnderecoPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class EnderecoPortImpl implements EnderecoPort {

    private final ViacepClient client;

    @Override
    public EnderecoDto consultarenderecoPorCep(String cep) {
        log.info("Iniciando integragação Via Cep , Buscando cep: {}.", cep);
        return client.consultarenderecoPorCep(cep);

    }
}
