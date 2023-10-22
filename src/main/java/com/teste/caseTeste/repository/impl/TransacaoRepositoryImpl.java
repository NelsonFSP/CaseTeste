package com.teste.caseTeste.repository.impl;

import com.teste.caseTeste.repository.TransacaoRepository;
import com.teste.caseTeste.repository.dto.TransacaoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class TransacaoRepositoryImpl {

    @Autowired
    private @Lazy TransacaoRepository repository;

    public TransacaoDto cadastra(TransacaoDto transacaoDto) {
        return repository.save(transacaoDto);
    }

    public List<TransacaoDto> buscaTransacoes(String conta) {
        return repository.buscaPorConta(conta);
    }
}
