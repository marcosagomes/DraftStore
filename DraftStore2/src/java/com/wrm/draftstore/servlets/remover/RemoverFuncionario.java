/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wrm.draftstore.servlets.remover;

import com.wrm.draftstore.classes.Fornecedor;
import com.wrm.draftstore.classes.Funcionario;
import com.wrm.draftstore.classes.Usuario;
import com.wrm.draftstore.database.ConexaoBDJavaDB;
import com.wrm.draftstore.servlets.editar.EditarFuncionario;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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
@WebServlet(name = "RemoverFuncionario", urlPatterns = {"/Servlet/RemoverFuncionario"})
public class RemoverFuncionario extends HttpServlet {
    static String idFuncionario;
    public void removerFuncionario(Funcionario f, Usuario u) {
        ConexaoBDJavaDB conexaoBD = new ConexaoBDJavaDB("draftCliente");
        PreparedStatement stmt = null;
        Connection conn = null;
        
        String updateSql = "UPDATE TB_FUNCIONARIO"
                + " SET ATIVO = FALSE"
                + " WHERE ID_FUNCIONARIO = "+idFuncionario+"";
        
        try {
            conn = conexaoBD.obterConexao();
            stmt = conn.prepareStatement(updateSql);
            stmt.execute();

            System.out.println("> Funcionario removido com sucesso.");

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(RemoverFuncionario.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERRO! -> " + ex.getMessage());
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RemoverFuncionario.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RemoverFuncionario.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public Funcionario buscarFuncionario(Object idFuncionario, Usuario u) {
    ConexaoBDJavaDB conexaoBD = new ConexaoBDJavaDB("draftCliente");
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
        
        Funcionario f = buscarFuncionario(idFuncionario, usuario);
//        request.setAttribute("Fornecedor", f);
        
        removerFuncionario(f, usuario);
        
        response.sendRedirect("BuscarFuncionario");

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