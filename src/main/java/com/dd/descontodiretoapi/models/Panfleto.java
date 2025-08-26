package com.dd.descontodiretoapi.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.Date;

@Entity
@Table(name = "panfletos")
public class Panfleto {
    private Long id;
    private String fotoUrl;
    private Date dataExpiracao;

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
}
