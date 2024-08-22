package br.com.devpaulo.desafiobba.core.usecase;

import br.com.devpaulo.desafiobba.core.dto.MunicipioDto;

import java.util.List;

public interface ConsultarMunicipiosUseCase {

    List<MunicipioDto> execute(String uf);
}
