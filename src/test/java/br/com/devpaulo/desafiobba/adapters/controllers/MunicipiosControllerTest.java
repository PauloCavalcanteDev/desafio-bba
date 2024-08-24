package br.com.devpaulo.desafiobba.adapters.controllers;

import br.com.devpaulo.desafiobba.core.dto.MunicipioDto;
import br.com.devpaulo.desafiobba.core.exception.IbgeClientException;
import br.com.devpaulo.desafiobba.core.usecase.ConsultarMunicipiosUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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

public class MunicipiosControllerTest {

    @Mock
    private ConsultarMunicipiosUseCase consultarMunicipiosUseCase;

    @InjectMocks
    private MunicipiosController municipiosController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve retonar statuscode 200 e a uma lista de municipios pertecentes aquele estado.")
    public void testGetMunicipios_Success() throws IbgeClientException {
        String estado = "SP";
        List<MunicipioDto> municipios = Arrays.asList(
                new MunicipioDto(11, "São Paulo"),
                new MunicipioDto(19, "Campinas")
        );
        when(consultarMunicipiosUseCase.execute(estado)).thenReturn(municipios);

        ResponseEntity<List<MunicipioDto>> response = municipiosController.getMunicipios(estado);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(municipios, response.getBody());
    }

    @Test
    @DisplayName("Deve retornar um erro 500 ao falhar a comunicação com o clint ibge.")
    public void testGetMunicipios_IbgeClientException() throws IbgeClientException {
        String estado = "SP";
        when(consultarMunicipiosUseCase.execute(estado)).thenThrow(new IbgeClientException("Erro ao consultar IBGE"));

        ResponseEntity<List<MunicipioDto>> response = municipiosController.getMunicipios(estado);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }
}
