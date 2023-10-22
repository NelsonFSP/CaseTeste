package com.teste.caseTeste.repository;

import com.teste.caseTeste.repository.dto.TransacaoDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TransacaoRepository extends JpaRepository<TransacaoDto, Integer> {

    @Query(value = "SELECT * FROM tb_transacao" +
            " WHERE (num_conta_origem = :conta) OR (num_conta_destino = :conta)", nativeQuery = true)
    List<TransacaoDto> buscaPorConta(@Param("conta") String conta);
}
