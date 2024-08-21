package br.com.devpaulo.desafiobba.infra.api.ibge.dto;

public record EstadoDto(
        Integer id,
        String sigla,
        String nome,
        RegiaoDto regiao
) {
}
