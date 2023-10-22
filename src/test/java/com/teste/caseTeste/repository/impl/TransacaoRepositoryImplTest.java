package com.teste.caseTeste.repository.impl;

import com.teste.caseTeste.repository.TransacaoRepository;
import com.teste.caseTeste.repository.dto.TransacaoDto;
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

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class TransacaoRepositoryImplTest {

    @Mock
    private TransacaoRepository repository;

    @InjectMocks
    private TransacaoRepositoryImpl transacaoRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCadastra() {

        TransacaoDto transacaoDto = new TransacaoDto(1, "123456", "456789", "Realizada com sucesso", 100.00, LocalDate.of(2023, 10, 21));

        when(repository.save(transacaoDto)).thenReturn(transacaoDto);

        TransacaoDto result = transacaoRepository.cadastra(transacaoDto);

        assertEquals(transacaoDto, result);
    }

    @Test
    public void testBuscaTransacoes() {
        String conta = "123456";

        List<TransacaoDto> transacoes = new ArrayList<>();
        transacoes.add(new TransacaoDto(1, "123456", "456789", "Realizada com sucesso", 100.00, LocalDate.of(2023, 10, 21)));
        transacoes.add(new TransacaoDto(2, "123456", "456789", "Realizada com sucesso", 100.00, LocalDate.of(2023, 10, 21)));
        transacoes.add(new TransacaoDto(3, "123456", "456789", "Realizada com sucesso", 100.00, LocalDate.of(2023, 10, 21)));

        when(repository.buscaPorConta(conta)).thenReturn(transacoes);

        List<TransacaoDto> result = transacaoRepository.buscaTransacoes(conta);

        assertEquals(transacoes, result);
    }
}
