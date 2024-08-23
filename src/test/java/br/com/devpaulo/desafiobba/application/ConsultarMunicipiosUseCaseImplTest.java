package br.com.devpaulo.desafiobba.application;

import br.com.devpaulo.desafiobba.adapters.ports.EstadosMunicipiosPort;
import br.com.devpaulo.desafiobba.core.dto.MunicipioDto;
import br.com.devpaulo.desafiobba.core.exception.IbgeClientException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


public class ConsultarMunicipiosUseCaseImplTest {

    @Mock
    private EstadosMunicipiosPort estadosMunicipiosPort;

    @InjectMocks
    private ConsultarMunicipiosUseCaseImpl consultarMunicipiosUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExecuteSuccess() throws IbgeClientException {
        // Arrange
        String uf = "SP";
        List<MunicipioDto> expectedMunicipios = Arrays.asList(new MunicipioDto(10, "São Paulo"), new MunicipioDto(10, "Campinas"));
        when(estadosMunicipiosPort.listarMunicipios(uf)).thenReturn(expectedMunicipios);

        // Act
        List<MunicipioDto> actualMunicipios = consultarMunicipiosUseCase.execute(uf);

        // Assert
        assertEquals(expectedMunicipios, actualMunicipios);
        verify(estadosMunicipiosPort, times(1)).listarMunicipios(uf);
    }

    @Test
    public void testExecuteThrowsIbgeClientException() throws IbgeClientException {
        // Arrange
        String uf = "SP";
        when(estadosMunicipiosPort.listarMunicipios(uf)).thenThrow(new IbgeClientException("Error"));

        // Act & Assert
        assertThrows(IbgeClientException.class, () -> consultarMunicipiosUseCase.execute(uf));
        verify(estadosMunicipiosPort, times(1)).listarMunicipios(uf);
    }
}