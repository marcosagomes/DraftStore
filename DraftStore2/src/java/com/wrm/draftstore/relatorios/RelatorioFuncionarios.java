/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wrm.draftstore.relatorios;

import com.wrm.draftstore.classes.Funcionario;
import com.wrm.draftstore.classes.Usuario;
import com.wrm.draftstore.database.ConexaoBDJavaDB;
import com.wrm.draftstore.servlets.busca.BuscarFornecedor;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author wilson.aoliveira
 */
@WebServlet(name = "RelatorioFuncionarios", urlPatterns = {"/Servlet/RelatorioFuncionarios"})
public class RelatorioFuncionarios extends HttpServlet {

    public void buscarVendas(List<Funcionario> funcionarios) {

        ConexaoBDJavaDB conexaoBD = new ConexaoBDJavaDB("draftCliente");
        Statement stmt = null;
        Connection conn = null;

        String sql
                = "select  CAST (sum(((prod.CUSTO * prod.PERCENTUAL_LUCRO)/100) * produto.QUANTIDADE) AS DECIMAL(30,2)) as VALOR,\n"
                + "        FK_FUNCIONARIO,\n"
                + "        NOME_USUARIO as FUNCIONARIO\n"
                + "        from TB_CARRINHO_VENDA venda, \n"
                + "            TB_CARRINHO produto,\n"
                + "            TB_PRODUTO prod\n"
                + "        where venda.ID_VENDA = produto.FK_CARRINHO_VENDA\n"
                + "          and prod.ID_PRODUTO = produto.FK_PRODUTO group\n"
                + "        by FK_FUNCIONARIO, NOME_USUARIO order by VALOR desc";
        try {
            conn = conexaoBD.obterConexao();
            stmt = conn.createStatement();
            ResultSet resultados = stmt.executeQuery(sql);

            while (resultados.next()) {
                funcionarios.add(new Funcionario(resultados.getString("FUNCIONARIO"), resultados.getFloat("VALOR")));
            }

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
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        // B) TENTA RECUPERAR A SESSÃO DO USUÁRIO
        HttpSession sessao = httpRequest.getSession();
        Usuario usuario = (Usuario) sessao.getAttribute("usuario");
        List<Funcionario> funcionarios = new ArrayList();
        buscarVendas(funcionarios);

        request.setAttribute("Funcionarios", funcionarios);

        for (Funcionario f : funcionarios) {
            System.out.println("Funcionario: " + f.getNome() + "\nValor: " + f.getValorVendas());
        }
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/relatoriosDesempenho.jsp");
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
