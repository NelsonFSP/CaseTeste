package com.teste.caseTeste.core.usecase;

import com.teste.caseTeste.Entrypoint.dto.Cliente;
import com.teste.caseTeste.Entrypoint.dto.Transacao;
import com.teste.caseTeste.core.mapper.ClienteMapper;
import com.teste.caseTeste.core.mapper.TransacaoMapper;
import com.teste.caseTeste.repository.dto.ClienteDto;
import com.teste.caseTeste.repository.dto.TransacaoDto;
import com.teste.caseTeste.repository.impl.ClienteRepositoryImpl;
import com.teste.caseTeste.repository.impl.TransacaoRepositoryImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class RegraUseCaseTest {

    @Mock
    private ClienteRepositoryImpl clienteRepositoryImpl;
    @Mock
    private TransacaoRepositoryImpl transacaoRepositoryImpl;
    @Mock
    private ClienteMapper clienteMapper;
    @Mock
    private TransacaoMapper transacaoMapper;
    @InjectMocks
    private RegraUseCase regraUseCase;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void cadastroClienteTest() {
        Cliente cliente = new Cliente("Joao Prado", "123456", 5000.00);
        ClienteDto clienteDto = new ClienteDto(1, "Joao Prado", "123456", 5000.00);
        when(clienteMapper.clienteToDto(cliente)).thenReturn(clienteDto);
        when(clienteRepositoryImpl.cadastra(clienteDto)).thenReturn(clienteDto);
        when(clienteMapper.dtoToCliente(clienteDto)).thenReturn(cliente);

        Cliente retorno = regraUseCase.cadastroCliente(cliente);

        Assert.assertEquals(retorno.getNome(), cliente.getNome());
        Assert.assertEquals(retorno.getNumeroConta(), cliente.getNumeroConta());
    }

    @Test
    public void testBuscarClientes() {
        List<ClienteDto> clienteDtos = new ArrayList<>();
        clienteDtos.add(new ClienteDto(1, "Joao Prado", "123456", 5000.00));
        clienteDtos.add(new ClienteDto(1, "Joao Silva", "456789", 5000.00));
        clienteDtos.add(new ClienteDto(1, "Joao Fernand", "147258", 5000.00));
        when(clienteRepositoryImpl.buscaClientes()).thenReturn(clienteDtos);

        var retorno = regraUseCase.buscarClientes();

        verify(clienteRepositoryImpl, times(1)).buscaClientes();
        Assert.assertEquals(3, retorno.size());

    }

    @Test
    public void testBuscarCliente() {
        String conta = "123456";
        Cliente cliente = new Cliente("Joao Prado", "123456", 5000.00);
        ClienteDto clienteDto = new ClienteDto(1, "Joao Prado", "123456", 5000.00);
        when(clienteRepositoryImpl.buscaCliente(conta)).thenReturn(clienteDto);
        when(clienteMapper.dtoToCliente(clienteDto)).thenReturn(cliente);

        Cliente retorno = regraUseCase.buscarCliente(conta);

        Assert.assertEquals(retorno.getNome(), cliente.getNome());
        Assert.assertEquals(retorno.getNumeroConta(), cliente.getNumeroConta());

    }

    @Test
    public void testRealizarTransacao() {
        Transacao transacao = new Transacao("123456", "456789", "Realizada com sucesso", 100.00, LocalDate.of(2023, 10, 21));
        TransacaoDto transacaoDto = new TransacaoDto(1, "123456", "456789", "Realizada com sucesso", 100.00, LocalDate.of(2023, 10, 21));
        ClienteDto clienteOrigemDto = new ClienteDto(1, "Joao Prado", "123456", 5000.00);
        ClienteDto clienteDestinoDto = new ClienteDto(1, "Joao Silva", "456789", 5000.00);
        Cliente clienteOrigem = new Cliente("Joao Prado", "123456", 5000.00);
        Cliente clienteDestino = new Cliente("Joao Silva", "456789", 5000.00);
        when(clienteRepositoryImpl.buscaCliente(transacao.getNumContaOrigem())).thenReturn(clienteOrigemDto);
        when(clienteRepositoryImpl.buscaCliente(transacao.getNumContaDestino())).thenReturn(clienteDestinoDto);
        when(clienteMapper.dtoToCliente(clienteOrigemDto)).thenReturn(clienteOrigem);
        when(clienteMapper.dtoToCliente(clienteDestinoDto)).thenReturn(clienteDestino);
        when(clienteMapper.clienteToDto(clienteOrigem)).thenReturn(clienteOrigemDto);
        when(clienteMapper.clienteToDto(clienteDestino)).thenReturn(clienteDestinoDto);
        when(transacaoMapper.transacaoToDto(transacao)).thenReturn(transacaoDto);
        when(transacaoMapper.dtoToTransacao(transacaoDto)).thenReturn(transacao);
        when(transacaoRepositoryImpl.cadastra(any())).thenReturn(transacaoDto);

        Transacao retorno = regraUseCase.realizarTransacao(transacao);

        Assert.assertSame(transacao.getNumContaOrigem(), retorno.getNumContaOrigem());
        Assert.assertSame(transacao.getNumContaDestino(), retorno.getNumContaDestino());
    }

    @Test
    public void testListarTransacoes() {
        String conta = "123456";
        List<TransacaoDto> transacaoDtos = new ArrayList<>();
        transacaoDtos.add(new TransacaoDto(1, "123456", "456789", "Realizada com sucesso", 100.00, LocalDate.of(2023, 10, 21)));
        transacaoDtos.add(new TransacaoDto(2, "123456", "456789", "Realizada com sucesso", 100.00, LocalDate.of(2023, 10, 21)));
        transacaoDtos.add(new TransacaoDto(3, "123456", "456789", "Realizada com sucesso", 100.00, LocalDate.of(2023, 10, 21)));
        List<Transacao> transacoes = new ArrayList<>();
        transacoes.add(new Transacao("123456", "456789", "Realizada com sucesso", 100.00, LocalDate.of(2023, 10, 21)));
        transacoes.add(new Transacao("123456", "456789", "Realizada com sucesso", 100.00, LocalDate.of(2023, 10, 21)));
        transacoes.add(new Transacao("123456", "456789", "Realizada com sucesso", 100.00, LocalDate.of(2023, 10, 21)));
        when(transacaoMapper.transacaoToDto(any())).thenReturn(transacaoDtos.get(0));
        when(transacaoMapper.dtoToTransacao(any())).thenReturn(transacoes.get(0));
        when(transacaoRepositoryImpl.buscaTransacoes(conta)).thenReturn(transacaoDtos);

        var retorno = regraUseCase.listarTransacoes(conta);

        Assert.assertEquals(3, retorno.size());
    }
}
