package com.teste.caseTeste.repository.impl;

import com.teste.caseTeste.repository.ClienteRepository;
import com.teste.caseTeste.repository.dto.ClienteDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ClienteRepositoryImpl {
    @Autowired
    private @Lazy ClienteRepository repository;

    public ClienteDto cadastra(ClienteDto clienteDto) {
        return repository.save(clienteDto);
    }

    public List<ClienteDto> buscaClientes() {
        return repository.findAll();
    }

    public ClienteDto buscaCliente(String conta) {
        return repository.buscaCliente(conta);
    }
}
