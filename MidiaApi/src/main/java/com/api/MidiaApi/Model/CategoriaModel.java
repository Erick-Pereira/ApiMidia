package com.api.MidiaApi.Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table(name="categoria")
public class CategoriaModel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, length = 100, unique = true)
    private String categoria;
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
    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
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
