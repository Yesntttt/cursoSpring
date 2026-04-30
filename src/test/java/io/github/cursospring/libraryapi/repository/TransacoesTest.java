package io.github.cursospring.libraryapi.repository;

import io.github.cursospring.libraryapi.service.TransacaoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TransacoesTest {

    @Autowired
    TransacaoService transacaoService;

    @Test
    void transacaoSimples() {
        transacaoService.executar();
    }
}
