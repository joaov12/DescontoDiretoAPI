package com.dd.descontodiretoapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.util.Date;

@Entity
@Table(name = "panfletos")
public class Panfleto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fotoUrl;
    private Date dataExpiracao;


    @JsonIgnore
    @ManyToOne
    @NotNull
    private Comercio comercio;


    public Panfleto() {
    }

    public Panfleto(Long id, String fotoUrl, Date dataExpiracao) {
        this.id = id;
        this.fotoUrl = fotoUrl;
        this.dataExpiracao = dataExpiracao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }

    public Date getDataExpiracao() {
        return dataExpiracao;
    }

    public void setDataExpiracao(Date dataExpiracao) {
        this.dataExpiracao = dataExpiracao;
    }

    public Long getComercioId() {
        return comercio != null ? comercio.getId() : null;
    }

    public Comercio getComercio() {
        return comercio;
    }

    public void setComercio(Comercio comercio) {
        this.comercio = comercio;
    }
}
