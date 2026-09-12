/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sisaudcom.crud.javaswing.model;

import java.util.Date;

/**
 *
 * @author neros
 */
public class Funcionario {

    private int id;
    private String nome;
    private Date dataDeAdmissao;
    private double salario;
    private boolean status;

    public Funcionario() {
    }

    public Funcionario(int id, String nome, Date dataDeAdmissao,
            double salario, boolean status) {
        this.id = id;
        this.nome = nome;
        this.dataDeAdmissao = dataDeAdmissao;
        this.salario = salario;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataDeAdmissao() {
        return dataDeAdmissao;
    }

    public void setDataDeAdmissao(Date dataDeAdmissao) {
        this.dataDeAdmissao = dataDeAdmissao;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
