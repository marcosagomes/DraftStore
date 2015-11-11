/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wrm.draftstore.classes;

import java.util.Date;

/**
 *
 * @author ramon.ahonorio
 */
public class Produto {

    private int idProduto;

    private float precoVenda;

    private float precoPromo;

    public float getPrecoPromo() {
        return precoPromo;
    }

    public void setPrecoPromo(float precoPromo) {
        this.precoPromo = precoPromo;
    }

    private float percentualLucro;

    private String modelo;

    private String marca;

    private float custo;

    private int idFornecedor;

    private int idCategoria;

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public int getIdSubCategoria() {
        return idSubCategoria;
    }

    public void setIdSubCategoria(int idSubCategoria) {
        this.idSubCategoria = idSubCategoria;
    }

    private int idSubCategoria;

    private String dataCriacao;

    private String nomeFornecedor;

    private String nomeUsuario;

    private int idFuncionario;

    private int quantidade;

    private String descricao;

    private String caminhoImagem;

    private String descImagem;

    private Date dataEvento;

    public Date getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(Date dataEvento) {
        this.dataEvento = dataEvento;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getCaminhoImagem() {
        return caminhoImagem;
    }

    public void setCaminhoImagem(String caminhoImagem) {
        this.caminhoImagem = caminhoImagem;
    }

    public String getDescImagem() {
        return descImagem;
    }

    public void setDescImagem(String descImagem) {
        this.descImagem = descImagem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getNomeFornecedor() {
        return nomeFornecedor;
    }

    public void setNomeFornecedor(String nomeFornecedor) {
        this.nomeFornecedor = nomeFornecedor;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

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

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
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

    public Produto(int idProduto, float precoVenda, float precoPromo, float percentualLucro, String modelo, String marca, float custo, int idFornecedor, int idCategoria, int idSubCategoria, String dataCriacao, String nomeFornecedor, String nomeUsuario, int idFuncionario, int quantidade, String descricao, String caminhoImagem, String descImagem, Date dataEvento) {
        this.idProduto = idProduto;
        this.precoVenda = precoVenda;
        this.precoPromo = precoPromo;
        this.percentualLucro = percentualLucro;
        this.modelo = modelo;
        this.marca = marca;
        this.custo = custo;
        this.idFornecedor = idFornecedor;
        this.idCategoria = idCategoria;
        this.idSubCategoria = idSubCategoria;
        this.dataCriacao = dataCriacao;
        this.nomeFornecedor = nomeFornecedor;
        this.nomeUsuario = nomeUsuario;
        this.idFuncionario = idFuncionario;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.caminhoImagem = caminhoImagem;
        this.descImagem = descImagem;
        this.dataEvento = dataEvento;
    }

    public Produto() {
    }

}
