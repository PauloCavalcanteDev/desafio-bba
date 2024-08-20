package br.com.devpaulo.desafiobba.application;

import br.com.devpaulo.desafiobba.core.domain.cliente.Cliente;
import br.com.devpaulo.desafiobba.core.exception.ClienteNotFoundException;
import br.com.devpaulo.desafiobba.core.exception.CpfInvalidoException;
import br.com.devpaulo.desafiobba.core.usecase.cliente.ClienteUseCase;
import br.com.devpaulo.desafiobba.ports.ClienteServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteUseCaseImpl implements ClienteUseCase {

    private final ClienteServicePort clienteService;

    @Override
    public Cliente consultarDadosCliente(String cpf) throws ClienteNotFoundException, CpfInvalidoException {
        if(cpf.length() < 11) {
           throw new CpfInvalidoException("CPF não pode ser menor que 11 digitos!");
        }
        return clienteService.findByCpf(cpf);
    }
}
