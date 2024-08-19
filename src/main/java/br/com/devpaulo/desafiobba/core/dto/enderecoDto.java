package br.com.devpaulo.desafiobba.core.dto;

public record enderecoDto(
        String cep,
        String logradouro,
        String complemento,
        String bairro,
        String localidade,
        String uf
) {
}
