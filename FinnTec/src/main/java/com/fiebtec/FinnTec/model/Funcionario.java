package com.fiebtec.FinnTec.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name="Funcionario")
public class Funcionario {
    @Id // pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto incrementar
    private Long id;
    @Column(nullable = false, length = 120)
    private String nome;
    @Column(nullable = false, length = 30)
    private String turno_trabalho;
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

    public boolean isCod_status() {

        return cod_status;
    }


    public void setCod_status(boolean cod_status) {
        this.cod_status = cod_status;
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

    public String getTurno_trabalho() {
        return turno_trabalho;
    }

    public void setTurno_trabalho(String turno_trabalho) {
        this.turno_trabalho = turno_trabalho;
    }

    public String getMensagemErro() {
        return mensagemErro;
    }
    public boolean validarFuncionario(){

        return isValid;
    }
}
