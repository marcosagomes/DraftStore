/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wrm.draftstore.relatorios;

import com.wrm.draftstore.database.ConexaoBDJavaDB;
import com.wrm.draftstore.servlets.busca.BuscarFornecedor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
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
@WebServlet(name = "Log", urlPatterns = {"/Servlet/Log"})
public class Log extends HttpServlet {

    public void buscarDados(String mesAtual) {
        String auxAtual;

        auxAtual = "20" + mesAtual.substring(6, 8) + "-" + mesAtual.substring(3, 5);
        ConexaoBDJavaDB conexaoBD = new ConexaoBDJavaDB("draftstoredb");
        Statement stmt = null;
        Connection conn = null;

        String sql
                = "select 'DESCRIÇÃO DO LOG: '||log.DESCRICAO_LOG||\n"
                + "       '   -  DATA DO LOG: '||log.DATA_CRIACAO||\n"
                + "       '   -  USUÁRIO ENVOLVIDO: '||log.NOME_USUARIO as DESCRICAO\n"
                + "        from ADM.TB_LOG log\n"
                + "       where SUBSTR((CAST((CAST(log.data_criacao AS DATE)) AS VARCHAR(10))),1,7) = '" + auxAtual + "'";
        try {
            conn = conexaoBD.obterConexao();
            stmt = conn.createStatement();
            ResultSet resultados = stmt.executeQuery(sql);
            try (PrintWriter writer = new PrintWriter("C:\\Users\\Edson\\Documents\\GitHub\\DraftStore\\DraftStore2\\web\\Log\\logCorrente.txt", "UTF-8")) {

                writer.println(" ------ ARQUIVO DE LOG REFERENTE AO ANO-MÊS " + auxAtual + " ------ ");

                while (resultados.next()) {
                    writer.println(resultados.getString("DESCRICAO"));
                }

                writer.println("------ FIM DO ARQUIVO ------");
                writer.flush();
                writer.close();
            }

        } catch (FileNotFoundException | UnsupportedEncodingException f) {
            Logger.getLogger(BuscarFornecedor.class.getName()).log(Level.SEVERE, null, f);
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
        buscarDados(new SimpleDateFormat().format(new Date(atual.getTimeInMillis())));

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
