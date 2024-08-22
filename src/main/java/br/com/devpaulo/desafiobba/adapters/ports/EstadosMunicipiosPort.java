package br.com.devpaulo.desafiobba.adapters.ports;

import br.com.devpaulo.desafiobba.core.dto.EstadoDto;
import br.com.devpaulo.desafiobba.core.dto.MunicipioDto;
import br.com.devpaulo.desafiobba.core.exception.IbgeClientException;

import java.util.List;

public interface EstadosMunicipiosPort {


    List<EstadoDto> listarEstados() throws IbgeClientException;
    List<MunicipioDto> listarMunicipios(String uf);
}
