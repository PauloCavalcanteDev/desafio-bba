package br.com.devpaulo.desafiobba.application;

import br.com.devpaulo.desafiobba.core.dto.EstadoDto;
import br.com.devpaulo.desafiobba.core.usecase.ConsultarEstadosUseCase;
import br.com.devpaulo.desafiobba.ports.EstadosMunicipiosPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ConsultarEstadosUseCaseImpl implements ConsultarEstadosUseCase {

    private final EstadosMunicipiosPort estadosMunicipiosPort;

    @Override
    public List<EstadoDto> execute() {
        return this.ordenarEstados(estadosMunicipiosPort.listarEstados());
    }

    private List<EstadoDto> ordenarEstados(List<EstadoDto> estados) {
        return estados.stream()
                .sorted((e1, e2) -> {
                    if (e1.estado().equals("São Paulo")) return -1;
                    if (e1.estado().equals("Rio de Janeiro")) return -1;
                    if (e2.estado().equals("São Paulo")) return 1;
                    if (e2.estado().equals("Rio de Janeiro")) return 1;
                    return e1.estado().compareTo(e2.estado());
                })
                .collect(Collectors.toList());
    }


}
