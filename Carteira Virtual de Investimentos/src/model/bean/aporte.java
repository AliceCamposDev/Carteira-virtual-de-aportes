/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

import java.util.Date;

/**
 *
 * @author Gabriel
 */
public class aporte {
    private String nome;
    private double valor;
    private Date data;
    private double valorAtual;  
    private double lucro;  
    private double retorno;

    public aporte(String nome, double valor, Date data, double valorAtual, double lucro, double retorno) {
        this.nome = nome;
        this.valor = valor;
        this.data = data;
        this.valorAtual = valorAtual;
        this.lucro = lucro;
        this.retorno = retorno;
    }
    
    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public double getValorAtual() {
        return valorAtual;
    }

    public void setValorAtual(double valorAtual) {
        this.valorAtual = valorAtual;
    }

    public double getLucro() {
        return lucro;
    }

    public void setLucro(double lucro) {
        this.lucro = lucro;
    }

    public double getRetorno() {
        return retorno;
    }

    public void setRetorno(double retorno) {
        this.retorno = retorno;
    }

    
}
