package br.com.devpaulo.desafiobba.infra.api.viacep.dto;

public record EnderecoDto(
        String cep,
        String logradouro,
        String complemento,
        String bairro,
        String localidade,
        String uf
) {
}
