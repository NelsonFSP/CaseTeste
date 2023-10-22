package com.teste.caseTeste.core.mapper;


import com.teste.caseTeste.Entrypoint.dto.Transacao;
import com.teste.caseTeste.repository.dto.TransacaoDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransacaoMapper {
    TransacaoDto transacaoToDto (Transacao transacao);

    Transacao dtoToTransacao (TransacaoDto transacaoDto);
}
