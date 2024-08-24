package br.com.devpaulo.desafiobba.adapters.ports.impl;

import br.com.devpaulo.desafiobba.core.dto.EstadoDto;
import br.com.devpaulo.desafiobba.core.dto.MunicipioDto;
import br.com.devpaulo.desafiobba.core.exception.IbgeClientException;
import br.com.devpaulo.desafiobba.infra.api.ibge.client.IbgeClient;
import br.com.devpaulo.desafiobba.infra.api.ibge.dto.EstadoResponseDto;
import br.com.devpaulo.desafiobba.infra.api.ibge.dto.RegiaoDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EstadosMunicipiosPortImplTest {

    @Mock
    private IbgeClient client;

    @InjectMocks
    private EstadosMunicipiosPortImpl estadosMunicipiosPort;


    @Test
    @DisplayName("Deve chamar o client-ibge e retornar uma lista de estados ordenados.")
    void testListarEstados() throws IbgeClientException {
        EstadoResponseDto estado1 = mockEstado("São Paulo", "SP");
        EstadoResponseDto estado2 = mockEstado("Rio de Janeiro", "RJ");
        EstadoResponseDto estado3 = mockEstado("Minas Gerais", "MG");
        List<EstadoResponseDto> estados = Arrays.asList(estado1, estado2, estado3);

        when(client.consultaEstadosBrasileiros()).thenReturn(estados);

        List<EstadoDto> result = estadosMunicipiosPort.listarEstados();

        assertEquals(3, result.size());
        assertEquals("São Paulo", result.get(0).estado());
        assertEquals("SP", result.get(0).uf());
        assertEquals("Rio de Janeiro", result.get(1).estado());
        assertEquals("RJ", result.get(1).uf());
        assertEquals("Minas Gerais", result.get(2).estado());
        assertEquals("MG", result.get(2).uf());

        verify(client, times(1)).consultaEstadosBrasileiros();
    }

    @Test
    @DisplayName("Deve lançar uma exceção ao falhar a comunicação com o client.")
    void testListarEstadosThrowsException() {

        when(client.consultaEstadosBrasileiros()).thenThrow(new RuntimeException("API error"));

        IbgeClientException exception = assertThrows(IbgeClientException.class, () -> {
            estadosMunicipiosPort.listarEstados();
        });

        verify(client, times(1)).consultaEstadosBrasileiros();
    }

    @Test
    @DisplayName("Deve chamar o client e retornar uma lista de municipios pertencentes aquele estado.")
    void testListarMunicipios() throws IbgeClientException {
        String uf = "SP";
        MunicipioDto municipio1 = mockMunicipios(1, "Osasco");
        MunicipioDto municipio2 = mockMunicipios(1, "Campinas");
        List<MunicipioDto> municipios = Arrays.asList(municipio1, municipio2);

        when(client.getMunicipios(uf)).thenReturn(municipios);

        List<MunicipioDto> result = estadosMunicipiosPort.listarMunicipios(uf);

        assertEquals(2, result.size());
        assertEquals("Osasco", result.get(0).nome());
        assertEquals("Campinas", result.get(1).nome());
        verify(client, times(1)).getMunicipios(uf);
    }


    @Test
    @DisplayName("Deve lançar uma exceção ao falhar a comunicação com o client.")
    void testListarMunicipiosThrowsException() {

        String uf = "SP";

        when(client.getMunicipios(uf)).thenThrow(new RuntimeException("API error"));

        IbgeClientException exception = assertThrows(IbgeClientException.class, () -> {
            estadosMunicipiosPort.listarMunicipios(uf);
        });

        verify(client, times(1)).getMunicipios(uf);
    }

    private EstadoResponseDto mockEstado(String estado, String uf) {
        return new EstadoResponseDto(
                Integer.numberOfTrailingZeros(1),
                uf,
                estado,
                new RegiaoDto(uf, estado)
        );

    }

    private MunicipioDto mockMunicipios(int id, String nome) {
        return new MunicipioDto(id, nome);
    }
}