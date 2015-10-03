/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wrm.draftstore.servlets.remover;

import com.wrm.draftstore.classes.Produto;
import com.wrm.draftstore.classes.Usuario;
import com.wrm.draftstore.database.ConexaoBDMySQL;
import com.wrm.draftstore.servlets.editar.EditarFornecedor;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ramon.ahonorio
 */
@WebServlet(name = "RemoverProduto", urlPatterns = {"/Servlet/RemoverProduto"})
public class RemoverProduto extends HttpServlet {

    static Object idProduto;

    public void removerProduto() {
        ConexaoBDMySQL conexaoBD = new ConexaoBDMySQL("DraftOfficeDB");
        PreparedStatement stmt = null;
        Connection conn = null;

        String updateSql = "DELETE FROM TB_PRODUTO WHERE ID_PRODUTO = " + idProduto + "";

        try {
            conn = conexaoBD.obterConexao();
            stmt = conn.prepareStatement(updateSql);
            stmt.execute();

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(RemoverProduto.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERRO! -> " + ex.getMessage());
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RemoverProduto.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RemoverProduto.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public Produto buscarProduto(Object idProduto, Usuario u) {
        ConexaoBDMySQL conexaoBD = new ConexaoBDMySQL("DraftOfficeDB");
        Statement stmt = null;
        Connection conn = null;

        String sql = "SELECT ID_PRODUTO ,\n"
                + "          PRECO_VENDA ,\n"
                + "          PERCENTUAL_LUCRO ,\n"
                + "          MODELO ,\n"
                + "          MARCA ,\n"
                + "          TIPO_PRODUTO ,\n"
                + "          CUSTO ,\n"
                + "          FK_FORNECEDOR ,\n"
                + "          FK_FUNCIONARIO ,\n"
                + "          DATA_CRIACAO ,\n"
                + "          NOME_FORNECEDOR ,\n"
                + "          NOME_USUARIO\n"
                + "     FROM TB_PRODUTO\n"
                + "    WHERE TB_PRODUTO.ID_PRODUTO = " + idProduto.toString();
        try {
            conn = conexaoBD.obterConexao();
            stmt = conn.createStatement();
            ResultSet resultados = stmt.executeQuery(sql);

            Produto p = new Produto();
            while (resultados.next()) {
                p.setIdProduto(Integer.parseInt(resultados.getString("ID_PRODUTO")));
                p.setPrecoVenda(Float.parseFloat(resultados.getString("PRECO_VENDA")));
                p.setPercentualLucro(Float.parseFloat(resultados.getString("PERCENTUAL_LUCRO")));
                p.setModelo(resultados.getString("MODELO"));
                p.setMarca(resultados.getString("MARCA"));
                p.setTipoProduto(resultados.getString("TIPO_PRODUTO"));
                p.setCusto(Float.parseFloat(resultados.getString("CUSTO")));
                p.setIdFornecedor(Integer.parseInt(resultados.getString("FK_FORNECEDOR")));
                p.setIdFuncionario(Integer.parseInt(resultados.getString("FK_FUNCIONARIO")));
                p.setDataCriacao(resultados.getString("DATA_CRIACAO"));
                p.setNomeUsuario(resultados.getString("NOME_FORNECEDOR"));
                p.setNomeFornecedor(resultados.getString("NOME_USUARIO"));
            }
            return p;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(EditarFornecedor.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERRO SQL! -> " + ex.getMessage());
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

        idProduto = request.getParameter("idProduto");
//        request.setAttribute("Fornecedor", f);

        removerProduto();

        response.sendRedirect("BuscarProduto");

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
