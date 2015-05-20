/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wrm.draftstore.classes;

/**
 *
 * @author ramonhonorio
 */
public class Venda {
  
  private String idVenda;

  public String getIdVenda() {
    return idVenda;
  }

  public void setIdVenda(String idVenda) {
    this.idVenda = idVenda;
  }

  private String idFuncionario;

  public String getIdFuncionario() {
    return idFuncionario;
  }

  public void setIdFuncionario(String idFuncionario) {
    this.idFuncionario = idFuncionario;
  }

  private String dataCriacao;

  public String getDataCriacao() {
    return dataCriacao;
  }

  public void setDataCriacao(String dataCriacao) {
    this.dataCriacao = dataCriacao;
  }

  private String nomeUsuario;

  public String getNomeUsuario() {
    return nomeUsuario;
  }

  public void setNomeUsuario(String nomeUsuario) {
    this.nomeUsuario = nomeUsuario;
  }

  public Venda(String idVenda, String idFuncionario, String dataCriacao, String nomeUsuario) {
    this.idVenda = idVenda;
    this.idFuncionario = idFuncionario;
    this.dataCriacao = dataCriacao;
    this.nomeUsuario = nomeUsuario;
  }

}
