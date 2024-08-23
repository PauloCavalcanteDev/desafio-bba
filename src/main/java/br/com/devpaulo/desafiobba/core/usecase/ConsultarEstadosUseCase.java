package br.com.devpaulo.desafiobba.core.usecase;

import br.com.devpaulo.desafiobba.core.dto.EstadoDto;
import br.com.devpaulo.desafiobba.core.exception.IbgeClientException;

import java.util.List;

public interface ConsultarEstadosUseCase {

    List<EstadoDto> execute() throws IbgeClientException;
}
