/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wrm.draftstore.servlets.busca;

import com.wrm.draftstore.classes.Produto;
import com.wrm.draftstore.database.ConexaoBDJavaDB;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author fernando.tsuda
 */
@WebServlet(name = "BuscarProduto", 
        urlPatterns = {"/Servlet/BuscarProduto"})
public class BuscarProduto extends HttpServlet {

  public List<Produto> listarProdutos() {
    ConexaoBDJavaDB conexaoBD = new ConexaoBDJavaDB("draftstoredb");
    Statement stmt = null;
    Connection conn = null;

    String sql = "SELECT TIPO_PRODUTO, MARCA, MODELO, PRECO_VENDA FROM TB_PRODUTO";
    try {
      conn = conexaoBD.obterConexao();
      stmt = conn.createStatement();
      ResultSet resultados = stmt.executeQuery(sql);
      
      List<Produto> lista = new ArrayList<>();

      while (resultados.next()) {
          Produto p = new Produto();
          p.setTipoProduto(resultados.getString("TIPO_PRODUTO"));
          p.setMarca(resultados.getString("MARCA"));
          p.setModelo(resultados.getString("MODELO"));
          p.setPrecoVenda(resultados.getFloat("PRECO_VENDA"));
          
          lista.add(p);
      }
      
     return lista;

    } catch (SQLException | ClassNotFoundException ex) {
      Logger.getLogger(BuscarProduto.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      if (stmt != null) {
        try {
          stmt.close();
        } catch (SQLException ex) {
          Logger.getLogger(BuscarProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
      if (conn != null) {
        try {
          conn.close();
        } catch (SQLException ex) {
          Logger.getLogger(BuscarProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    }
    return null;
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
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        List<Produto> produtosLista = listarProdutos();
        request.setAttribute("lista", produtosLista);
        
        RequestDispatcher rd = request.getRequestDispatcher("../WEB-INF/buscarProduto.jsp");
        rd.forward(request, response);
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

}
