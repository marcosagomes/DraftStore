/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wrm.draftstore.relatorios;

import com.wrm.draftstore.classes.Usuario;
import com.wrm.draftstore.database.ConexaoBDJavaDB;
import com.wrm.draftstore.servlets.busca.BuscarFornecedor;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
 * @author wilson.aoliveira
 */
@WebServlet(name = "RelatorioVendas", urlPatterns = {"/Servlet/RelatorioVendas"})
public class RelatorioVendas extends HttpServlet {
    
    private float vendaMesAtual;
    private float vendaMesAnterior;
    
    String auxAtual, auxAnterior;
    
    public static String theMonth(int month) {
        String[] monthNames = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
        return monthNames[month - 1];
    }
    
    public void buscarVendas(String mesAtual, String mesAnterior) {
        
        auxAtual = "20" + mesAtual.substring(6, 8) + "-" + mesAtual.substring(3, 5);
        auxAnterior = "20" + mesAnterior.substring(6, 8) + "-" + mesAnterior.substring(3, 5);
        
        ConexaoBDJavaDB conexaoBD = new ConexaoBDJavaDB("draftstoredb");
        Statement stmt = null;
        Connection conn = null;
        
        String sql
                = "select  sum(produto.PRECO) as VALOR,\n"
                + "        SUBSTR((CAST((CAST(venda.DATA_CRIACAO as DATE)) as VARCHAR(10))),1,7) as MES\n"
                + "        from ADM.TB_VENDA venda, \n"
                + "            ADM.TB_ITEM_VENDA produto\n"
                + "        where venda.ID_VENDA = produto.FK_VENDA\n"
                + "        and (CAST((SUBSTR((CAST((CAST(venda.DATA_CRIACAO as DATE)) as VARCHAR(10))),1,8)||'01') AS DATE)) "
                + "        BETWEEN '" + auxAnterior + "-01'" + " AND '" + auxAtual + "-01'" + " group\n"
                + "        by SUBSTR((CAST((CAST(venda.DATA_CRIACAO as DATE)) as VARCHAR(10))),1,7)\n";
        try {
            conn = conexaoBD.obterConexao();
            stmt = conn.createStatement();
            ResultSet resultados = stmt.executeQuery(sql);
            
            while (resultados.next()) {
                if (auxAtual.equals(resultados.getString("MES"))) {
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
    
    public void buscarVendas(String anoAtual, String mesAtual, String anoAnterior, String mesAnterior) {
        
        vendaMesAtual = 0;
        vendaMesAnterior = 0;
        
        mesAtual = mesAtual.length() == 1 ? "0" + mesAtual : mesAtual;
        auxAtual = "20" + anoAtual.substring(6, 8) + "-" + mesAtual;
        
        mesAnterior = mesAnterior.length() == 1 ? "0" + mesAnterior : mesAnterior;
        auxAnterior = "20" + anoAnterior.substring(6, 8) + "-" + mesAnterior;
        
        ConexaoBDJavaDB conexaoBD = new ConexaoBDJavaDB("draftstoredb");
        Statement stmt = null;
        Connection conn = null;
        
        String sql
                = "select  sum(produto.PRECO) as VALOR,\n"
                + "        SUBSTR((CAST((CAST(venda.DATA_CRIACAO as DATE)) as VARCHAR(10))),1,7) as MES\n"
                + "        from ADM.TB_VENDA venda, \n"
                + "            ADM.TB_ITEM_VENDA produto\n"
                + "        where venda.ID_VENDA = produto.FK_VENDA\n"
                + "        and (CAST((SUBSTR((CAST((CAST(venda.DATA_CRIACAO as DATE)) as VARCHAR(10))),1,8)||'01') AS DATE)) "
                + "        BETWEEN '" + auxAnterior + "-01'" + " AND '" + auxAtual + "-01'" + " group\n"
                + "        by SUBSTR((CAST((CAST(venda.DATA_CRIACAO as DATE)) as VARCHAR(10))),1,7)\n";
        try {
            conn = conexaoBD.obterConexao();
            stmt = conn.createStatement();
            ResultSet resultados = stmt.executeQuery(sql);
            
            while (resultados.next()) {
                if (auxAtual.equals(resultados.getString("MES"))) {
                    vendaMesAtual = resultados.getFloat("VALOR");
                } else if (auxAnterior.equals(resultados.getString("MES"))) {
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
        // 1) OBTEM AS INFORMACOES DO USUARIO DA SESSAO
        // A) CAST DOS PARÂMETROS RECEBIDOS
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        // B) TENTA RECUPERAR A SESSÃO DO USUÁRIO
        HttpSession sessao = httpRequest.getSession();
        Usuario usuario = (Usuario) sessao.getAttribute("usuario");
        Calendar atual = Calendar.getInstance();
        Calendar anterior = Calendar.getInstance();
        anterior.add(Calendar.MONTH, -1);
        
        buscarVendas(new SimpleDateFormat().format(
                new Date(atual.getTimeInMillis())),
                new SimpleDateFormat().format(new Date(anterior.getTimeInMillis()))
        );
        System.out.println("Venda mes atual " + vendaMesAtual);
        System.out.println("Venda mes anterior " + vendaMesAnterior);
        
        request.setAttribute("vendaMesAtual", vendaMesAtual);
        request.setAttribute("vendaMesAnterior", vendaMesAnterior);
        
        request.setAttribute("mesAnterior", theMonth(Integer.parseInt(auxAnterior.substring(5, 7))));
        request.setAttribute("mesAtual", theMonth(Integer.parseInt(auxAtual.substring(5, 7))));
        
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/relatoriosDespesas.jsp");
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
        // 1) OBTEM AS INFORMACOES DO USUARIO DA SESSAO
        // A) CAST DOS PARÂMETROS RECEBIDOS
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        // B) TENTA RECUPERAR A SESSÃO DO USUÁRIO
        HttpSession sessao = httpRequest.getSession();
        Usuario usuario = (Usuario) sessao.getAttribute("usuario");
        
        String primeiro = request.getParameter("primeiro");
        String segundo = request.getParameter("segundo");
        
        Calendar atual = Calendar.getInstance();
        Calendar anterior = Calendar.getInstance();
        
        buscarVendas(new SimpleDateFormat().format(new Date(atual.getTimeInMillis())),
                segundo,
                new SimpleDateFormat().format(new Date(anterior.getTimeInMillis())),
                primeiro
        );
        System.out.println("Venda mes atual " + vendaMesAtual);
        System.out.println("Venda mes anterior " + vendaMesAnterior);
        
        request.setAttribute("vendaMesAtual", vendaMesAtual);
        request.setAttribute("vendaMesAnterior", vendaMesAnterior);
        
        request.setAttribute("mesAnterior", theMonth(Integer.parseInt(primeiro)));
        request.setAttribute("mesAtual", theMonth(Integer.parseInt(segundo)));
        
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/relatoriosDespesas.jsp");
        rd.forward(request, response);
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
