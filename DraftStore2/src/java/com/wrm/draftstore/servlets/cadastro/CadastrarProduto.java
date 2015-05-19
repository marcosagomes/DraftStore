/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wrm.draftstore.servlets.cadastro;

import com.wrm.draftstore.servlets.editar.EditarFornecedor;
import com.wrm.draftstore.classes.Fornecedor;
import com.wrm.draftstore.classes.Produto;
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
import java.text.NumberFormat;
import java.text.ParseException;
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
@WebServlet(name = "CadastrarProduto", urlPatterns = {"/Servlet/CadastrarProduto"})
public class CadastrarProduto extends HttpServlet {
  
  public List<Fornecedor> listarFornecedores() {
    ConexaoBDJavaDB conexaoBD = new ConexaoBDJavaDB("draftstoredb");
    Statement stmt = null;
    Connection conn = null;

    String sql = "SELECT ID_FORNECEDOR, RAZAO_SOCIAL FROM TB_FORNECEDOR";
    try {
      conn = conexaoBD.obterConexao();
      stmt = conn.createStatement();
      ResultSet resultados = stmt.executeQuery(sql);
      
      List<Fornecedor> lista = new ArrayList<>();

      while (resultados.next()) {
          Fornecedor f = new Fornecedor();
          f.setIdFornecedor(resultados.getString("ID_FORNECEDOR"));
          f.setRazaoSocial(resultados.getString("RAZAO_SOCIAL"));
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
  
  public void cadastrarProduto(Produto p, Usuario u){
        ConexaoBDJavaDB conexaoBD = new ConexaoBDJavaDB("draftstoredb");
        PreparedStatement stmt = null;
        Connection conn = null;
        
        String sql = "INSERT INTO ADM.TB_PRODUTO (PRECO_VENDA, PERCENTUAL_LUCRO, "
                + "MODELO, MARCA, TIPO_PRODUTO, CUSTO, FK_FORNECEDOR, FK_USUARIO, DATA_CRIACAO) \n"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
        conn = conexaoBD.obterConexao();
        stmt = conn.prepareStatement(sql);
        
        stmt.setFloat(1, p.getPrecoVenda());
        stmt.setFloat(2, p.getPercentualLucro());
        stmt.setString(3, p.getModelo());
        stmt.setString(4, p.getMarca());
        stmt.setString(5, p.getTipoProduto());
        stmt.setFloat(6, p.getCusto());
        stmt.setInt(7, p.getIdFornecedor());
        stmt.setString(8, u.getIdUsuario());
        
        // Criando um Timestamp atual do sistema
        Date dataAtual = new Date();
        String timeStamp = new Timestamp(dataAtual.getTime()).toString();
        
        stmt.setString(9, timeStamp);
        
        stmt.executeUpdate();
        
        System.out.println("> Produto cadastrado com sucesso.");

      } catch (SQLException | ClassNotFoundException ex) {
        Logger.getLogger(EditarFornecedor.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERRO! -> " + ex.getMessage());
      } finally {
        if (stmt != null) {
          try {
            stmt.close();
          } catch (SQLException ex) {
            Logger.getLogger(EditarFornecedor.class.getName()).log(Level.SEVERE, null, ex);
          }
        }
        if (conn != null) {
          try {
            conn.close();
          } catch (SQLException ex) {
            Logger.getLogger(EditarFornecedor.class.getName()).log(Level.SEVERE, null, ex);
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
        
        String stringPrecoVenda = request.getParameter("precoVenda");
        String stringPercLucro = request.getParameter("lucro");
        String stringCusto = request.getParameter("custo");
        float precoVenda = 0;
        float percentualLucro = 0;
        float custo = 0;
        try {
          precoVenda = (Long) NumberFormat.getIntegerInstance().parse(stringPrecoVenda);
          percentualLucro = (Long) NumberFormat.getNumberInstance().parse(stringPercLucro);
          custo = (Long) NumberFormat.getNumberInstance().parse(stringCusto);
        } catch (ParseException ex) {
          Logger.getLogger(CadastrarProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
//        float precoVenda = Float.parseFloat(request.getParameter("precoVenda"));
//        float percentualLucro = Float.parseFloat(request.getParameter("lucro"));
        String modelo = request.getParameter("modelo");
        String marca = request.getParameter("marca");
        String tipoProduto = request.getParameter("tipoProduto");
//        float custo = Float.parseFloat(request.getParameter("custo"));
        int fkFornecedor = Integer.parseInt(request.getParameter("fornecedor"));
        
        Produto p = new Produto(0, precoVenda, percentualLucro, modelo, 
                marca, tipoProduto, custo, fkFornecedor);
        
        // 1) OBTEM AS INFORMACOES DO USUARIO DA SESSAO
        // A) CAST DOS PARÂMETROS RECEBIDOS
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        // B) TENTA RECUPERAR A SESSÃO DO USUÁRIO
        HttpSession sessao = httpRequest.getSession();
        Usuario usuario = (Usuario) sessao.getAttribute("usuario");
        
        cadastrarProduto(p, usuario);
        
        System.out.println("> Fornecedor cadastrado.");
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
        //processRequest(request, response);
        request.setAttribute("lista", listarFornecedores());
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/cadastrarProduto.jsp");
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

}
