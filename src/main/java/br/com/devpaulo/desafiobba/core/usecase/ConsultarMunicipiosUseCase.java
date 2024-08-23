package br.com.devpaulo.desafiobba.core.usecase;

import br.com.devpaulo.desafiobba.core.dto.MunicipioDto;
import br.com.devpaulo.desafiobba.core.exception.IbgeClientException;

import java.util.List;

public interface ConsultarMunicipiosUseCase {

    List<MunicipioDto> execute(String uf) throws IbgeClientException;
}
