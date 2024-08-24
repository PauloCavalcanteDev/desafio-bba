package br.com.devpaulo.desafiobba.adapters.controllers;

import br.com.devpaulo.desafiobba.core.exception.EnderecoNotFoundException;
import br.com.devpaulo.desafiobba.core.usecase.ConsultarEnderecoUseCase;
import br.com.devpaulo.desafiobba.infra.api.viacep.dto.EnderecoDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class EnderecoControllerTest {

    public static final String CEP = "12345678";
    @Mock
    private ConsultarEnderecoUseCase consultarEnderecoUseCase;

    @InjectMocks
    private EnderecoController enderecoController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetEndereco_Success() throws EnderecoNotFoundException {
        EnderecoDto endereco = mockEndereco(CEP);
        when(consultarEnderecoUseCase.buscarEnderecoPorCep(CEP)).thenReturn(endereco);

        ResponseEntity<?> response = enderecoController.getEndereco(CEP);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(endereco, response.getBody());
    }

    @Test
    public void testGetEndereco_NotFound() throws EnderecoNotFoundException {
        String cep = "12345678";
        when(consultarEnderecoUseCase.buscarEnderecoPorCep(cep)).thenThrow(new EnderecoNotFoundException("Endereço não encontrado"));

        ResponseEntity<?> response = enderecoController.getEndereco(cep);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Endereço não encontrado", response.getBody());
    }

    private EnderecoDto mockEndereco(String cep) {
        return new EnderecoDto(cep,
                "Rua Exemplo",
                "",
                "Centro",
                "Cidade",
                "UF");
    }
}
