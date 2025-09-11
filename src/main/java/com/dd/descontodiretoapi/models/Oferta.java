package com.dd.descontodiretoapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ofertas")
public class Oferta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Float preco;
    private Date validade;
    private LocalDateTime dataPostagem;

    @ManyToOne
    @NotNull
    private Produto produto;
    @JsonIgnore
    @ManyToOne
    @NotNull
    private Comercio comercio;

    @JsonIgnore
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
        this.dataPostagem = LocalDateTime.now();
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

    // Getter que conta quantos clientes favoritaram esta oferta
    public Integer getLikes() {
        return clientes != null ? clientes.size() : 0;
    }

    public LocalDateTime getDataPostagem() {
        return dataPostagem;
    }

    public void setDataPostagem(LocalDateTime dataPostagem) {
        this.dataPostagem = dataPostagem;
    }

    // Getter customizado para retornar apenas o ID do comércio
    public Long getComercioId() {
        return comercio != null ? comercio.getId() : null;
    }
}
