package br.com.devpaulo.desafiobba.adapters.webservice;

import br.com.devpaulo.desafiobba.core.dto.EstadoDto;
import br.com.devpaulo.desafiobba.core.dto.MunicipioDto;
import br.com.devpaulo.desafiobba.infra.api.ibge.client.IbgeClient;
import br.com.devpaulo.desafiobba.ports.EstadosMunicipiosPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EstadosMunicipiosPortImpl implements EstadosMunicipiosPort {

    private final IbgeClient client;


    @Override
    public List<EstadoDto> listarEstados() {
        var estados = client.consultaEstadosBrasileiros();

        return estados.stream()
                .map(estado -> new EstadoDto(estado.nome(), estado.sigla()))
                .collect(Collectors.toList());
    }

    @Override
    public List<MunicipioDto> listarMunicipios(String uf) {
        return client.getMunicipios(uf);
    }
}
