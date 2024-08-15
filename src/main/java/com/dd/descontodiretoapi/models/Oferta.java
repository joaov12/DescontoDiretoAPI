package com.dd.descontodiretoapi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ofertas")
public class Oferta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Float preco;
    private Date validade;

    @ManyToOne
    @NotNull
    private Produto produto;

    @ManyToOne
    @NotNull
    private Comercio comercio;

    @ManyToMany(mappedBy = "ofertasPreferidas")
    private List<Cliente> clientes;

    public Oferta() {
    }

    public Oferta(Comercio comercio, Long id, Float preco, Produto produto, Date validade) {
        this.comercio = comercio;
        this.id = id;
        this.preco = preco;
        this.produto = produto;
        this.validade = validade;
    }

    public @NotNull Comercio getComercio() {
        return comercio;
    }

    public void setComercio(@NotNull Comercio comercio) {
        this.comercio = comercio;
    }

    public Date getValidade() {
        return validade;
    }

    public void setValidade(Date validade) {
        this.validade = validade;
    }

    public @NotNull Produto getProduto() {
        return produto;
    }

    public void setProduto(@NotNull Produto produto) {
        this.produto = produto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }
}
