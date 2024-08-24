package br.com.devpaulo.desafiobba.application;

import br.com.devpaulo.desafiobba.adapters.ports.ConsultarClientPort;
import br.com.devpaulo.desafiobba.core.domain.cliente.Cliente;
import br.com.devpaulo.desafiobba.core.domain.endereco.Endereco;
import br.com.devpaulo.desafiobba.core.dto.ClienteDto;
import br.com.devpaulo.desafiobba.core.exception.ClienteNotFoundException;
import br.com.devpaulo.desafiobba.infra.api.viacep.dto.EnderecoDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ConsultarClienteUseCaseImplTest {

    @Mock
    private ConsultarClientPort clientPort;

    @InjectMocks
    private ConsultarClienteUseCaseImpl consultarClienteUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve retornar o cliente e seus respectivos enderecos ao receber um cpf válido")
    void testConsultarClientePorCpf_Success() throws ClienteNotFoundException {

        String cpf = "12345678900";
        Cliente cliente = new Cliente(
                cpf,
                "João da Silva",
                Collections.singletonList(new Endereco(UUID.randomUUID(),
                        Cliente.builder()
                                .nome("João da Silva")
                                .cpf(cpf)
                                .build(),
                        "12345-678",
                        "Rua Exemplo",
                        "", "Centro",
                        "Cidade",
                        "UF"))
        );
        when(clientPort.buscarClientePorCpf(cpf)).thenReturn(Optional.of(cliente));


        ClienteDto result = consultarClienteUseCase.consultarClientePorCpf(cpf);


        assertNotNull(result);
        assertEquals(cpf, result.cpf());
        assertEquals("João da Silva", result.nome());
        assertEquals(1, result.endereco().size());
        EnderecoDto enderecoDto = result.endereco().get(0);
        assertEquals("12345-678", enderecoDto.cep());
        assertEquals("Rua Exemplo", enderecoDto.logradouro());
        assertEquals("Centro", enderecoDto.bairro());
        assertEquals("Cidade", enderecoDto.localidade());
        assertEquals("UF", enderecoDto.uf());
    }

    @Test
    @DisplayName("Deve lançar uma exceção ao busca um cliente que não exista na base de dados.")
    void testConsultarClientePorCpf_ClienteNotFound() {

        String cpf = "12345678900";
        when(clientPort.buscarClientePorCpf(cpf)).thenReturn(Optional.empty());

        ClienteNotFoundException exception = assertThrows(ClienteNotFoundException.class, () ->
                consultarClienteUseCase.consultarClientePorCpf(cpf)
        );
        assertEquals("Cliente Não Encontrado.", exception.getMessage());
    }
}