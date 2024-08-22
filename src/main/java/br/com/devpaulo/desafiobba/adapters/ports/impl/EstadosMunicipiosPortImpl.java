package br.com.devpaulo.desafiobba.adapters.ports.impl;

import br.com.devpaulo.desafiobba.adapters.ports.EstadosMunicipiosPort;
import br.com.devpaulo.desafiobba.core.dto.EstadoDto;
import br.com.devpaulo.desafiobba.core.dto.MunicipioDto;
import br.com.devpaulo.desafiobba.core.exception.IbgeClientException;
import br.com.devpaulo.desafiobba.infra.api.ibge.client.IbgeClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class EstadosMunicipiosPortImpl implements EstadosMunicipiosPort {

    private final IbgeClient client;


    @Override
    public List<EstadoDto> listarEstados() throws IbgeClientException {
        try {
            log.info("Inciando Integração com API do IBGE.");
            var estados = client.consultaEstadosBrasileiros();

            return estados.stream()
                    .map(estado -> new EstadoDto(estado.nome(), estado.sigla()))
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            log.error("Falha Integração com API do IBGE.");
            throw new IbgeClientException("Falha ao se comunicar com a API-> IBGE");
        }

    }

    @Override
    public List<MunicipioDto> listarMunicipios(String uf) {
        return client.getMunicipios(uf);
    }
}
