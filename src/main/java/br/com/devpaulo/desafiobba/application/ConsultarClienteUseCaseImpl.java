package br.com.devpaulo.desafiobba.application;

import br.com.devpaulo.desafiobba.adapters.api.viacep.dto.EnderecoDto;
import br.com.devpaulo.desafiobba.core.dto.ClienteDto;
import br.com.devpaulo.desafiobba.core.exception.ClienteNotFoundException;
import br.com.devpaulo.desafiobba.core.usecase.ConsultarClienteUseCase;
import br.com.devpaulo.desafiobba.ports.ConsultarClientPort;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ConsultarClienteUseCaseImpl implements ConsultarClienteUseCase {

    private static final Logger log = LoggerFactory.getLogger(ConsultarClienteUseCaseImpl.class);
    private final ConsultarClientPort clientPort;

    @Override
    public ClienteDto consultarClientePorCpf(String cpf) throws ClienteNotFoundException {
        var dadosCliente = clientPort.buscarClientePorCpf(cpf);
        if (dadosCliente.isPresent()) {
            log.info("Dados Clientes Recuperados com Sucesso.");

            return dadosCliente
                    .map(data -> new ClienteDto(data.getCpf(), data.getNome(),
                            data.getEnderecos().stream()
                                    .map(endereco -> new EnderecoDto(
                                            endereco.getCep(),
                                            endereco.getLogradouro(),
                                            endereco.getComplemento(),
                                            endereco.getBairro(),
                                            endereco.getLocalidade(),
                                            endereco.getUf()))
                                    .collect(Collectors.toList())
                    )).get();
        } else {
            log.error("CPF '{}', não cadastrado", cpf);
            throw new ClienteNotFoundException("Cliente Não Encontrado.");
        }
    }
}
