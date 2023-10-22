package com.teste.caseTeste.core.mapper;

import com.teste.caseTeste.Entrypoint.dto.Cliente;
import com.teste.caseTeste.repository.dto.ClienteDto;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ClienteMapper {
    ClienteDto clienteToDto(Cliente cliente);

    Cliente dtoToCliente(ClienteDto clienteDto);
}
