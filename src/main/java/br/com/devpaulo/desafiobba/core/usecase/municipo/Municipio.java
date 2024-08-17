package br.com.devpaulo.desafiobba.core.usecase.municipo;

import br.com.devpaulo.desafiobba.core.domain.estado.Estado;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Municipio {
    private String nomeMunicipio;
    private Estado estado;
}
