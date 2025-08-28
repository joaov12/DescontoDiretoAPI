package com.dd.descontodiretoapi.models;

import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "comercios")
public class Comercio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String endereco;
    private String telefone;
    private String categoria;
    private String email;
    private String senha;
    private LocalTime horarioAbertura;
    private LocalTime horarioFechamento;
    private Boolean aberto;


    @Column(name = "foto_url")
    private String fotoUrl;

    @OneToMany(mappedBy = "comercio")
    private List<Oferta> ofertas;

    public Comercio() {
    }

    public Comercio(String categoria, String telefone, String nome, Long id, String endereco, String email, String senha, LocalTime horarioAbertura, LocalTime horarioFechamento, Boolean aberto) {
        this.categoria = categoria;
        this.telefone = telefone;
        this.nome = nome;
        this.id = id;
        this.endereco = endereco;
        this.email = email;
        this.senha = senha;
        this.horarioAbertura = horarioAbertura;
        this.horarioFechamento = horarioFechamento;
        this.aberto = aberto;
    }



    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
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

    public void setOfertas(List<Oferta> ofertas) {
        this.ofertas = ofertas;
    }

    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }

    public LocalTime getHorarioAbertura() {
        return horarioAbertura;
    }
    public void setHorarioAbertura(LocalTime horarioAbertura) {
        this.horarioAbertura = horarioAbertura;
    }
    public LocalTime getHorarioFechamento() {
        return horarioFechamento;
    }
    public void setHorarioFechamento(LocalTime horarioFechamento) {
        this.horarioFechamento = horarioFechamento;
    }
    public Boolean getAberto() {
        if (horarioAbertura != null && horarioFechamento != null) {
            LocalTime now = LocalTime.now();
            return now.isAfter(horarioAbertura) && now.isBefore(horarioFechamento);
        }
        return aberto;
    }
    public void setAberto(Boolean aberto) {
        this.aberto = aberto;
    }
}
