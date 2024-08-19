package br.com.devpaulo.desafiobba.application;

import br.com.devpaulo.desafiobba.core.domain.endereco.Endereco;
import br.com.devpaulo.desafiobba.core.domain.estado.Estado;
import br.com.devpaulo.desafiobba.core.usecase.endereco.EnderecoUseCase;

import java.util.List;

public class EnderecoUseCaseImpl implements EnderecoUseCase {

    //TODO Inclui as dependencias do mundo externo que fica em Ports
    @Override
    public Endereco consultarEnderecoPorCep(String cep) {
        return null;
    }

    @Override
    public List<Estado> consultarEstados() {
        return List.of();
    }

    @Override
    public String consultarMunicipio(Estado estado) {
        return "";
    }
}
