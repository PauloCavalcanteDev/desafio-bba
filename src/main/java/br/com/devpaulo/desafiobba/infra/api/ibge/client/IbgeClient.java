package br.com.devpaulo.desafiobba.infra.api.ibge.client;

import br.com.devpaulo.desafiobba.infra.api.ibge.dto.EstadoResponseDto;
import br.com.devpaulo.desafiobba.infra.api.viacep.dto.EnderecoDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Service
public class IbgeClient {

    private final RestTemplate restTemplate = new RestTemplate();


    public String consultaMunicio(String cep) {
        ResponseEntity<EnderecoDto> response = restTemplate.getForEntity(
                String.format("https://viacep.com.br/ws/%s/json", cep), EnderecoDto.class);
        return null;
    }

    public List<EstadoResponseDto> consultaEstadosBrasileiros() {

        ResponseEntity<List<EstadoResponseDto>> response = restTemplate.exchange(
                "https://servicodados.ibge.gov.br/api/v1/localidades/estados",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<EstadoResponseDto>>() {}
        );
        return response.getBody();
    }
}
