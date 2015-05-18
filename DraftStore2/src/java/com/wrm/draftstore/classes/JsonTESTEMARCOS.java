/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wrm.draftstore.classes;

/**
 *
 * @author Marcos
 */

import com.wrm.draftstore.servlets.busca.BuscarProduto;

public class JsonTESTEMARCOS {

  public void gerarJson() {

    BuscarProduto bp = new BuscarProduto();

    //instancia um objeto da classe Gson	    	
//    Gson gson = new Gson();

    //pega os dados do filme, converte para JSON e armazena em string
//    String aux = gson.toJson(bp.listarProdutos());

    //imprime os resultados
//    System.out.println(aux);
  }

  public static void main(String[] args) {
    JsonTESTEMARCOS a = new JsonTESTEMARCOS();
    a.gerarJson();
  }

}
