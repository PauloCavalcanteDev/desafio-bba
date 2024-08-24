package br.com.devpaulo.desafiobba.application;

import br.com.devpaulo.desafiobba.adapters.ports.EnderecoPort;
import br.com.devpaulo.desafiobba.core.exception.EnderecoNotFoundException;
import br.com.devpaulo.desafiobba.infra.api.viacep.dto.EnderecoDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ConsultarEnderecoUseCaseImplTest {

    @Mock
    private EnderecoPort port;

    @InjectMocks
    private ConsultarEnderecoUseCaseImpl consultarEnderecoUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve receber um cep e retornar o endereço correspondente")
    void testBuscarEnderecoPorCep_Success() throws EnderecoNotFoundException {

        String cep = "12345678";
        EnderecoDto enderecoDto = new EnderecoDto(cep, "Rua Exemplo", "", "Centro", "Cidade", "UF");
        when(port.consultarEnderecoPorCep(cep)).thenReturn(enderecoDto);

        EnderecoDto result = consultarEnderecoUseCase.buscarEnderecoPorCep(cep);

        assertNotNull(result);
        assertEquals(cep, result.cep());
        assertEquals("Rua Exemplo", result.logradouro());
        assertEquals("Centro", result.bairro());
        assertEquals("Cidade", result.localidade());
        assertEquals("UF", result.uf());
    }

    @Test
    @DisplayName("Deve lançar uma exceção ao receber um cep inválido")
    void testBuscarEnderecoPorCep_EnderecoNotFound() {

        String cep = "12345678";
        EnderecoDto enderecoNull = new EnderecoDto(
                null,
                null,
                null,
                null,
                null,
                null

        );

        when(port.consultarEnderecoPorCep(cep)).thenReturn(enderecoNull);

        EnderecoNotFoundException exception = assertThrows(EnderecoNotFoundException.class, () ->
                consultarEnderecoUseCase.buscarEnderecoPorCep(cep)
        );
        assertEquals("Endereco Não Encontrado, verifique o Cep Digitado", exception.getMessage());
    }
}
