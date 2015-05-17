/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wrm.draftstore.servlets.editar;

import com.wrm.draftstore.classes.Fornecedor;
import com.wrm.draftstore.classes.Usuario;
import com.wrm.draftstore.database.ConexaoBDJavaDB;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
@WebServlet(name = "EditarFornecedor", urlPatterns = {"/Servlet/EditarFornecedor"})
public class EditarFornecedor extends HttpServlet {
    static String idFornec;
    public void editarFornecedor(Fornecedor f, Usuario u) {
        ConexaoBDJavaDB conexaoBD = new ConexaoBDJavaDB("draftstoredb");
        PreparedStatement stmt = null;
        Connection conn = null; 
        
        String updateSql = "UPDATE TB_FORNECEDOR\n" +
                          "    SET RAZAO_SOCIAL = '"+f.getRazaoSocial()+"', \n" +
                          "        CNPJ = '"+f.getCnpj()+"', \n" +
                          "        CEP = '"+f.getCep()+"', \n" +
                          "        ENDERECO = '"+f.getEndereco()+"', \n" +
                          "        BAIRRO = '"+f.getBairro()+"',\n" +
                          "        NUMERO = "+Integer.parseInt(f.getNumero())+", \n" +
                          "        CIDADE = '"+f.getCidade()+"', \n" +
                          "        ESTADO = '"+f.getEstado()+"', \n" +
                          "        TELEFONE = '"+f.getTelefone()+"', \n" +
                          "        EMAIL = '"+f.getEmail()+"', \n" +
                          "        SITE = '"+f.getSite()+"',\n" +
                          "        FK_FUNCIONARIO = "+Integer.parseInt(u.getIdUsuario())+",\n" +
                          "        NOME_USUARIO = '"+u.getNomeDoFuncionario()+"',\n" +
                          "        DATA_CRIACAO = '"+new Timestamp(new Date().getTime()).toString()+"'\n" +
                          "    WHERE ID_FORNECEDOR = "+idFornec+"\n" +
                          "";
        
        try {
            conn = conexaoBD.obterConexao();
            stmt = conn.prepareStatement(updateSql);
            stmt.execute();

            System.out.println("Registro incluido com sucesso.");

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

    public Fornecedor buscarFornecedor(Object idFornecedor, Usuario u) {
        ConexaoBDJavaDB conexaoBD = new ConexaoBDJavaDB("draftstoredb");
        Statement stmt = null;
        Connection conn = null;

        String sql = "SELECT ID_FORNECEDOR,\n"
                + "          CEP,\n"
                + "          ENDERECO,\n"
                + "          BAIRRO,\n"
                + "          CIDADE,\n"
                + "          ESTADO,\n"
                + "          TELEFONE,\n"
                + "          EMAIL,\n"
                + "          SITE,\n"
                + "          NUMERO,\n"
                + "          FK_FUNCIONARIO,\n"
                + "          DATA_CRIACAO,\n"
                + "          RAZAO_SOCIAL,\n"
                + "          CNPJ\n"
//                + "          NOME_USUARIO"
                + "     FROM TB_FORNECEDOR\n"
                + "    WHERE TB_FORNECEDOR.ID_FORNECEDOR = " + idFornecedor.toString();
        try {
            conn = conexaoBD.obterConexao();
            stmt = conn.createStatement();
            ResultSet resultados = stmt.executeQuery(sql);

            Fornecedor f = new Fornecedor();
            while (resultados.next()) {
                f.setIdFornecedor(resultados.getString("ID_FORNECEDOR"));
                f.setCep(resultados.getString("CEP"));
                f.setEndereco(resultados.getString("ENDERECO"));
                f.setBairro(resultados.getString("BAIRRO"));
                f.setCidade(resultados.getString("CIDADE"));
                f.setEstado(resultados.getString("ESTADO"));
                f.setTelefone(resultados.getString("TELEFONE"));
                f.setEmail(resultados.getString("EMAIL"));
                f.setSite(resultados.getString("SITE"));
                f.setNumero(resultados.getString("NUMERO"));
                f.setRazaoSocial(resultados.getString("RAZAO_SOCIAL"));
                f.setCnpj(resultados.getString("CNPJ"));
            }
            return f;
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

        // 1) OBTEM AS INFORMACOES DO USUARIO DA SESSAO
        // A) CAST DOS PARÂMETROS RECEBIDOS
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        // B) TENTA RECUPERAR A SESSÃO DO USUÁRIO
        HttpSession sessao = httpRequest.getSession();
        Usuario usuario = (Usuario) sessao.getAttribute("usuario");
        
        idFornec = request.getParameter("idFornecedor");
        
        Fornecedor f = buscarFornecedor(idFornec, usuario);
        request.setAttribute("Fornecedor", f);

        RequestDispatcher rd = request.getRequestDispatcher("../WEB-INF/editarFornecedor.jsp");
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

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String razaoSocial = request.getParameter("RazaoSocial");
        String cnpj = request.getParameter("Cnpj");
        String cep = request.getParameter("Cep");
        String endereco = request.getParameter("Endereco");
        String bairro = request.getParameter("Bairro");
        String cidade = request.getParameter("Cidade");
        String estado = request.getParameter("Uf");
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

        editarFornecedor(f, usuario);

        System.out.println("> Fornecedor alterado por ["+usuario.getNomeDoFuncionario()+"].");
        response.sendRedirect("BuscarFornecedor");
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