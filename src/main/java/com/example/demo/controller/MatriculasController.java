package com.example.demo.controller;

import com.example.demo.dto.TotalPorEstadoDTO;
import com.example.demo.repository.MatriculasRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class MatriculasController {

    private final MatriculasRepository repository;

    public MatriculasController(MatriculasRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/matriculas/por-estado")
    public List<TotalPorEstadoDTO> getTotalPorEstado() {
        return repository.totalPorEstado();
    }
}