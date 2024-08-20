package br.com.devpaulo.desafiobba.core.dto;

import br.com.devpaulo.desafiobba.adapters.api.viacep.dto.EnderecoDto;

import java.util.List;

public record ClienteDto(
        String cpf,
        String nome,
        List<EnderecoDto> endereco
) {
}
