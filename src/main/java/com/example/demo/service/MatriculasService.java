package com.example.demo.service;

import com.example.demo.entity.Matricula;
import com.example.demo.repository.MatriculasRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatriculasService {

    private final MatriculasRepository repository;

    public MatriculasService(MatriculasRepository repository) {
        this.repository = repository;
    }

    public List<Matricula> consultarMatriculas(String estado, Integer ano) {
        try {
            return repository.findMatriculas(estado, ano);
        } catch (DataAccessException e) {
            throw new RuntimeException("Erro ao consultar matrículas: " + e.getMessage(), e);
        }
    }
}
