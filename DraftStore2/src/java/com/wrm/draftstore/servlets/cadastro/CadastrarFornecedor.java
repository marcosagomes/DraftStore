/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wrm.draftstore.servlets.cadastro;

import com.wrm.draftstore.classes.Fornecedor;
import com.wrm.draftstore.classes.Usuario;
import com.wrm.draftstore.database.ConexaoBDJavaDB;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
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
@WebServlet(name = "CadastrarFornecedor", urlPatterns = {"/Servlet/CadastrarFornecedor"})
public class CadastrarFornecedor extends HttpServlet {

    public void cadastrarFornecedor(Fornecedor f, Usuario u){
        ConexaoBDJavaDB conexaoBD = new ConexaoBDJavaDB("draftCliente");
        PreparedStatement stmt = null;
        Connection conn = null;
        
        String sql = "INSERT INTO TB_FORNECEDOR " // Notar que antes de fechar aspas tem espaço em branco!
                + "(RAZAO_SOCIAL,"
                + " CNPJ, "
                + "CEP, "
                + "ENDERECO, "
                + "BAIRRO, "
                + "NUMERO, "
                + "CIDADE, "
                + "ESTADO, "
                + "TELEFONE, "
                + "EMAIL, "
                + "SITE, "
                + "FK_FUNCIONARIO, "
                + "DATA_CRIACAO, "
                + "NOME_USUARIO) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
        conn = conexaoBD.obterConexao();
        stmt = conn.prepareStatement(sql);
        
        stmt.setString(1, f.getRazaoSocial());
        stmt.setString(2, f.getCnpj());
        stmt.setString(3, f.getCep());
        stmt.setString(4, f.getEndereco());
        stmt.setString(5, f.getBairro());
        int numero = Integer.parseInt(f.getNumero());
        stmt.setInt(6, numero);
        stmt.setString(7, f.getCidade());
        stmt.setString(8, f.getEstado());
        stmt.setString(9, f.getTelefone());
        stmt.setString(10, f.getEmail());
        stmt.setString(11, f.getSite());
        int idUser = Integer.parseInt(u.getIdUsuario());
        stmt.setInt(12, idUser);
        
        // Criando um Timestamp atual do sistema
        Date dataAtual = new Date();
        String timeStamp = new Timestamp(dataAtual.getTime()).toString();
        
        stmt.setString(13, timeStamp);
        stmt.setString(14, u.getNomeDoFuncionario());
        
        stmt.executeUpdate();
        
        System.out.println("> FORNECEDOR incluido com sucesso.");

      } catch (SQLException | ClassNotFoundException ex) {
        Logger.getLogger(CadastrarFornecedor.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERRO! -> " + ex.getMessage());
      } finally {
        if (stmt != null) {
          try {
            stmt.close();
          } catch (SQLException ex) {
            Logger.getLogger(CadastrarFornecedor.class.getName()).log(Level.SEVERE, null, ex);
          }
        }
        if (conn != null) {
          try {
            conn.close();
          } catch (SQLException ex) {
            Logger.getLogger(CadastrarFornecedor.class.getName()).log(Level.SEVERE, null, ex);
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
        
        String razaoSocial = request.getParameter("RazaoSocial");
        String cnpj = request.getParameter("Cnpj");
        String cep = request.getParameter("Cep");
        String endereco = request.getParameter("Endereco");
        String bairro = request.getParameter("Bairro");
        String cidade = request.getParameter("Cidade");
        String estado = request.getParameter("UF");
        String telefone = request.getParameter("Telefone");
        String email = request.getParameter("Email");
        String site = request.getParameter("Site");
        String numero = request.getParameter("Numero");
        
        Fornecedor f = new Fornecedor(razaoSocial, cnpj, cep, endereco, bairro, 
                cidade, estado, telefone, email, site, numero);
        
        // 1) OBTEM AS INFORMACOES DO USUARIO DA SESSAO
        // A) CAST DOS PARÂMETROS RECEBIDOS
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        // B) TENTA RECUPERAR A SESSÃO DO USUÁRIO
        HttpSession sessao = httpRequest.getSession();
        Usuario usuario = (Usuario) sessao.getAttribute("usuario");
        
        cadastrarFornecedor(f, usuario);
        
        response.sendRedirect("CadastrarFornecedor");
        
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
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/cadastrarFornecedor.jsp");
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
        
        String razaoSocial = request.getParameter("RazaoSocial");
        String cnpj = request.getParameter("Cnpj");
        String cep = request.getParameter("Cep");
        String endereco = request.getParameter("Endereco");
        String bairro = request.getParameter("Bairro");
        String cidade = request.getParameter("Cidade");
        String estado = request.getParameter("UF");
        String telefone = request.getParameter("Telefone");
        String email = request.getParameter("Email");
        String site = request.getParameter("Site");
        String numero = request.getParameter("Numero");
        
        Fornecedor f = new Fornecedor(razaoSocial, cnpj, cep, endereco, bairro, 
                cidade, estado, telefone, email, site, numero);
        
        // 1) OBTEM AS INFORMACOES DO USUARIO DA SESSAO
        // A) CAST DOS PARÂMETROS RECEBIDOS
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        // B) TENTA RECUPERAR A SESSÃO DO USUÁRIO
        HttpSession sessao = httpRequest.getSession();
        Usuario usuario = (Usuario) sessao.getAttribute("usuario");
        
      cadastrarFornecedor(f, usuario);
        
        System.out.println("> Fornecedor cadastrado.");
        response.sendRedirect("CadastrarFornecedor");
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