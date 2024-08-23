package br.com.devpaulo.desafiobba.application;

import br.com.devpaulo.desafiobba.adapters.ports.EstadosMunicipiosPort;
import br.com.devpaulo.desafiobba.core.dto.EstadoDto;
import br.com.devpaulo.desafiobba.core.exception.IbgeClientException;
import br.com.devpaulo.desafiobba.core.usecase.ConsultarEstadosUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Log4j2
public class ConsultarEstadosUseCaseImpl implements ConsultarEstadosUseCase {

    private final EstadosMunicipiosPort estadosMunicipiosPort;

    @Override
    public List<EstadoDto> execute() throws IbgeClientException {

        var estados = estadosMunicipiosPort.listarEstados();
        log.info("Lista de estados recuperada com sucesso!");
        return this.ordenarEstados(estados);
    }

    public List<EstadoDto> ordenarEstados(List<EstadoDto> estados) {
        log.info("Retornando Lista de Estados Ordenada!");

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
