package br.com.devpaulo.desafiobba.application;

import br.com.devpaulo.desafiobba.adapters.ports.EstadosMunicipiosPort;
import br.com.devpaulo.desafiobba.core.dto.MunicipioDto;
import br.com.devpaulo.desafiobba.core.exception.IbgeClientException;
import br.com.devpaulo.desafiobba.core.usecase.ConsultarMunicipiosUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Log4j2
public class ConsultarMunicipiosUseCaseImpl implements ConsultarMunicipiosUseCase {

    private final EstadosMunicipiosPort estadosMunicipiosPort;

    @Override
    public List<MunicipioDto> execute(String uf) throws IbgeClientException {
        var municipios = estadosMunicipiosPort.listarMunicipios(uf);
        log.info("Lista de municipios recuperada com sucesso!");
        return municipios;
    }
}
