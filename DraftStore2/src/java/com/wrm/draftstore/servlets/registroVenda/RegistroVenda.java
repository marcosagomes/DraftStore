/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wrm.draftstore.servlets.registroVenda;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.wrm.draftstore.classes.*;
import com.wrm.draftstore.database.ConexaoBDJavaDB;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Marcos
 */
@WebServlet(name = "RegistroVenda", urlPatterns = {"/Servlet/RegistroVenda"})
public class RegistroVenda extends HttpServlet {

  String timeStamp;

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
    request.getRequestDispatcher("../WEB-INF/registroDeVenda.jsp").forward(request, response);
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
    response.setContentType("text/html;charset=UTF-8");
    request.setCharacterEncoding("UTF-8");

    // 1) OBTEM AS INFORMACOES DO USUARIO DA SESSAO
    // A) CAST DOS PARÂMETROS RECEBIDOS
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    // B) TENTA RECUPERAR A SESSÃO DO USUÁRIO
    HttpSession sessao = httpRequest.getSession();
    Usuario usuario = (Usuario) sessao.getAttribute("usuario");

    String jsonCarrinho = request.getParameter("jsonCarrinho");

    Type listType = new TypeToken<List<ItemVenda>>() {
    }.getType();
    List<ItemVenda> lista = new Gson().fromJson(jsonCarrinho, listType);

    imprimirCarrinho(lista);

    inserirVenda(usuario);
    cadastrarItensVenda(usuario, lista);
    
  }

  public int selectIdVenda() {
    ConexaoBDJavaDB conexaoBD = new ConexaoBDJavaDB("draftstoredb");
    Statement stmt = null;
    Connection conn = null;
    int idVenda = -1;

    String sql = "SELECT ID_VENDA\n"
            + "    FROM TB_VENDA\n"
            + "    WHERE DATA_CRIACAO = '" + timeStamp + "'";
    try {
      conn = conexaoBD.obterConexao();
      stmt = conn.createStatement();
      ResultSet resultados = stmt.executeQuery(sql);

      while (resultados.next()) {
        idVenda = resultados.getInt("ID_VENDA");
      }

    } catch (SQLException | ClassNotFoundException ex) {
      Logger.getLogger(RegistroVenda.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      if (stmt != null) {
        try {
          stmt.close();
        } catch (SQLException ex) {
          Logger.getLogger(RegistroVenda.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
      if (conn != null) {
        try {
          conn.close();
        } catch (SQLException ex) {
          Logger.getLogger(RegistroVenda.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    }
    return idVenda;
  }

  public void inserirVenda(Usuario u) {
    ConexaoBDJavaDB conexaoBD = new ConexaoBDJavaDB("draftstoredb");
    PreparedStatement stmt = null;
    Connection conn = null;

    String sql = "INSERT INTO ADM.TB_VENDA "
            + "(FK_FUNCIONARIO, DATA_CRIACAO, NOME_USUARIO) \n"
            + "	VALUES (?, ?, ?);\n";
    try {
      conn = conexaoBD.obterConexao();
      stmt = conn.prepareStatement(sql);

      // Criando um Timestamp atual do sistema
      Date dataAtual = new Date();
      String timeStamp = new Timestamp(dataAtual.getTime()).toString();

      stmt.setInt(1, Integer.parseInt(u.getIdUsuario()));
      stmt.setString(2, timeStamp);

      stmt.executeUpdate();

      System.out.println("> VENDA incluida com sucesso.");

    } catch (SQLException | ClassNotFoundException ex) {
      Logger.getLogger(RegistroVenda.class.getName()).log(Level.SEVERE, null, ex);
      System.out.println("ERRO! -> " + ex.getMessage());
    } finally {
      if (stmt != null) {
        try {
          stmt.close();
        } catch (SQLException ex) {
          Logger.getLogger(RegistroVenda.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
      if (conn != null) {
        try {
          conn.close();
        } catch (SQLException ex) {
          Logger.getLogger(RegistroVenda.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    }
  }

  public void cadastrarItensVenda(Usuario u, List<ItemVenda> lista) {
    ConexaoBDJavaDB conexaoBD = new ConexaoBDJavaDB("draftstoredb");
    PreparedStatement stmt = null;
    Connection conn = null;

    String sql = "INSERT INTO ADM.TB_ITEM_VENDA "
            + "(FK_PRODUTO, FK_VENDA, QUANTIDADE, PRECO, NOME_PRODUTO) "
            + "VALUES (?, ?, ?, ?, ?)";
    try {
      conn = conexaoBD.obterConexao();
      stmt = conn.prepareStatement(sql);

      for (ItemVenda item : lista) {
        stmt.setInt(1, item.getIdProduto());
        stmt.setInt(2, this.selectIdVenda());
        stmt.setInt(3, item.getQuantidade());
        stmt.setFloat(4, item.getPreco());
        stmt.setString(5, item.getNomeProduto());

        stmt.executeUpdate();
      }

      System.out.println("> VENDA incluida com sucesso.");

    } catch (SQLException | ClassNotFoundException ex) {
      Logger.getLogger(RegistroVenda.class.getName()).log(Level.SEVERE, null, ex);
      System.out.println("ERRO! -> " + ex.getMessage());
    } finally {
      if (stmt != null) {
        try {
          stmt.close();
        } catch (SQLException ex) {
          Logger.getLogger(RegistroVenda.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
      if (conn != null) {
        try {
          conn.close();
        } catch (SQLException ex) {
          Logger.getLogger(RegistroVenda.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    }
  }

  public void imprimirCarrinho(List<ItemVenda> lista) {
    System.out.println("---- Carrinho JSON ----");
    for (ItemVenda item : lista) {
      System.out.print("item.nomeProduto: " + item.getNomeProduto());
      System.out.print("item.preco: " + item.getPreco());
      System.out.print("item.idProduto: " + item.getIdProduto());
      System.out.println("item.quantidade: " + item.getQuantidade());
    }
    System.out.println("-----------------------");
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
