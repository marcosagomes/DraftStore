/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wrm.draftstore.auxiliares;

import com.google.gson.Gson;
import com.wrm.draftstore.classes.Fornecedor;
import com.wrm.draftstore.classes.Produto;
import com.wrm.draftstore.database.ConexaoBDJavaDB;
import com.wrm.draftstore.servlets.busca.BuscarFornecedor;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.JsonException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ramonhonorio
 */
@WebServlet(name = "JsonProdutosServlet", urlPatterns = {"/Servlet/JsonProdutosServlet"})
public class JsonProdutosServlet extends HttpServlet {

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
        
        List<Produto> produtosLista = listarProdutos();
        request.setAttribute("lista", produtosLista);
        
        String json = new Gson().toJson(produtosLista);
        PrintWriter out = response.getWriter();
        
        try {
            response.setContentType("application/json");
//      out.write(json);
            out.print(json);
//      System.out.println(json);
        } catch (JsonException e) {
            System.out.println("ERRO! -> [Json]: " + e);
        }
        
        FileWriter file = new FileWriter("fornec.json");
        try {
            file.write(json);
//            System.out.println("Successfully Copied JSON Object to File...");
//            System.out.println("\nJSON Object: " + json);
            
        } catch (IOException e) {
            e.printStackTrace();
            
        } finally {
            file.flush();
            file.close();
        }
        
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

    public List<Produto> listarProdutos() {
        ConexaoBDJavaDB conexaoBD = new ConexaoBDJavaDB("draftCliente");
        Statement stmt = null;
        Connection conn = null;
        
        String sql = "SELECT TIPO_PRODUTO,"
                + "          MARCA,"
                + "          ID_PRODUTO,"
                + "          PRECO_VENDA,"
                + "          MODELO"
                + "     FROM TB_PRODUTO";
        try {
            conn = conexaoBD.obterConexao();
            stmt = conn.createStatement();
            ResultSet resultados = stmt.executeQuery(sql);
            
            List<Produto> lista = new ArrayList<>();
            
            while (resultados.next()) {
                Produto p = new Produto();
                p.setTipoProduto(resultados.getString("TIPO_PRODUTO"));
                p.setMarca(resultados.getString("MARCA"));
                p.setIdProduto(resultados.getInt("ID_PRODUTO"));
                p.setPrecoVenda(resultados.getFloat("PRECO_VENDA"));
                p.setModelo(resultados.getString("MODELO"));
                lista.add(p);
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
    
}
