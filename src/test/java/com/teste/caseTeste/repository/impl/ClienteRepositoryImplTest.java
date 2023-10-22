package com.teste.caseTeste.repository.impl;

import com.teste.caseTeste.repository.ClienteRepository;
import com.teste.caseTeste.repository.dto.ClienteDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ClienteRepositoryImplTest {

    @Mock
    private ClienteRepository repository;
    @InjectMocks
    private ClienteRepositoryImpl clienteRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCadastra() {
        ClienteDto clienteDto = new ClienteDto(1, "Joao Prado", "123456", 5000.00);

        when(repository.save(clienteDto)).thenReturn(clienteDto);

        ClienteDto retorno = clienteRepository.cadastra(clienteDto);

        assertEquals(clienteDto, retorno);
    }

    @Test
    public void testBuscaClientes() {
        List<ClienteDto> clientes = new ArrayList<>();
        clientes.add(new ClienteDto(1, "Joao Prado", "123456", 5000.00));
        clientes.add(new ClienteDto(1, "Joao Silva", "456789", 5000.00));
        clientes.add(new ClienteDto(1, "Joao Fernand", "147258", 5000.00));

        when(repository.findAll()).thenReturn(clientes);

        List<ClienteDto> result = clienteRepository.buscaClientes();

        assertEquals(clientes, result);
    }

    @Test
    public void testBuscaCliente() {
        String conta = "123456";

        ClienteDto clienteDto = new ClienteDto(1, "Joao Prado", "123456", 5000.00);

        when(repository.buscaCliente(conta)).thenReturn(clienteDto);

        ClienteDto result = clienteRepository.buscaCliente(conta);

        assertEquals(clienteDto, result);
    }
}
