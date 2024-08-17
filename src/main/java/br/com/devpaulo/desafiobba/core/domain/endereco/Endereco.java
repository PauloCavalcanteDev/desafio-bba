package br.com.devpaulo.desafiobba.core.domain.endereco;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class Endereco {

    private long Id;
    private String logadouro;
}
