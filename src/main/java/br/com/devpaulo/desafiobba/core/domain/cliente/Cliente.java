package br.com.devpaulo.desafiobba.core.domain.cliente;

import br.com.devpaulo.desafiobba.core.domain.endereco.Endereco;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Cliente {
    private Long id;
    private String cpf;
    private String nome;
    private List<Endereco> endereco;

}
