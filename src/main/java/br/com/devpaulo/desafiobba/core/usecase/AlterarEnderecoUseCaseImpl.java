package br.com.devpaulo.desafiobba.core.usecase;

import br.com.devpaulo.desafiobba.adapters.ports.ConsultarClientPort;
import br.com.devpaulo.desafiobba.adapters.ports.EnderecoPort;
import br.com.devpaulo.desafiobba.core.domain.endereco.Endereco;
import br.com.devpaulo.desafiobba.core.exception.ClienteNotFoundException;
import br.com.devpaulo.desafiobba.core.exception.EnderecoNotFoundException;
import br.com.devpaulo.desafiobba.infra.api.viacep.dto.EnderecoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
@Log4j2
public class AlterarEnderecoUseCaseImpl implements AlterarEnderecoUseCase {

    private final EnderecoPort port;
    private final ConsultarClientPort clientPort;


    @Override
    public Endereco execute(String cpf, UUID enderecoId, EnderecoDto novoEndereco)
            throws EnderecoNotFoundException, ClienteNotFoundException {

        if (clientPort.buscarClientePorCpf(cpf).isPresent()) {
            log.info("Iniciando Atualização de  Endereço do cliente id -> {}", cpf);
            return port.atulizarEndereco(enderecoId, novoEndereco);
        }
        throw new ClienteNotFoundException(String.format("cpf : %s -> Não existe na base de Dados.", cpf));


    }
}
