/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wrm.draftstore.auxiliares;

import com.google.gson.Gson;
import com.wrm.draftstore.classes.*;
import com.wrm.draftstore.database.ConexaoBDMySQL;
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
@WebServlet(name = "JsonFuncionariosServlet", urlPatterns = {"/Servlet/JsonFuncionariosServlet"})
public class JsonFuncionariosServlet extends HttpServlet {

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
    
    List<Funcionario> funcionariosLista = listarFuncionarios();
    request.setAttribute("lista", funcionariosLista);
    
    String json = new Gson().toJson(funcionariosLista);
    PrintWriter out = response.getWriter();
    
    try {
      response.setContentType("application/json");
//      out.write(json);
      out.print(json);
//      System.out.println(json);
    } catch (JsonException e) {
      System.out.println("ERRO! -> [Json]: "+e);
    }
    
    FileWriter file = new FileWriter("func.json");
        try {
            file.write(json);
 
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

  public List<Funcionario> listarFuncionarios() {
    ConexaoBDMySQL conexaoBD = new ConexaoBDMySQL("DraftOfficeDB");
    Statement stmt = null;
    Connection conn = null;

    String sql = "SELECT ID_FUNCIONARIO,\n" +
                "        NOME,\n" +
                "        CPF,\n" +
                "        EMAIL\n" +
                "    FROM TB_FUNCIONARIO "
            + " WHERE ATIVO = TRUE";
    try {
      conn = conexaoBD.obterConexao();
      stmt = conn.createStatement();
      ResultSet resultados = stmt.executeQuery(sql);

      List<Funcionario> lista = new ArrayList<>();

      while (resultados.next()) {
        Funcionario f = new Funcionario();
        f.setIdFuncionario(resultados.getString("ID_FUNCIONARIO"));
        f.setNome(resultados.getString("NOME"));
        f.setCpf(resultados.getString("CPF"));
        f.setEmail(resultados.getString("EMAIL"));
        
        lista.add(f);
      }

      return lista;

    } catch (SQLException | ClassNotFoundException ex) {
      Logger.getLogger(JsonFuncionariosServlet.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      if (stmt != null) {
        try {
          stmt.close();
        } catch (SQLException ex) {
          Logger.getLogger(JsonFuncionariosServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
      if (conn != null) {
        try {
          conn.close();
        } catch (SQLException ex) {
          Logger.getLogger(JsonFuncionariosServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    }
    return null;
  }
  
}
