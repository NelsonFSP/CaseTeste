package com.teste.caseTeste.repository;

import com.teste.caseTeste.repository.dto.ClienteDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteDto, Integer> {

    @Query(value = "SELECT * FROM tb_cliente " +
            "WHERE numero_conta = :conta", nativeQuery = true)
    ClienteDto buscaCliente(@Param("conta") String conta);
}
