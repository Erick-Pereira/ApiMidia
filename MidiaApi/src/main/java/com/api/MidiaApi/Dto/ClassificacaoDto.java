package com.api.MidiaApi.Dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;

public class ClassificacaoDto {
    @NotBlank 
    String classificacao; 
    LocalDate data_insercao; 
    LocalDate data_inativacao;

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
