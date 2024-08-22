package br.com.devpaulo.desafiobba.application;

import br.com.devpaulo.desafiobba.core.dto.MunicipioDto;
import br.com.devpaulo.desafiobba.core.usecase.ConsultarMunicipiosUseCase;
import br.com.devpaulo.desafiobba.ports.EstadosMunicipiosPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ConsultarMunicipiosUseCaseImpl implements ConsultarMunicipiosUseCase {

    private final EstadosMunicipiosPort estadosMunicipiosPort;

    @Override
    public List<MunicipioDto> execute(String uf) {
        return estadosMunicipiosPort.listarMunicipios(uf);
    }
}
