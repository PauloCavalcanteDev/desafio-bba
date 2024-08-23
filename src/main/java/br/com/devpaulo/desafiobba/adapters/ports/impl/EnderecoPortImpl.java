package br.com.devpaulo.desafiobba.adapters.ports.impl;

import br.com.devpaulo.desafiobba.adapters.ports.EnderecoPort;
import br.com.devpaulo.desafiobba.core.domain.endereco.Endereco;
import br.com.devpaulo.desafiobba.core.exception.EnderecoNotFoundException;
import br.com.devpaulo.desafiobba.infra.api.viacep.client.ViacepClient;
import br.com.devpaulo.desafiobba.infra.api.viacep.dto.EnderecoDto;
import br.com.devpaulo.desafiobba.infra.database.EnderecoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Log4j2
public class EnderecoPortImpl implements EnderecoPort {

    private final ViacepClient client;
    private final EnderecoRepository enderecoRepository;

    @Override
    public EnderecoDto consultarEnderecoPorCep(String cep) {
        log.info("Iniciando integragação Via Cep , Buscando cep: {}.", cep);
        return client.consultarenderecoPorCep(cep);

    }

    @Override
    public Endereco atulizarEndereco(UUID enderecoId, EnderecoDto novoEndereco) throws EnderecoNotFoundException {

        log.info("Consultando Endereco {} , ", enderecoId);
        Optional<Endereco> enderecoOpt = enderecoRepository.findById(enderecoId);
        if (enderecoOpt.isPresent()) {
            log.info("Atualizando Endereço...");

            Endereco endereco = enderecoOpt.get();
            Endereco enderecoAtualizado = Endereco.builder()
                    .Id(endereco.getId())
                    .cliente(endereco.getCliente())
                    .cep(novoEndereco.cep())
                    .logradouro(novoEndereco.logradouro())
                    .complemento(novoEndereco.complemento())
                    .bairro(novoEndereco.bairro())
                    .localidade(novoEndereco.localidade())
                    .uf(novoEndereco.uf())
                    .build();
            log.info("Endereço Atualizado com sucesso!");
            return enderecoRepository.save(enderecoAtualizado);

        }
        log.error("Id Endereco{}, não cadastrado", enderecoId);
        throw new EnderecoNotFoundException("Endereço não encontrado");
    }
}

