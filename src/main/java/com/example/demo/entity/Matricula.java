package com.example.demo.entity;

import jakarta.persistence.Column;
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

    @Column(name = "ano")
    private Integer ano;

    @Column(name = "estado")
    private String estado;

    @Column(name = "baixa_visao")
    private Double baixaVisao;

    @Column(name = "cegueira")
    private Double cegueira;

    @Column(name = "def_auditiva")
    private Double defAuditiva;

    @Column(name = "surdez")
    private Double surdez;

    @Column(name = "surdocegueira")
    private Double surdocegueira;

    @Column(name = "def_fisica")
    private Double defFisica;

    @Column(name = "def_intelectual")
    private Double defIntelectual;

    @Column(name = "autismo")
    private Double autismo;

    @Column(name = "superdotacao")
    private Double superdotacao;

    @Column(name = "def_multipla")
    private Double defMultipla;

    public Matricula() {}

    public Matricula(Integer ano, String estado, Double baixaVisao, Double cegueira, Double defAuditiva, Double surdez,
                     Double surdocegueira, Double defFisica, Double defIntelectual, Double autismo,
                     Double superdotacao, Double defMultipla) {
        this.ano = ano;
        this.estado = estado;
        this.baixaVisao = baixaVisao;
        this.cegueira = cegueira;
        this.defAuditiva = defAuditiva;
        this.surdez = surdez;
        this.surdocegueira = surdocegueira;
        this.defFisica = defFisica;
        this.defIntelectual = defIntelectual;
        this.autismo = autismo;
        this.superdotacao = superdotacao;
        this.defMultipla = defMultipla;
    }

    // Getters e setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Double getBaixaVisao() {
        return baixaVisao;
    }

    public void setBaixaVisao(Double baixaVisao) {
        this.baixaVisao = baixaVisao;
    }

    public Double getCegueira() {
        return cegueira;
    }

    public void setCegueira(Double cegueira) {
        this.cegueira = cegueira;
    }

    public Double getDefAuditiva() {
        return defAuditiva;
    }

    public void setDefAuditiva(Double defAuditiva) {
        this.defAuditiva = defAuditiva;
    }

    public Double getSurdez() {
        return surdez;
    }

    public void setSurdez(Double surdez) {
        this.surdez = surdez;
    }

    public Double getSurdocegueira() {
        return surdocegueira;
    }

    public void setSurdocegueira(Double surdocegueira) {
        this.surdocegueira = surdocegueira;
    }

    public Double getDefFisica() {
        return defFisica;
    }

    public void setDefFisica(Double defFisica) {
        this.defFisica = defFisica;
    }

    public Double getDefIntelectual() {
        return defIntelectual;
    }

    public void setDefIntelectual(Double defIntelectual) {
        this.defIntelectual = defIntelectual;
    }

    public Double getAutismo() {
        return autismo;
    }

    public void setAutismo(Double autismo) {
        this.autismo = autismo;
    }

    public Double getSuperdotacao() {
        return superdotacao;
    }

    public void setSuperdotacao(Double superdotacao) {
        this.superdotacao = superdotacao;
    }

    public Double getDefMultipla() {
        return defMultipla;
    }

    public void setDefMultipla(Double defMultipla) {
        this.defMultipla = defMultipla;
    }

    // Método para calcular total (somente em runtime, não é armazenado no BD)
    public Double getTotal() {
        return baixaVisao + cegueira + defAuditiva + surdez + surdocegueira +
               defFisica + defIntelectual + autismo + superdotacao + defMultipla;
    }
}
