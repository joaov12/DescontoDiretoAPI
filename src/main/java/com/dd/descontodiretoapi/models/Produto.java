package com.dd.descontodiretoapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String medida;
    private String unidadeMedida;
    private String categoria; // Mudar categoria ara sendo um ENUM
    private String fotoUrl;

    @JsonIgnore
    @OneToMany(mappedBy = "produto")
    private List<Oferta> ofertas;

    public Produto() {
    }

    public Produto(String categoria, Long id, String medida, String unidadeMedida, String nome) {
        this.categoria = categoria;
        this.id = id;
        this.medida = medida;
        this.unidadeMedida = unidadeMedida;
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
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

    public List<Oferta> getOfertas() {
        return ofertas;
    }
    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }

    public void setOfertas(List<Oferta> ofertas) {
        this.ofertas = ofertas;
    }
}
