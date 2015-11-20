/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wrm.draftstore.servlets.editar;

import com.wrm.draftstore.classes.Produto;
import com.wrm.draftstore.classes.Usuario;
import com.wrm.draftstore.database.ConexaoBDJavaDB;
import com.wrm.draftstore.servlets.cadastro.CadastrarProduto;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
 * @author Edson
 */
@WebServlet(name = "EditarProduto", urlPatterns = {"/Servlet/EditarProduto"})
public class EditarProduto extends HttpServlet {

    static Object idProduto;

    public void editarProduto(Produto p, Usuario u) {
        ConexaoBDJavaDB conexaoBD = new ConexaoBDJavaDB("draftCliente");
        PreparedStatement stmt = null;
        Connection conn = null;

        String updateSql = "UPDATE TB_PRODUTO\n"
                + "    SET PRECO_VENDA  = " + p.getPrecoVenda() + ", \n"
                + "        PRECO_PROMO  = " + p.getPrecoPromo() + ", \n"
                + "        PERCENTUAL_LUCRO  = " + p.getPercentualLucro() + ", \n"
                + "        MODELO = '" + p.getModelo() + "', \n"
                + "        MARCA  = '" + p.getMarca() + "', \n"
                + "        CUSTO  = " + p.getCusto() + ", \n"
                + "        QUANTIDADE  = " + p.getQuantidade() + ", \n"
                + "        CAMINHO_IMAGEM  = '" + p.getCaminhoImagem() + "', \n"
                + "        DESCRICAO  = '" + p.getDescricao() + "', \n"
                + "        DESCRICAO_IMAGEM  = '" + p.getDescImagem() + "', \n"
                + "        DATA_EVENTO_INI  = '" + p.getDataEventoIni() + "', \n"
                + "        DATA_EVENTO_FIM = '" + p.getDataEventoFim() + "' \n"
                + "  WHERE ID_PRODUTO = " + idProduto + "\n";
        try {
            conn = conexaoBD.obterConexao();
            stmt = conn.prepareStatement(updateSql);
            stmt.execute();

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

    public Produto buscarProduto(Object idProduto, Usuario u) {
        ConexaoBDJavaDB conexaoBD = new ConexaoBDJavaDB("draftCliente");
        Statement stmt = null;
        Connection conn = null;

        String sql = "SELECT ID_PRODUTO ,\n"
                + "          PRECO_VENDA ,\n"
                + "          PRECO_PROMO ,\n"
                + "          PERCENTUAL_LUCRO ,\n"
                + "          MODELO ,\n"
                + "          MARCA ,\n"
                + "          CUSTO ,\n"
                + "          P.FK_CATEGORIA ,\n"
                + "          NOME_CATEGORIA ,\n"
                + "          P.FK_SUBCATEGORIA ,\n"
                + "          NOME_SUBCATEGORIA ,\n"
                + "          QUANTIDADE ,\n"
                + "          CAMINHO_IMAGEM ,\n"
                + "          DESCRICAO ,\n"
                + "          FK_FORNECEDOR ,\n"
                + "          FK_FUNCIONARIO ,\n"
                + "          DATA_CRIACAO ,\n"
                + "          NOME_FORNECEDOR ,\n"
                + "          NOME_USUARIO, \n"
                + "          DESCRICAO_IMAGEM ,\n"
                + "          DATA_EVENTO_INI ,\n"
                + "          DATA_EVENTO_FIM\n"
                + "     FROM TB_PRODUTO P,\n"
                + "          TB_CATEGORIA C,\n"
                + "          TB_SUBCATEGORIA S\n"
                + "    WHERE C.ID_CATEGORIA = S.FK_CATEGORIA\n"
                + "      AND P.FK_CATEGORIA = C.ID_CATEGORIA\n"
                + "      AND P.FK_SUBCATEGORIA = S.ID_SUBCATEGORIA\n"
                + "      AND P.ID_PRODUTO = " + idProduto.toString();
        try {
            conn = conexaoBD.obterConexao();
            stmt = conn.createStatement();
            ResultSet resultados = stmt.executeQuery(sql);
            Produto p = new Produto();
            while (resultados.next()) {
                p.setIdProduto(resultados.getInt("ID_PRODUTO"));
                p.setIdCategoria(resultados.getInt("FK_CATEGORIA"));
                p.setNomeCategoria(resultados.getString("NOME_CATEGORIA"));
                p.setIdSubCategoria(resultados.getInt("FK_SUBCATEGORIA"));
                p.setNomeSubCategoria(resultados.getString("NOME_SUBCATEGORIA"));
                p.setIdFornecedor(resultados.getInt("FK_FORNECEDOR"));
                p.setNomeFornecedor(resultados.getString("NOME_FORNECEDOR"));
                p.setMarca(resultados.getString("MARCA"));
                p.setModelo(resultados.getString("MODELO"));
                p.setCusto(resultados.getFloat("CUSTO"));
                p.setPercentualLucro(resultados.getFloat("PERCENTUAL_LUCRO"));
                p.setPrecoVenda(resultados.getFloat("PRECO_VENDA"));
                p.setPrecoPromo(resultados.getFloat("PRECO_PROMO"));
                p.setQuantidade(resultados.getInt("QUANTIDADE"));
                p.setCaminhoImagem(resultados.getString("CAMINHO_IMAGEM"));
                p.setDescricao(resultados.getString("DESCRICAO"));
                p.setDescImagem(resultados.getString("DESCRICAO_IMAGEM"));
                p.setIdFuncionario(resultados.getInt("FK_FUNCIONARIO"));
                p.setDataCriacao(resultados.getString("DATA_CRIACAO"));
                p.setNomeUsuario(resultados.getString("NOME_USUARIO"));
                SimpleDateFormat df = new SimpleDateFormat("dd MM yyyy");

                Date dtD;
                dtD = resultados.getDate("DATA_EVENTO_INI");

                try {
                    Date dtIni = df.parse(df.format(dtD));
                    System.out.println(dtIni);
                } catch (ParseException ex) {
                    Logger.getLogger(EditarProduto.class.getName()).log(Level.SEVERE, null, ex);
                }

                p.setDataEventoIni(java.sql.Date.valueOf(resultados.getString("DATA_EVENTO_INI")));
                p.setDataEventoFim(java.sql.Date.valueOf(resultados.getString("DATA_EVENTO_FIM")));
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

        // 1) OBTEM AS INFORMACOES DO USUARIO DA SESSAO
        // A) CAST DOS PARÂMETROS RECEBIDOS
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        // B) TENTA RECUPERAR A SESSÃO DO USUÁRIO
        HttpSession sessao = httpRequest.getSession();
        Usuario usuario = (Usuario) sessao.getAttribute("usuario");

        idProduto = request.getParameter("idProduto");

        Produto p = buscarProduto(idProduto, usuario);
        request.setAttribute("Produto", p);
        request.setAttribute("idProduto", idProduto.toString());

        RequestDispatcher rd = request.getRequestDispatcher("../WEB-INF/editarProduto.jsp");
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

        int idCategoria = Integer.parseInt(request.getParameter("selectCategoria"));
        int idSubCategoria = Integer.parseInt(request.getParameter("subTipo"));
        int fkFornecedor = Integer.parseInt(request.getParameter("Fornecedor"));
        String nomeFornecedor = request.getParameter(String.valueOf(fkFornecedor));
        String marca = request.getParameter("Marca");
        String modelo = request.getParameter("Modelo");
        String stringCusto = request.getParameter("Custo");
        String stringPercLucro = request.getParameter("lucro");
        String stringPrecoVenda = request.getParameter("preco");
        String stringPrecoPromo = request.getParameter("precoPromo");
        String dataIni = request.getParameter("dataEventoIni");
        String dataFim = request.getParameter("dataEventoFim");
        float precoVenda = 0f;
        float percentualLucro;
        float custo;
        float precoPromo;
        java.sql.Date dataEventoFim;
        java.sql.Date dataEventoIni;

        if (stringPrecoPromo.equals(" ") || dataIni.equals("") || dataFim.equals("")) {
            stringPrecoPromo = "0.0";
            dataIni = "2000-01-01";
            dataFim = "2000-01-01";
        }
        dataEventoIni = java.sql.Date.valueOf(dataIni);
        dataEventoFim = java.sql.Date.valueOf(dataFim);
        try {
            precoVenda = (Long) NumberFormat.getIntegerInstance().parse(stringPrecoVenda.substring(3, stringPrecoVenda.length() - 3));
        } catch (ParseException ex) {
            Logger.getLogger(CadastrarProduto.class.getName()).log(Level.SEVERE, null, ex);
        }

        precoPromo = Float.valueOf(stringPrecoPromo);
        percentualLucro = Float.valueOf(stringPercLucro);
        custo = Float.valueOf(stringCusto);
        int quantidade = Integer.parseInt(request.getParameter("quantidade"));
        String caminhoImagem = request.getParameter("imagem");
        String descImagem = request.getParameter("descImagem");
        String descricao = request.getParameter("descricao");
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession sessao = httpRequest.getSession();
        Usuario usuario = (Usuario) sessao.getAttribute("usuario");
        Date d = new Date();
        String dataCriacao = String.valueOf(d.getTime());
        String nomeUsuario = usuario.getNomeDoFuncionario();
        int fkFuncionario = Integer.parseInt(usuario.getIdUsuario());

        Produto p = new Produto(0, precoVenda, precoPromo, percentualLucro, modelo, marca, custo, fkFornecedor, idCategoria, idSubCategoria, dataCriacao, nomeFornecedor, nomeUsuario, fkFuncionario, quantidade, descricao, caminhoImagem, descImagem, dataEventoIni, dataEventoFim);

        editarProduto(p, usuario);

        response.sendRedirect("BuscarProduto");
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
