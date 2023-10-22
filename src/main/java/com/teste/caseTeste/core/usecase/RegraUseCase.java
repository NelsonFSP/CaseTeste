package com.teste.caseTeste.core.usecase;

import com.teste.caseTeste.Entrypoint.dto.Cliente;
import com.teste.caseTeste.Entrypoint.dto.Transacao;
import com.teste.caseTeste.core.mapper.ClienteMapper;
import com.teste.caseTeste.core.mapper.TransacaoMapper;
import com.teste.caseTeste.repository.dto.ClienteDto;
import com.teste.caseTeste.repository.dto.TransacaoDto;
import com.teste.caseTeste.repository.impl.ClienteRepositoryImpl;
import com.teste.caseTeste.repository.impl.TransacaoRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Component
@RequiredArgsConstructor
public class RegraUseCase {
    private final ClienteRepositoryImpl clienteRepositoryImpl;

    private final TransacaoRepositoryImpl transacaoRepositoryImpl;
    private final ClienteMapper clienteMapper;
    private final TransacaoMapper transacaoMapper;

    private static final String REALIZADA = "Realizada com sucesso";
    private static final String FALHA = "Falhou";

    public Cliente cadastroCliente(Cliente cliente) {
        return this.clienteMapper.dtoToCliente(clienteRepositoryImpl.cadastra(this.clienteMapper.clienteToDto(cliente)));
    }

    public List<Cliente> buscarClientes() {
        var clientes = clienteRepositoryImpl.buscaClientes();
        List<Cliente> clienteList = new ArrayList<>();
        for (ClienteDto cliente : clientes) {
            clienteList.add(this.clienteMapper.dtoToCliente(cliente));
        }
        return clienteList;
    }

    public Cliente buscarCliente(String conta) {
        var cliente = clienteRepositoryImpl.buscaCliente(conta);
        return this.clienteMapper.dtoToCliente(cliente);
    }

    public Transacao realizarTransacao(Transacao transacao) {
        var clienteorigem = clienteRepositoryImpl.buscaCliente(transacao.getNumContaOrigem());
        var clienteDestino = clienteRepositoryImpl.buscaCliente(transacao.getNumContaDestino());

        if (atualizaSaldo(clienteorigem, clienteDestino, transacao.getValor())) {
            transacao.setStatusTransacao(REALIZADA);
        } else {
            transacao.setStatusTransacao(FALHA);
        }
        return this.transacaoMapper.dtoToTransacao(transacaoRepositoryImpl.cadastra(this.transacaoMapper.transacaoToDto(transacao)));
    }

    public List<Transacao> listarTransacoes(String conta) {
        var transacoes = transacaoRepositoryImpl.buscaTransacoes(conta);
        List<Transacao> transacoesList = new ArrayList<>();
        for (TransacaoDto transacao : transacoes) {
            transacoesList.add(this.transacaoMapper.dtoToTransacao(transacao));
        }
        Collections.sort(transacoesList, Comparator.comparing(Transacao::getDataTransacao).reversed());
        return transacoesList;
    }

    private static boolean validaSaldo(double saldo) {
        return saldo >= 1000.00d;
    }

    private boolean atualizaSaldo(ClienteDto clienteOrigem, ClienteDto clienteDestino, double valor){
        if(validaSaldo(clienteOrigem.getSaldo())){
            clienteOrigem.setSaldo(clienteOrigem.getSaldo() - valor);
            clienteRepositoryImpl.cadastra(clienteOrigem);
            clienteDestino.setSaldo(clienteDestino.getSaldo() + valor);
            clienteRepositoryImpl.cadastra(clienteDestino);
            return true;
        }
        return false;
    }
}
