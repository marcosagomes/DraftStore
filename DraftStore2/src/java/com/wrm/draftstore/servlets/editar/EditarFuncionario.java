/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wrm.draftstore.servlets.editar;

import com.wrm.draftstore.classes.Funcionario;
import com.wrm.draftstore.classes.Usuario;
import com.wrm.draftstore.database.ConexaoBDMySQL;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ramon.ahonorio
 */
@WebServlet(name = "EditarFuncionario", urlPatterns = {"/Servlet/EditarFuncionario"})
public class EditarFuncionario extends HttpServlet {

  static String idFuncionario;

  public void editarFuncionario(Funcionario f, Usuario u) {
    ConexaoBDMySQL conexaoBD = new ConexaoBDMySQL("DraftOfficeDB");
    PreparedStatement stmt = null;
    Connection conn = null;
    Date data = new Date();
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    try {
      data = (java.util.Date) format.parse(f.getDtNascimento());
    } catch (ParseException ex) {
      Logger.getLogger(EditarFuncionario.class.getName()).log(Level.SEVERE, null, ex);
    }
    String dtFormatada = new Timestamp(data.getTime()).toString();

    String updateSql = "UPDATE TB_FUNCIONARIO\n"
            + "SET SENHA = '" + f.getSenha() + "', \n"
            + "NOME = '" + f.getNome() + "', \n"
            + "DATA_NASCIMENTO = '" + dtFormatada + "', \n"
            + "SEXO = '" + f.getSenha() + "', \n"
            + "CPF = '" + f.getCpf() + "', \n"
            + "RG = '" + f.getRg() + "', \n"
            + "FK_PAPEL = " + f.getCargo() + ", \n"
            + "TELEFONE = '" + f.getTelefone() + "', \n"
            + "CELULAR = '" + f.getCelular() + "', \n"
            + "EMAIL = '" + f.getEmail() + "', \n"
            + "DATA_CRIACAO = '" + new Timestamp(new Date().getTime()).toString() + "'\n"
            + "WHERE ID_FUNCIONARIO = " + idFuncionario;

    try {
      conn = conexaoBD.obterConexao();
      stmt = conn.prepareStatement(updateSql);
      stmt.execute();

      System.out.println("> Funcionario editado com sucesso.");

    } catch (SQLException | ClassNotFoundException ex) {
      Logger.getLogger(EditarFuncionario.class.getName()).log(Level.SEVERE, null, ex);
      System.out.println("ERRO! -> " + ex.getMessage());
    } finally {
      if (stmt != null) {
        try {
          stmt.close();
        } catch (SQLException ex) {
          Logger.getLogger(EditarFuncionario.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
      if (conn != null) {
        try {
          conn.close();
        } catch (SQLException ex) {
          Logger.getLogger(EditarFuncionario.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    }
  }

  public Funcionario buscarFuncionario(Object idFuncionario, Usuario u) {
    ConexaoBDMySQL conexaoBD = new ConexaoBDMySQL("DraftOfficeDB");
    Statement stmt = null;
    Connection conn = null;

    String sql = "SELECT ID_FUNCIONARIO,\n"
            + "        SENHA,\n"
            + "        NOME,\n"
            + "        DATA_NASCIMENTO,\n"
            + "        SEXO,\n"
            + "        CPF,\n"
            + "        RG,\n"
            + "        FK_PAPEL,\n"
            + "        TELEFONE,\n"
            + "        CELULAR,\n"
            + "        EMAIL,\n"
            + "        DATA_CRIACAO\n"
            + "    FROM TB_FUNCIONARIO\n"
            + "    WHERE ID_FUNCIONARIO = " + idFuncionario.toString();
    try {
      conn = conexaoBD.obterConexao();
      stmt = conn.createStatement();
      ResultSet resultados = stmt.executeQuery(sql);

      Funcionario f = new Funcionario();
      while (resultados.next()) {
        f.setIdFuncionario(resultados.getString("ID_FUNCIONARIO"));
        f.setSenha(resultados.getString("SENHA"));
        f.setNome(resultados.getString("NOME"));
        Timestamp dtTimestamp = resultados.getTimestamp("DATA_NASCIMENTO");
        f.setDtNascimento(new SimpleDateFormat("dd/MM/yyyy").format(dtTimestamp));
        f.setSexo(resultados.getString("SEXO"));
        f.setCpf(resultados.getString("CPF"));
        f.setRg(resultados.getString("RG"));
        f.setCargo(resultados.getInt("FK_PAPEL"));
        f.setTelefone(resultados.getString("TELEFONE"));
        f.setCelular(resultados.getString("CELULAR"));
        f.setEmail(resultados.getString("EMAIL"));
      }
      return f;
    } catch (SQLException | ClassNotFoundException ex) {
      Logger.getLogger(EditarFuncionario.class.getName()).log(Level.SEVERE, null, ex);
      System.out.println("ERRO SQL! -> " + ex.getMessage());
    } finally {
      if (stmt != null) {
        try {
          stmt.close();
        } catch (SQLException ex) {
          Logger.getLogger(EditarFuncionario.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
      if (conn != null) {
        try {
          conn.close();
        } catch (SQLException ex) {
          Logger.getLogger(EditarFuncionario.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    }
    return null;
  }

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

    response.setContentType("text/html;charset=UTF-8");
    request.setCharacterEncoding("UTF-8");

    // 1) OBTEM AS INFORMACOES DO USUARIO DA SESSAO
    // A) CAST DOS PARÂMETROS RECEBIDOS
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    // B) TENTA RECUPERAR A SESSÃO DO USUÁRIO
    HttpSession sessao = httpRequest.getSession();
    Usuario usuario = (Usuario) sessao.getAttribute("usuario");

    idFuncionario = request.getParameter("idFuncionario");
//        System.out.println("ID Funcionario: "+idFuncionario);

    Funcionario f = buscarFuncionario(idFuncionario, usuario);
    request.setAttribute("Funcionario", f);
    request.setAttribute("idFuncionario", idFuncionario);

    RequestDispatcher rd = request.getRequestDispatcher("../WEB-INF/editarFuncionario.jsp");
    rd.forward(request, response);
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

    response.setContentType("text/html;charset=UTF-8");
    request.setCharacterEncoding("UTF-8");

    String nome = request.getParameter("Nome");
    String dtNasc = request.getParameter("Data");
    String sexo = request.getParameter("Sexo");
    String cpf = request.getParameter("CPF");
    String rg = request.getParameter("RG");
    String telefone = request.getParameter("Telefone");
    String celular = request.getParameter("Celular");
    int cargo = Integer.parseInt(request.getParameter("Cargo"));
    String email = request.getParameter("Email");
    String senha = request.getParameter("Senha");

    Funcionario f = new Funcionario(nome, dtNasc, sexo, cpf, rg, telefone, celular, cargo, email, senha);

    // 1) OBTEM AS INFORMACOES DO USUARIO DA SESSAO
    // A) CAST DOS PARÂMETROS RECEBIDOS
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    // B) TENTA RECUPERAR A SESSÃO DO USUÁRIO
    HttpSession sessao = httpRequest.getSession();
    Usuario usuario = (Usuario) sessao.getAttribute("usuario");

    editarFuncionario(f, usuario);

    System.out.println("> Funcionario alterado por [" + usuario.getNomeDoFuncionario() + "].");
    response.sendRedirect("BuscarFuncionario");
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
