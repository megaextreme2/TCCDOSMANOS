package com.fiebtec.FinnTec.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "Produto")
public class Produto {
    @Id // pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto incrementar
    private Long id;
    @Column(nullable = false, length = 100)
    private String nome;
    @Column(length = 100)
    private String descricao;
    @Column(nullable = true, columnDefinition = "INTEGER")
    private Long qtd_estoque;
    @Column(nullable = false, columnDefinition = "DECIMAL(4,2)")
    private double preco;
    @Column(nullable = false)
    private boolean cod_status =  true;

    @Transient  //Atributos que não estão em uma coluna
    @JsonIgnore
    private String mensagemErro = "";

    @Transient
    @JsonIgnore
    private boolean isValid = true;


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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getQtd_estoque() {
        return qtd_estoque;
    }

    public void setQtd_estoque(Long qtd_estoque) {
        this.qtd_estoque = qtd_estoque;
    }

    public boolean isCod_status() {
        return cod_status;
    }

    public void setCod_status(boolean cod_status) {
        this.cod_status = cod_status;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getMensagemErro() {
        return mensagemErro;
    }
    public boolean validarProduto(){

        return isValid;
    }
}
