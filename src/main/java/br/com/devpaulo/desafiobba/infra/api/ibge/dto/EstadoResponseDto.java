package br.com.devpaulo.desafiobba.infra.api.ibge.dto;

public record EstadoResponseDto(
        Integer id,
        String sigla,
        String nome,
        RegiaoDto regiao
) {
}
