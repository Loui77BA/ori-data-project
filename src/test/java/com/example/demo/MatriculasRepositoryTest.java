package com.example.demo;

import com.example.demo.entity.Matricula; // Adicione esta importação
import com.example.demo.dto.TotalPorEstadoDTO;
import com.example.demo.repository.MatriculasRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class MatriculasRepositoryTest {

    @Autowired
    private MatriculasRepository repository;

    @Test
    @Transactional
    void testTotalPorEstado() {
        // Inserir dados de teste
        repository.save(new Matricula(null, "Bahia", "Salvador", 2020, 100.0));
        repository.save(new Matricula(null, "Bahia", "Barreiras", 2020, 50.0));

        // Executar a consulta e validar
        List<TotalPorEstadoDTO> results = repository.totalPorEstado();
        assertFalse(results.isEmpty());
    }
}
