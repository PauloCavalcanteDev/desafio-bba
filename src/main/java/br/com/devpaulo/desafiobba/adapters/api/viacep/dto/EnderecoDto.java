package br.com.devpaulo.desafiobba.adapters.api.viacep.dto;

public record EnderecoDto(
        String cep,
        String logradouro,
        String complementoString,
        String bairro,
        String localidade,
        String uf
) {
}
