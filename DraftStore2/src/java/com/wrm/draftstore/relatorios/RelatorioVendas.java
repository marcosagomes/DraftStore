/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wrm.draftstore.relatorios;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import com.wrm.draftstore.classes.Fornecedor;
import com.wrm.draftstore.database.ConexaoBDJavaDB;
import com.wrm.draftstore.servlets.busca.BuscarFornecedor;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author wilson.aoliveira
 */
@WebServlet(name = "RelatorioVendas", urlPatterns = {"/Servlet/RelatorioVendas"})
public class RelatorioVendas extends HttpServlet {

    private float vendaMesAtual;
    private float vendaMesAnterior;

    public void buscarVendas(int mesAtual, int mesAnterior) {
        ConexaoBDJavaDB conexaoBD = new ConexaoBDJavaDB("draftstoredb");
        Statement stmt = null;
        Connection conn = null;

        String sql
                = "select sum(produto.PRECO) as VALOR, (CAST((SUBSTR((CAST(venda.DATA_CRIACAO as VARCHAR(25))),6,2)) as INTEGER)) as MES\n"
                + "  from ADM.TB_VENDA venda, \n"
                + "       ADM.TB_ITEM_VENDA produto\n"
                + " where venda.ID_VENDA = produto.FK_VENDA \n"
                + "   and ((CAST((SUBSTR((CAST(venda.DATA_CRIACAO as VARCHAR(25))),6,2)) as INTEGER)) = " + mesAtual
                + "    or (CAST((SUBSTR((CAST(venda.DATA_CRIACAO as VARCHAR(25))),6,2)) as INTEGER)) = " + mesAnterior + ") group\n"
                + "    by (CAST((SUBSTR((CAST(DATA_CRIACAO as VARCHAR(25))),6,2)) as INTEGER))";
        try {
            conn = conexaoBD.obterConexao();
            stmt = conn.createStatement();
            ResultSet resultados = stmt.executeQuery(sql);

            while (resultados.next()) {
                if (mesAtual == resultados.getInt("MES")) {
                    vendaMesAtual = resultados.getFloat("VALOR");
                } else {
                    vendaMesAnterior = resultados.getFloat("VALOR");
                }

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
        Calendar atual = Calendar.getInstance();
        Calendar anterior = Calendar.getInstance();
        anterior.add(Calendar.MONTH, -1);

        buscarVendas(atual.get(Calendar.MONTH), anterior.get(Calendar.MONTH));
        System.out.println("Venda mes atual " + vendaMesAtual);
        System.out.println("Venda mes anterior " + vendaMesAnterior);

        request.setAttribute("vendaMesAtual", vendaMesAtual);
        request.setAttribute("vendaMesAnterior", vendaMesAnterior);

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

}
