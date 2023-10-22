package com.teste.caseTeste.repository.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_transacao")
public class TransacaoDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "num_conta_origem")
    private String numContaOrigem;
    @Column(name = "num_conta_destino")
    private String numContaDestino;
    @Column(name = "status_transacao")
    private String statusTransacao;
    @Column(name = "valor")
    private double valor;
    @Column(name = "data_transacao")
    private LocalDate dataTransacao;
}
