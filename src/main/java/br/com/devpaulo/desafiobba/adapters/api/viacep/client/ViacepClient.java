package br.com.devpaulo.desafiobba.adapters.api.viacep.client;

import br.com.devpaulo.desafiobba.adapters.api.viacep.dto.EnderecoDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ViacepClient{

    private final RestTemplate restTemplate = new RestTemplate();


    public EnderecoDto consultarenderecoPorCep(String cep) {
        ResponseEntity<EnderecoDto> response = restTemplate.getForEntity(
                String.format("https://viacep.com.br/ws/%s/json", cep), EnderecoDto.class);
        return response.getBody();
    }
}
