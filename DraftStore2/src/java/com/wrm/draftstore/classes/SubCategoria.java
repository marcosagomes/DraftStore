/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wrm.draftstore.classes;

/**
 *
 * @author wilson.oliveira
 */
public class SubCategoria {

    private int value;
    private String nome;

    public SubCategoria(int value, String nome) {
        this.value = value;
        this.nome = nome;
    }

    public SubCategoria() {
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
