package br.com.devpaulo.desafiobba.application;

import br.com.devpaulo.desafiobba.adapters.api.viacep.dto.EnderecoDto;
import br.com.devpaulo.desafiobba.core.dto.ClienteDto;
import br.com.devpaulo.desafiobba.core.usecase.ConsultarClienteUseCase;
import br.com.devpaulo.desafiobba.ports.ConsultarClientPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ConsultarClienteUseCaseImpl implements ConsultarClienteUseCase {

    private final ConsultarClientPort clientPort;

    @Override
    public ClienteDto consultarClientePorCpf(String cpf) {
        return clientPort.buscarClientePorCpf(cpf)
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
                )).orElse(null);
    }
}
