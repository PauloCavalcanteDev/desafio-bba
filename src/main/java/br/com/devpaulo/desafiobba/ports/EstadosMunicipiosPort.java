package br.com.devpaulo.desafiobba.ports;

import br.com.devpaulo.desafiobba.core.dto.EstadoDto;

import java.util.List;

public interface EstadosMunicipiosPort {


    List<EstadoDto> listarEstados();
}
