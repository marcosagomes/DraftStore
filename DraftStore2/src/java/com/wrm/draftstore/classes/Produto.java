/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wrm.draftstore.classes;

/**
 *
 * @author ramon.ahonorio
 */
public class Produto {
  
    private float precoVenda;
    
    private float percentualLucro;
    
    private String modelo;
    
    private String marca;
    
    private String tipoProduto;
    
    private float custo;
    
    private int idFornecedor;

    public float getPercentualLucro() {
        return percentualLucro;
    }
    public void setPercentualLucro(float percentualLucro) {
        this.percentualLucro = percentualLucro;
    }
    public float getPrecoVenda() {
        return precoVenda;
    }
    public void setPrecoVenda(float precoVenda) {
        this.precoVenda = precoVenda;
    }
    public String getTipoProduto() {
        return tipoProduto;
    }
    public void setTipoProduto(String tipoProduto) {
        this.tipoProduto = tipoProduto;
    }
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }

  public float getCusto() {
    return custo;
  }

  public void setCusto(float custo) {
    this.custo = custo;
  }

  public int getIdFornecedor() {
    return idFornecedor;
  }

  public void setIdFornecedor(int idFornecedor) {
    this.idFornecedor = idFornecedor;
  }

  public Produto(float precoVenda, float percentualLucro, String modelo, String marca, String tipoProduto, float custo, int idFornecedor) {
    this.precoVenda = precoVenda;
    this.percentualLucro = percentualLucro;
    this.modelo = modelo;
    this.marca = marca;
    this.tipoProduto = tipoProduto;
    this.custo = custo;
    this.idFornecedor = idFornecedor;
  }

  public Produto() {
  }
  
}
