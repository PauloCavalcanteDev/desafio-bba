package br.com.devpaulo.desafiobba.adapters.ports.impl;

import br.com.devpaulo.desafiobba.core.domain.cliente.Cliente;
import br.com.devpaulo.desafiobba.core.domain.endereco.Endereco;
import br.com.devpaulo.desafiobba.core.exception.EnderecoNotFoundException;
import br.com.devpaulo.desafiobba.infra.api.viacep.client.ViacepClient;
import br.com.devpaulo.desafiobba.infra.api.viacep.dto.EnderecoDto;
import br.com.devpaulo.desafiobba.infra.database.EnderecoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EnderecoPortImplTest {

    @Mock
    private ViacepClient client;

    @Mock
    private EnderecoRepository enderecoRepository;

    @InjectMocks
    private EnderecoPortImpl enderecoPort;

    private final String CPF = "12345678901";


    @Test
    @DisplayName("Deve chamar o client viacep e retornar o endereço pertecente aquele cep.")
    public void testConsultarEnderecoPorCep() {

        String cep = "12345678";
        EnderecoDto expectedEndereco = mockEnderecoDto();

        when(client.consultarenderecoPorCep(cep)).thenReturn(expectedEndereco);

        EnderecoDto actualEndereco = enderecoPort.consultarEnderecoPorCep(cep);

        assertEquals(expectedEndereco, actualEndereco);
        verify(client, times(1)).consultarenderecoPorCep(cep);
    }

    @Test
    @DisplayName("Deve validar a atualização de um endereço e cliente existente")
    public void testAtulizarEnderecoSuccess() throws EnderecoNotFoundException, EnderecoNotFoundException {

        UUID enderecoId = UUID.randomUUID();
        EnderecoDto novoEndereco = mockEnderecoDto();
        Endereco endereco = new Endereco(enderecoId,
                Cliente.builder()
                        .cpf(CPF)
                        .nome("Teste 1")
                        .enderecos(List.of())
                        .build(),
                "12345678",
                "Rua Exemplo",
                "Complemento",
                "Bairro",
                "Cidade",
                "UF");

        when(enderecoRepository.findById(enderecoId)).thenReturn(Optional.of(endereco));
        when(enderecoRepository.save(any(Endereco.class))).thenReturn(endereco);

        Endereco actualEndereco = enderecoPort.atulizarEndereco(enderecoId, novoEndereco);

        assertEquals(enderecoId, actualEndereco.getId());
        assertEquals(novoEndereco.cep(), actualEndereco.getCep());
        verify(enderecoRepository, times(1)).findById(enderecoId);
        verify(enderecoRepository, times(1)).save(any(Endereco.class));
    }

    @Test
    @DisplayName("Deve lançar uma exceção ao tentar atualizar um endereço inexistente.")
    public void testAtulizarEnderecoNotFound() {

        UUID enderecoId = UUID.randomUUID();
        EnderecoDto novoEndereco = new EnderecoDto("12345678", "Rua Nova", "Complemento", "Bairro", "Cidade", "UF");
        when(enderecoRepository.findById(enderecoId)).thenReturn(Optional.empty());

        assertThrows(EnderecoNotFoundException.class, () -> enderecoPort.atulizarEndereco(enderecoId, novoEndereco));
        verify(enderecoRepository, times(1)).findById(enderecoId);
        verify(enderecoRepository, never()).save(any(Endereco.class));
    }

    public EnderecoDto mockEnderecoDto() {
        return new EnderecoDto(
                "12345678",
                "Rua Exemplo",
                "Complemento",
                "Bairro",
                "Cidade",
                "UF");
    }
}
