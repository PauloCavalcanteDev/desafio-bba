package br.com.devpaulo.desafiobba.application;

import br.com.devpaulo.desafiobba.adapters.ports.EnderecoPort;
import br.com.devpaulo.desafiobba.core.exception.EnderecoNotFoundException;
import br.com.devpaulo.desafiobba.infra.api.viacep.dto.EnderecoDto;
import org.junit.jupiter.api.BeforeEach;
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
    void testBuscarEnderecoPorCep_Success() throws EnderecoNotFoundException {
        // Arrange
        String cep = "12345-678";
        EnderecoDto enderecoDto = new EnderecoDto(cep, "Rua Exemplo", "", "Centro", "Cidade", "UF");
        when(port.consultarEnderecoPorCep(cep)).thenReturn(enderecoDto);

        // Act
        EnderecoDto result = consultarEnderecoUseCase.buscarEnderecoPorCep(cep);

        // Assert
        assertNotNull(result);
        assertEquals(cep, result.cep());
        assertEquals("Rua Exemplo", result.logradouro());
        assertEquals("Centro", result.bairro());
        assertEquals("Cidade", result.localidade());
        assertEquals("UF", result.uf());
    }

    @Test
    void testBuscarEnderecoPorCep_EnderecoNotFound() {
        // Arrange
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

        // Act & Assert
        EnderecoNotFoundException exception = assertThrows(EnderecoNotFoundException.class, () ->
                consultarEnderecoUseCase.buscarEnderecoPorCep(cep)
        );
        assertEquals("Endereco Não Encontrado, verifique o Cep Digitado", exception.getMessage());
    }
}
