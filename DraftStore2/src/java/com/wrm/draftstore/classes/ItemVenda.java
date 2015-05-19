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
public class ItemVenda {

  private int idItemVenda;

  public int getIdItemVenda() {
    return idItemVenda;
  }

  public void setIdItemVenda(int idItemVenda) {
    this.idItemVenda = idItemVenda;
  }

  private int idProduto;

  public int getIdProduto() {
    return idProduto;
  }

  public void setIdProduto(int idProduto) {
    this.idProduto = idProduto;
  }

  private int idVenda;

  public int getIdVenda() {
    return idVenda;
  }

  public void setIdVenda(int idVenda) {
    this.idVenda = idVenda;
  }

  private int quantidade;

  public int getQuantidade() {
    return quantidade;
  }

  public void setQuantidade(int quantidade) {
    this.quantidade = quantidade;
  }

  private float preco;

  public float getPreco() {
    return preco;
  }

  public void setPreco(float preco) {
    this.preco = preco;
  }

  private String nomeProduto;

  public String getNomeProduto() {
    return nomeProduto;
  }

  public void setNomeProduto(String nomeProduto) {
    this.nomeProduto = nomeProduto;
  }

  public ItemVenda() {
  }

  public ItemVenda(int idItemVenda, int idProduto, int idVenda, int quantidade, float preco, String nomeProduto) {
    this.idItemVenda = idItemVenda;
    this.idProduto = idProduto;
    this.idVenda = idVenda;
    this.quantidade = quantidade;
    this.preco = preco;
    this.nomeProduto = nomeProduto;
  }

  public ItemVenda(int idProduto, int idVenda, int quantidade, float preco, String nomeProduto) {
    this.idProduto = idProduto;
    this.idVenda = idVenda;
    this.quantidade = quantidade;
    this.preco = preco;
    this.nomeProduto = nomeProduto;
  } 

}
