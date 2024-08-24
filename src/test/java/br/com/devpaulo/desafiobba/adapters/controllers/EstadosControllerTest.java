package br.com.devpaulo.desafiobba.adapters.controllers;

import br.com.devpaulo.desafiobba.core.dto.EstadoDto;
import br.com.devpaulo.desafiobba.core.exception.IbgeClientException;
import br.com.devpaulo.desafiobba.core.usecase.ConsultarEstadosUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

public class EstadosControllerTest {

    @Mock
    private ConsultarEstadosUseCase consultarEstadosUseCase;

    @InjectMocks
    private EstadosController estadosController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetEstados_Success() throws IbgeClientException {
        List<EstadoDto> estados = Arrays.asList(
                new EstadoDto("SP", "São Paulo"),
                new EstadoDto("RJ", "Rio de Janeiro")
        );

        when(consultarEstadosUseCase.execute()).thenReturn(estados);

        ResponseEntity<List<EstadoDto>> response = estadosController.getEstados();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(estados, response.getBody());
    }

    @Test
    public void testGetEstados_IbgeClientException() throws IbgeClientException {
        when(consultarEstadosUseCase.execute()).thenThrow(new IbgeClientException("Erro ao consultar IBGE"));

        ResponseEntity<List<EstadoDto>> response = estadosController.getEstados();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }
}
