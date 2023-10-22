package com.teste.caseTeste.Entrypoint.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Transacao{
    private String numContaOrigem;
    private String numContaDestino;
    private String statusTransacao;
    private double valor;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dataTransacao;
}
