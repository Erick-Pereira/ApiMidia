package com.api.MidiaApi.Model;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.*;

@Entity
@Table(name="midia")
public class MidiaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, length = 100, unique = true)
    private String midia;
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

    public String getMidia() {
        return midia;
    }

    public void setMidia(String midia) {
        this.midia = midia;
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
