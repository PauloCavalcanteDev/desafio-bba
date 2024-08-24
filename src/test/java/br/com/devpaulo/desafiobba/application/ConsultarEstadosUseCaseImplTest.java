package br.com.devpaulo.desafiobba.application;

import br.com.devpaulo.desafiobba.adapters.ports.EstadosMunicipiosPort;
import br.com.devpaulo.desafiobba.core.dto.EstadoDto;
import br.com.devpaulo.desafiobba.core.exception.IbgeClientException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

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
    @DisplayName("Deve trazer a lista de estados ordenada")
    public void testExecute_Success() throws IbgeClientException {

        List<EstadoDto> estados = List.of(
                new EstadoDto("São Paulo", "SP"),
                new EstadoDto("Rio de Janeiro", "RJ"),
                new EstadoDto("Minas Gerais", "MG"),
                new EstadoDto("Goias", "GO")

        );
        when(estadosMunicipiosPort.listarEstados()).thenReturn(estados);

        List<EstadoDto> result = consultarEstadosUseCase.execute();

        // Assert
        assertNotNull(result);
        assertEquals(4, result.size());
        assertEquals("Rio de Janeiro", result.get(0).estado());
        assertEquals("São Paulo", result.get(1).estado());
        assertEquals("Goias", result.get(2).estado());
        assertEquals("Minas Gerais", result.get(3).estado());
    }

    @DisplayName("Deve lançar uma exceção ao falhar a conexão com client IBGE.")
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
    @DisplayName("Deve ordenar a lista de estados")
    public void testOrdenarEstados() {

        List<EstadoDto> estados = List.of(
                new EstadoDto("Minas Gerais", "MG"),
                new EstadoDto("São Paulo", "SP"),
                new EstadoDto("Rio de Janeiro", "RJ"),
                new EstadoDto("Espírito Santo", "ES")
        );

        List<EstadoDto> result = consultarEstadosUseCase.ordenarEstados(estados);


        assertNotNull(result);
        assertEquals(4, result.size());
        assertEquals("São Paulo", result.get(1).estado());
        assertEquals("Rio de Janeiro", result.get(0).estado());
        assertEquals("Espírito Santo", result.get(2).estado());
        assertEquals("Minas Gerais", result.get(3).estado());
    }
}