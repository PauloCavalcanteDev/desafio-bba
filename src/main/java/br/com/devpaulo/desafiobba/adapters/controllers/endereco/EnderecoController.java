package br.com.devpaulo.desafiobba.adapters.controllers.endereco;

import br.com.devpaulo.desafiobba.adapters.api.ibge.client.IbgeClient;
import br.com.devpaulo.desafiobba.adapters.api.ibge.dto.EstadoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/enderecos")
@RequiredArgsConstructor
public class EnderecoController {

//    private final EnderecoService enderecoService;
    private final IbgeClient ibgeClient;


//    @GetMapping("/{cep}")
//    public ResponseEntity<EnderecoDto> realizarTransferencia(@PathVariable("cep") String cep) {
//        return ResponseEntity.ok(enderecoService.consultarenderecoPorCep(cep));
//
//    }

    @GetMapping("/estados")
    public ResponseEntity<List<EstadoDto>> consultarEstados() {
        return ResponseEntity.ok(ibgeClient.consultaEstadosBrasileiros());
    }

}
