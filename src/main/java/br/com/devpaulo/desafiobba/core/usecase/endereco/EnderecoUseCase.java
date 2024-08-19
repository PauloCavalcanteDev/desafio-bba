package br.com.devpaulo.desafiobba.core.usecase.endereco;

import br.com.devpaulo.desafiobba.core.domain.endereco.Endereco;
import br.com.devpaulo.desafiobba.core.domain.estado.Estado;

import java.util.List;

public interface EnderecoUseCase {

    Endereco consultarEnderecoPorCep(String cep);

    List<Estado> consultarEstados();

    String consultarMunicipio(Estado estado);

}
