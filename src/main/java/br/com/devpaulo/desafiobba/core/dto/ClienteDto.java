package br.com.devpaulo.desafiobba.core.dto;

import br.com.devpaulo.desafiobba.core.domain.endereco.Endereco;

import java.util.List;

public record ClienteDto(
        String cpf,
        String nome,
        List<Endereco> endereco
) {
}
