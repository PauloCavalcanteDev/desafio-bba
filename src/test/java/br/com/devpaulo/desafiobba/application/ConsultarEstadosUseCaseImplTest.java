package br.com.devpaulo.desafiobba.application;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import br.com.devpaulo.desafiobba.adapters.ports.EstadosMunicipiosPort;
import br.com.devpaulo.desafiobba.core.dto.EstadoDto;
import br.com.devpaulo.desafiobba.core.exception.IbgeClientException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ConsultarEstadosUseCaseImplTest {

    @Mock
    private EstadosMunicipiosPort estadosMunicipiosPort;

    @InjectMocks
    private ConsultarEstadosUseCaseImpl consultarEstadosUseCase;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExecute_Success() throws IbgeClientException {
        // Arrange
        List<EstadoDto> estados = List.of(
                new EstadoDto("São Paulo", "SP"),
                new EstadoDto("Rio de Janeiro", "RJ"),
                new EstadoDto("Minas Gerais", "MG"),
                new EstadoDto("Goias", "GO")

        );
        when(estadosMunicipiosPort.listarEstados()).thenReturn(estados);

        // Act
        List<EstadoDto> result = consultarEstadosUseCase.execute();

        // Assert
        assertNotNull(result);
        assertEquals(4, result.size());
//        assertEquals("Rio de Janeiro", result.get(0).estado());
//        assertEquals("São Paulo", result.get(1).estado());
//        assertEquals("Goias", result.get(1).estado());
//        assertEquals("Minas Gerais", result.get(2).estado());
    }

    @Test
    public void testExecute_IbgeClientException() throws IbgeClientException {
        // Arrange
        when(estadosMunicipiosPort.listarEstados()).thenThrow(new IbgeClientException("Erro ao consultar IBGE"));

        // Act & Assert
        assertThrows(IbgeClientException.class, () -> {
            consultarEstadosUseCase.execute();
        });
    }

    @Test
    public void testOrdenarEstados() {
        // Arrange
        List<EstadoDto> estados = List.of(
                new EstadoDto("Minas Gerais", "MG"),
                new EstadoDto("São Paulo", "SP"),
                new EstadoDto("Rio de Janeiro", "RJ"),
                new EstadoDto("Espírito Santo", "ES")
        );

        // Act
        List<EstadoDto> result = consultarEstadosUseCase.ordenarEstados(estados);

        // Assert
        assertNotNull(result);
        assertEquals(4, result.size());
        assertEquals("São Paulo", result.get(1).estado());
        assertEquals("Rio de Janeiro", result.get(0).estado());
        assertEquals("Espírito Santo", result.get(2).estado());
        assertEquals("Minas Gerais", result.get(3).estado());
    }
}