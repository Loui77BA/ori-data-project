package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "matriculas")
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String estado;
    private String municipio;
    private Integer ano;
    private Double totalMatriculas;

    // Construtor padrão (necessário para o JPA)
    public Matricula() {}

    // Construtor parametrizado
    public Matricula(Long id, String estado, String municipio, Integer ano, Double totalMatriculas) {
        this.id = id;
        this.estado = estado;
        this.municipio = municipio;
        this.ano = ano;
        this.totalMatriculas = totalMatriculas;
    }

    // Getters e setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Double getTotalMatriculas() {
        return totalMatriculas;
    }

    public void setTotalMatriculas(Double totalMatriculas) {
        this.totalMatriculas = totalMatriculas;
    }
}
