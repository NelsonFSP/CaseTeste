package com.teste.caseTeste.entrypoint.resource;

import com.teste.caseTeste.Entrypoint.dto.Cliente;
import com.teste.caseTeste.Entrypoint.dto.Transacao;
import com.teste.caseTeste.Entrypoint.resource.ControllerResource;
import com.teste.caseTeste.core.usecase.RegraUseCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ControllerResourceTest {

    @Mock
    private RegraUseCase regraUseCase;

    @InjectMocks
    private ControllerResource controllerResource;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCadastraCliente() throws Exception {

        Cliente cliente = new Cliente("Joao Prado", "123456", 5000.00);

        when(regraUseCase.cadastroCliente(any())).thenReturn(cliente);

        var retorno = controllerResource.cadastraCliente(cliente);

        var teste = new ResponseEntity<>(cliente, HttpStatus.CREATED);

        Assert.assertEquals(retorno, teste);

    }

    @Test
    public void testListarClientes() throws Exception {
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(new Cliente("Joao Prado", "123456", 5000.00));
        clientes.add(new Cliente("Joao Silva", "456789", 5000.00));

        when(regraUseCase.buscarClientes()).thenReturn(clientes);
        var retorno = controllerResource.listarClientes();

        var teste = new ResponseEntity<>(clientes, HttpStatus.OK);

        Assert.assertEquals(retorno, teste);

    }

    @Test
    public void testCliente() throws Exception {
        String numConta = "123456";

        Cliente cliente = new Cliente("Joao Prado", "123456", 5000.00);

        when(regraUseCase.buscarCliente(anyString())).thenReturn(cliente);

        var retorno = controllerResource.cliente(numConta);

        var teste = new ResponseEntity<>(cliente, HttpStatus.OK);

        Assert.assertEquals(retorno, teste);

    }

    @Test
    public void testTransacao() throws Exception {
        Transacao transacao = new Transacao("123456", "456789", "Realizada com sucesso", 100.00, LocalDate.of(2023, 10, 21));

        when(regraUseCase.realizarTransacao(any())).thenReturn(transacao);

        var retorno = controllerResource.transacao(transacao);

        var teste = new ResponseEntity<>(transacao, HttpStatus.CREATED);

        Assert.assertEquals(retorno, teste);

    }

    @Test
    public void testListarTransacao() throws Exception {
        String numConta = "123456";

        List<Transacao> transacoes = new ArrayList<>();
        transacoes.add(new Transacao("123456", "456789", "Realizada com sucesso", 100.00, LocalDate.of(2023, 10, 21)));
        transacoes.add(new Transacao("123456", "456789", "Realizada com sucesso", 100.00, LocalDate.of(2023, 10, 21)));
        transacoes.add(new Transacao("123456", "456789", "Realizada com sucesso", 100.00, LocalDate.of(2023, 10, 21)));

        when(regraUseCase.listarTransacoes(anyString())).thenReturn(transacoes);

        var retorno = controllerResource.listarTransacao(numConta);

        var teste = new ResponseEntity<>(transacoes, HttpStatus.OK);

        Assert.assertEquals(retorno, teste);
    }
}
