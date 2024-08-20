package br.com.devpaulo.desafiobba.adapters.controllers.endereco;

import br.com.devpaulo.desafiobba.adapters.api.ibge.client.IbgeClient;
import br.com.devpaulo.desafiobba.adapters.api.ibge.dto.EstadoDto;
import br.com.devpaulo.desafiobba.adapters.api.viacep.dto.EnderecoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/enderecos")
@RequiredArgsConstructor
public class EnderecoController {

//    private final EnderecoService enderecoService;


    @GetMapping("/{cep}")
    public ResponseEntity<String> getEndereco(@PathVariable("cep") String cep) {
        return ResponseEntity.ok("API Enderecos Running");
    }


}
