package br.com.devpaulo.desafiobba.adapters.ports;

import br.com.devpaulo.desafiobba.core.dto.EstadoDto;
import br.com.devpaulo.desafiobba.core.dto.MunicipioDto;

import java.util.List;

public interface EstadosMunicipiosPort {


    List<EstadoDto> listarEstados();
    List<MunicipioDto> listarMunicipios(String uf);
}
