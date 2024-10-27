package com.fiebtec.FinnTec.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "Pedido")
public class Pedido {
    @Id // pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto incrementar
    private Long id;
    @Column(nullable = true, columnDefinition = "INTEGER")
    private Long qtd_produtos;
    @Column(nullable = true, columnDefinition = "DECIMAL(4,2)")
    private double preco_total;
    @Column(nullable = false, length = 25)
    private String metodo_pagamento;
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

    public Long getQtd_produtos() {
        return qtd_produtos;
    }

    public void setQtd_produtos(Long qtd_produtos) {
        this.qtd_produtos = qtd_produtos;
    }

    public double getPreco_total() {
        return preco_total;
    }

    public void setPreco_total(double preco_total) {
        this.preco_total = preco_total;
    }

    public String getMetodo_pagamento() {
        return metodo_pagamento;
    }

    public void setMetodo_pagamento(String metodo_pagamento) {
        this.metodo_pagamento = metodo_pagamento;
    }

    public boolean isCod_status() {
        return cod_status;
    }

    public void setCod_status(boolean cod_status) {
        this.cod_status = cod_status;
    }

    public String getMensagemErro() {
        return mensagemErro;
    }
    public boolean validarPedido(){

        return isValid;
    }
}
