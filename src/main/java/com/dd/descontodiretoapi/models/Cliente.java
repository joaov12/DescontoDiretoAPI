package com.dd.descontodiretoapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String senha;
    private String telefone;
    @ManyToMany
    private List<Oferta> ofertasPreferidas;

    public Cliente() {
    }

    public Cliente(Long id, String email, String nome, String senha, String telefone) {
        this.id = id;
        this.email = email;
        this.nome = nome;
        this.senha = senha;
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<Oferta> getOfertasPreferidas() {
        return ofertasPreferidas;
    }

    public void setOfertasPreferidas(List<Oferta> ofertasPreferidas) {
        this.ofertasPreferidas = ofertasPreferidas;
    }
}
