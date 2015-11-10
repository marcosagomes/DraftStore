/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wrm.draftstore.auxiliares;

import com.google.gson.Gson;
import com.wrm.draftstore.classes.Produto;
import com.wrm.draftstore.database.ConexaoBDJavaDB;
import com.wrm.draftstore.servlets.busca.BuscarFornecedor;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.JsonException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ramonhonorio
 */
@WebServlet(name = "JsonVendasServlet", urlPatterns = {"/Servlet/JsonVendasServlet"})
public class JsonVendasServlet extends HttpServlet {

  /**
   * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
   * methods.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    
    List<Produto> fornecedoresLista = listarProdutos();
    request.setAttribute("lista", fornecedoresLista);
    
    String json = new Gson().toJson(fornecedoresLista);
    PrintWriter out = response.getWriter();
    
    try {
      response.setContentType("application/json");
//      out.write(json);
      out.print(json);
//      System.out.println(json);
    } catch (JsonException e) {
      System.out.println("ERRO! -> [Json]: "+e);
    }
    
    FileWriter file = new FileWriter("fornec.json");
        try {
            file.write(json);
//            System.out.println("Successfully Copied JSON Object to File...");
//            System.out.println("\nJSON Object: " + json);
 
        } catch (IOException e) {
            e.printStackTrace();
 
        } finally {
            file.flush();
            file.close();
        }
    
  }

  // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
  /**
   * Handles the HTTP <code>GET</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    processRequest(request, response);
  }

  /**
   * Handles the HTTP <code>POST</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    processRequest(request, response);
  }

  /**
   * Returns a short description of the servlet.
   *
   * @return a String containing servlet description
   */
  @Override
  public String getServletInfo() {
    return "Short description";
  }// </editor-fold>

  public List<Produto> listarProdutos() {
    ConexaoBDJavaDB conexaoBD = new ConexaoBDJavaDB("draftCliente");
    Statement stmt = null;
    Connection conn = null;

    String sql = "SELECT   TB_PRODUTO.ID_PRODUTO,\n" +
                  "        TB_PRODUTO.PRECO_VENDA,\n" +
                  "        TB_PRODUTO.PERCENTUAL_LUCRO,\n" +
                  "        TB_PRODUTO.MODELO,\n" +
                  "        TB_PRODUTO.MARCA,\n" +
                  "        TB_PRODUTO.TIPO_PRODUTO,\n" +
                  "        TB_PRODUTO.CUSTO,\n" +
                  "        TB_PRODUTO.FK_FORNECEDOR,\n" +
                  "        TB_PRODUTO.FK_FUNCIONARIO,\n" +
                  "        TB_PRODUTO.DATA_CRIACAO,\n" +
                  "        TB_PRODUTO.NOME_FORNECEDOR,\n" +
                  "        TB_PRODUTO.NOME_USUARIO\n" +
                  "    FROM TB_PRODUTO";
    try {
      conn = conexaoBD.obterConexao();
      stmt = conn.createStatement();
      ResultSet resultados = stmt.executeQuery(sql);

      List<Produto> lista = new ArrayList<>();

      while (resultados.next()) {
        Produto p = new Produto();
        p.setIdProduto(resultados.getInt("ID_PRODUTO"));
        p.setPrecoVenda(resultados.getFloat("PRECO_VENDA"));
        p.setPercentualLucro(resultados.getFloat("PERCENTUAL_LUCRO"));
        p.setModelo(resultados.getString("MODELO"));
        p.setMarca(resultados.getString("MARCA"));
        p.setTipoProduto(resultados.getString("TIPO_PRODUTO"));
        p.setCusto(resultados.getFloat("CUSTO"));
        p.setIdFornecedor(resultados.getInt("FK_FORNECEDOR"));
        p.setIdFuncionario(resultados.getInt("FK_FUNCIONARIO"));
        p.setDataCriacao(resultados.getString("DATA_CRIACAO"));
        p.setNomeFornecedor(resultados.getString("NOME_FORNECEDOR"));
        p.setNomeUsuario(resultados.getString("NOME_USUARIO"));
        
        lista.add(p);
        
      }

      return lista;

    } catch (SQLException | ClassNotFoundException ex) {
      Logger.getLogger(BuscarFornecedor.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      if (stmt != null) {
        try {
          stmt.close();
        } catch (SQLException ex) {
          Logger.getLogger(BuscarFornecedor.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
      if (conn != null) {
        try {
          conn.close();
        } catch (SQLException ex) {
          Logger.getLogger(BuscarFornecedor.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    }
    return null;
  }
  
}
