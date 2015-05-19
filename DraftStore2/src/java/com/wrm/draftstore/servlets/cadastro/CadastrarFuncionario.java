/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wrm.draftstore.servlets.cadastro;

import com.wrm.draftstore.classes.Funcionario;
import com.wrm.draftstore.classes.Usuario;
import com.wrm.draftstore.database.ConexaoBDJavaDB;
import com.wrm.draftstore.servlets.busca.BuscarFornecedor;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
@WebServlet(name = "CadastrarFuncionario", urlPatterns = {"/Servlet/CadastrarFuncionario"})
public class CadastrarFuncionario extends HttpServlet {
    
    //Metódo para tentar comparar CARGO > FK_PAPEL
    public List<Funcionario> listarFuncionarios() {
    ConexaoBDJavaDB conexaoBD = new ConexaoBDJavaDB("draftstoredb");
    Statement stmt = null;
    Connection conn = null;

    String sql = "SELECT NOME, FK_PAPEL FROM TB_FUNCIONARIO";
    try {
      conn = conexaoBD.obterConexao();
      stmt = conn.createStatement();
      ResultSet resultados = stmt.executeQuery(sql);
      
      List<Funcionario> lista = new ArrayList<>();

      while (resultados.next()) {
          Funcionario f = new Funcionario();
          f.setNome(resultados.getString("NOME"));
          f.setFkPapel(resultados.getString("FK_PAPEL"));
          lista.add(f);
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

    public void cadastrarFuncionario(Funcionario f, Usuario u){
        ConexaoBDJavaDB conexaoBD
            = new ConexaoBDJavaDB("draftstoredb");
        PreparedStatement stmt = null;
        Connection conn = null;
        
        String sql = "INSERT INTO TB_FUNCIONARIO " // Notar que antes de fechar aspas tem espaço em branco!
                + "(SENHA, NOME, DATA_NASCIMENTO, SEXO, CPF, RG, FK_PAPEL, TELEFONE, CELULAR, EMAIL, DATA_CRIACAO) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
        conn = conexaoBD.obterConexao();
        stmt = conn.prepareStatement(sql);
  
        stmt.setString(1, f.getSenha());
        stmt.setString(2, f.getNome());
        stmt.setString(3, f.getDtNascimento());
        stmt.setString(4, f.getSexo());
        stmt.setString(5, f.getCpf());
        stmt.setString(6, f.getRg());
        stmt.setString(7, f.getCargo());
        stmt.setString(8, f.getTelefone());
        stmt.setString(9, f.getCelular());
        stmt.setString(10, f.getEmail());
        
        Date dataAtual = new Date();
        String timeStamp = new Timestamp(dataAtual.getTime()).toString();
        
        stmt.setString(11, timeStamp);
        
        stmt.executeUpdate();
        
        System.out.println("> Funcionario incluido com sucesso.");

      } catch (SQLException | ClassNotFoundException ex) {
        Logger.getLogger(CadastrarFuncionario.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("-> " + ex.getMessage());
      } finally {
        if (stmt != null) {
          try {
            stmt.close();
          } catch (SQLException ex) {
            Logger.getLogger(CadastrarFuncionario.class.getName()).log(Level.SEVERE, null, ex);
          }
        }
        if (conn != null) {
          try {
            conn.close();
          } catch (SQLException ex) {
            Logger.getLogger(CadastrarFuncionario.class.getName()).log(Level.SEVERE, null, ex);
          }
        }
      }
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
        
        String nome = request.getParameter("Nome");
        String dtNascimento = request.getParameter("Data");
        String sexo = request.getParameter("Sexo");
        String cpf = request.getParameter("CPG");
        String rg = request.getParameter("RG");
        String telefone = request.getParameter("Telefone");
        String celular = request.getParameter("Celular");
        String cargo = request.getParameter("Cargo");
        String email = request.getParameter("Email");
        String senha = request.getParameter("Senha");
        
       
        Funcionario f = new Funcionario(nome, dtNascimento, sexo, cpf, rg, telefone, celular, cargo, email, senha);
         
        // 1) OBTEM AS INFORMACOES DO USUARIO DA SESSAO
        // A) CAST DOS PARÂMETROS RECEBIDOS
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        // B) TENTA RECUPERAR A SESSÃO DO USUÁRIO
        HttpSession sessao = httpRequest.getSession();
        Usuario usuario = (Usuario) sessao.getAttribute("usuario");
        
        cadastrarFuncionario(f, usuario);
        
        
        response.sendRedirect("../resultado.jsp");
        
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
        
        RequestDispatcher rd = request.getRequestDispatcher("../WEB-INF/cadastrarFuncionario.jsp");
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
 
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        String nome = request.getParameter("Nome");
        String dtNascimento = request.getParameter("Data");
        String sexo = request.getParameter("Sexo");
        String cpf = request.getParameter("CPG");
        String rg = request.getParameter("RG");
        String telefone = request.getParameter("Telefone");
        String celular = request.getParameter("Celular");
        String cargo = request.getParameter("Cargo");
        String email = request.getParameter("Email");
        String senha = request.getParameter("Senha");
        
       
        Funcionario f = new Funcionario(nome, dtNascimento, sexo, cpf, rg, telefone, celular, cargo, email, senha);
         
        // 1) OBTEM AS INFORMACOES DO USUARIO DA SESSAO
        // A) CAST DOS PARÂMETROS RECEBIDOS
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        // B) TENTA RECUPERAR A SESSÃO DO USUÁRIO
        HttpSession sessao = httpRequest.getSession();
        Usuario usuario = (Usuario) sessao.getAttribute("usuario");
        
        cadastrarFuncionario(f, usuario);
        
        
        response.sendRedirect("../resultado.jsp");
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