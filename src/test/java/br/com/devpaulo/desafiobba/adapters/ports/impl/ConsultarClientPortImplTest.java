package br.com.devpaulo.desafiobba.adapters.ports.impl;

import br.com.devpaulo.desafiobba.core.domain.cliente.Cliente;
import br.com.devpaulo.desafiobba.infra.database.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConsultarClientPortImplTest {


    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    ConsultarClientPortImpl clientPort;

    @Test
    void testBuscarClientePorCpf() {
        String cpf = "12345678900";
        Cliente cliente = new Cliente();
        when(clienteRepository.findById(any())).thenReturn(Optional.of(cliente));
        Optional<Cliente> result = clientPort.buscarClientePorCpf(cpf);

        assertTrue(result.isPresent());
        assertEquals(cliente, result.get());
        verify(clienteRepository, times(1)).findById(any());
    }

    @Test
    void testBuscarClientePorCpfNotFound() {
        String cpf = "12345678900";
        when(clienteRepository.findById(cpf)).thenReturn(Optional.empty());

        Optional<Cliente> result = clientPort.buscarClientePorCpf(cpf);

        assertFalse(result.isPresent());
        verify(clienteRepository, times(1)).findById(cpf);
    }
}