package br.com.devpaulo.desafiobba.adapters.controllers;

import br.com.devpaulo.desafiobba.core.dto.ClienteDto;
import br.com.devpaulo.desafiobba.core.exception.ClienteNotFoundException;
import br.com.devpaulo.desafiobba.core.exception.EnderecoNotFoundException;
import br.com.devpaulo.desafiobba.core.usecase.AlterarEnderecoUseCase;
import br.com.devpaulo.desafiobba.core.usecase.ConsultarClienteUseCase;
import br.com.devpaulo.desafiobba.infra.api.viacep.dto.EnderecoDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.UUID;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ClienteControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ConsultarClienteUseCase clienteUseCase;

    @Mock
    private AlterarEnderecoUseCase alterarEnderecoUseCase;

    @InjectMocks
    private ClienteController clienteController;

    private String URL_PATH = "/api/v1/clientes/";

    private String CEP = "12345678";

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(clienteController).build();
    }

    @Test
    public void testGetClientes_Success() throws Exception {
        String cpf = "12345678901";
        EnderecoDto enderecoDto = mockEndereco(CEP);
        ClienteDto cliente = new ClienteDto(cpf, "Teste 1", Arrays.asList(enderecoDto));


        when(clienteUseCase.consultarClientePorCpf(cpf)).thenReturn(cliente);

        mockMvc.perform(get(URL_PATH + cpf))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.cpf").value(cpf));
    }

    @Test
    public void testGetClientes_NotFound() throws Exception {
        String cpf = "12345678901";
        when(clienteUseCase.consultarClientePorCpf(cpf)).thenThrow(new ClienteNotFoundException("Cliente Não Encontrado."));

        mockMvc.perform(get(URL_PATH + cpf))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Cliente Não Encontrado."));
    }

    @Test
    public void testAtualizarEndereco_Success() throws Exception {
        String cpf = "12345678901";
        UUID enderecoId = UUID.randomUUID();
        EnderecoDto novoEndereco = mockEndereco(CEP);

        mockMvc.perform(put(URL_PATH + cpf + "/enderecos/" + enderecoId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonToString(novoEndereco)))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testAtualizarEndereco_EnderecoNotFound() throws Exception {
        String cpf = "12345678901";
        UUID enderecoId = UUID.randomUUID();
        EnderecoDto novoEndereco = mockEndereco(CEP);

        doThrow(new EnderecoNotFoundException("Endereço Não Encontrado.")).when(alterarEnderecoUseCase).execute(cpf, enderecoId, novoEndereco);

        mockMvc.perform(put(URL_PATH + cpf + "/enderecos/" + enderecoId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonToString(novoEndereco)))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Endereço Não Encontrado."));
    }

    @Test
    public void testAtualizarEndereco_ClienteNotFound() throws Exception {
        String cpf = "12345678901";
        UUID enderecoId = UUID.randomUUID();
        EnderecoDto novoEndereco = mockEndereco(CEP);

        doThrow(new ClienteNotFoundException("Cliente Não Encontrado.")).when(alterarEnderecoUseCase).execute(cpf, enderecoId, novoEndereco);

        mockMvc.perform(put("/" + cpf + "/enderecos/" + enderecoId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonToString(novoEndereco)))
                .andExpect(status().isNotFound());
    }

    private static String jsonToString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private EnderecoDto mockEndereco(String cep) {
        return new EnderecoDto(cep,
                "Rua Exemplo",
                "",
                "Centro",
                "Cidade",
                "UF");
    }
}
