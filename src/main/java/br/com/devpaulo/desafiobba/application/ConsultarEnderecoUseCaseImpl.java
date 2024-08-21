package br.com.devpaulo.desafiobba.application;

import br.com.devpaulo.desafiobba.core.exception.EnderecoNotFoundException;
import br.com.devpaulo.desafiobba.core.usecase.ConsultarEnderecoUseCase;
import br.com.devpaulo.desafiobba.infra.api.viacep.dto.EnderecoDto;
import br.com.devpaulo.desafiobba.ports.EnderecoPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Log4j2
public class ConsultarEnderecoUseCaseImpl implements ConsultarEnderecoUseCase {

    private final EnderecoPort port;

    @Override
    public EnderecoDto buscarEnderecoPorCep(String cep) throws EnderecoNotFoundException {
        EnderecoDto endereco = port.consultarenderecoPorCep(cep);
        if (endereco.cep() != null) {
            log.info("Endereço Recuperado com Sucesso -> {}.", endereco);
            return endereco;
        }
        log.error("Endereço Não Encontrado,para o Cep: {},", cep);
        throw new EnderecoNotFoundException("Endereco Não Encontrado, verifique o Cep Digitado");
    }
}
