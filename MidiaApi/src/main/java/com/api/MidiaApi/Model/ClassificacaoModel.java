package com.api.MidiaApi.Model;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table(name="classificacao")
public class ClassificacaoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, length = 100, unique = true)
    private String classificacao;
    @Column(nullable = false)
    private LocalDate data_insercao;
    @Column
    private LocalDate data_inativacao;

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getClassificacao() {
        return classificacao;
    }
    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }
    public LocalDate getData_insercao() {
        return data_insercao;
    }
    public void setData_insercao(LocalDate data_insercao) {
        this.data_insercao = data_insercao;
    }
    public LocalDate getData_inativacao() {
        return data_inativacao;
    }
    public void setData_inativacao(LocalDate data_inativacao) {
        this.data_inativacao = data_inativacao;
    }   
    
}
