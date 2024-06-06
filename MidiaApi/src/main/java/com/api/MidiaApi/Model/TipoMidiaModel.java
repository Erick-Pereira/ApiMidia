package com.api.MidiaApi.Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import jakarta.persistence.*;

@Entity
@Table(name="tipo_midia")
public class TipoMidiaModel implements Serializable{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, length = 100, unique = true)
    private String tipo_midia;
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
    public String getTipo_midia() {
        return tipo_midia;
    }
    public void setTipo_midia(String tipo_midia) {
        this.tipo_midia = tipo_midia;
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
