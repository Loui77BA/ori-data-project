package com.example.demo.controller;

import com.example.demo.entity.Matricula;
import com.example.demo.service.MatriculasService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MatriculasController {

    private final MatriculasService service;

    public MatriculasController(MatriculasService service) {
        this.service = service;
    }

    @GetMapping("/matriculas")
    public List<Matricula> getMatriculas(
        @RequestParam(required = false) String estado,
        @RequestParam(required = false) Integer ano
    ) {
        return service.consultarMatriculas(estado, ano);
    }
}
