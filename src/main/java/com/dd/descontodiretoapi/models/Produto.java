package com.dd.descontodiretoapi.models;

import jakarta.persistence.*;

@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String medida;
    private String categoria; // Mudar categoria ara sendo um ENUM
    private String descricao;
    // private Long id_comercio; necessário?


    public Produto() {
    }

    public Produto(String categoria, String descricao, Long id, String medida, String nome) {
        this.categoria = categoria;
        this.descricao = descricao;
        this.id = id;
        this.medida = medida;
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
