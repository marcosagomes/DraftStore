/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wrm.draftstore.classes;

/**
 *
 * @author Maikon Evangelista
 */
public class Funcionario {

    //Construtor
    public Funcionario(String nome, String dtNascimento, String sexo, String cpf, String rg, String telefone, String celular, int cargo, String email, String senha) {
        this.nome = nome;
        this.dtNascimento = dtNascimento;
        this.sexo = sexo;
        this.cpf = cpf;
        this.rg = rg;
        this.telefone = telefone;
        this.celular = celular;
        this.cargo = cargo;
        this.email = email;
        this.senha = senha;
    }
    
    
    public Funcionario(){
    }
    
    
        //Atributos
        private String idFuncionario;
        private String nome;
        private String dtNascimento;
        private String sexo;
        private String cpf;
        private String rg;
        private String telefone;
        private String celular;
        private int cargo;
        private String email;
        private String senha;
        private String fkPapel;

 
        
        
        
        //Métodos
        
    public String getFkPapel() {
        return fkPapel;
    }

    public void setFkPapel(String fkPapel) {
        this.fkPapel = fkPapel;
    }
        
        
    public String getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(String idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(String dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public int getCargo() {
        return cargo;
    }

    public void setCargo(int cargo) {
        this.cargo = cargo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    

        
}
