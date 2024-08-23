package br.com.devpaulo.desafiobba.infra.api.ibge.client;

import br.com.devpaulo.desafiobba.core.dto.MunicipioDto;
import br.com.devpaulo.desafiobba.infra.api.ibge.dto.EstadoResponseDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class IbgeClient {
    private final RestTemplate restTemplate = new RestTemplate();


    public List<MunicipioDto> getMunicipios(String uf) {
        ResponseEntity<List<MunicipioDto>> response = restTemplate.exchange(
                String.format(
                        "https://servicodados.ibge.gov.br/api/v1/localidades/estados/%s/municipios", uf),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<MunicipioDto>>() {
                }
        );
        return response.getBody();
    }


    public List<EstadoResponseDto> consultaEstadosBrasileiros() {

        ResponseEntity<List<EstadoResponseDto>> response = restTemplate.exchange(
                "https://servicodados.ibge.gov.br/api/v1/localidades/estados",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<EstadoResponseDto>>() {
                }
        );
        return response.getBody();
    }
}
