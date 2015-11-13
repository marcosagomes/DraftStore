/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wrm.draftstore.servlets.cadastro;

import com.wrm.draftstore.classes.Categoria;
import com.wrm.draftstore.servlets.editar.EditarFornecedor;
import com.wrm.draftstore.classes.Fornecedor;
import com.wrm.draftstore.classes.Produto;
import com.wrm.draftstore.classes.SubCategoria;
import com.wrm.draftstore.classes.Usuario;
import com.wrm.draftstore.database.ConexaoBDJavaDB;
import com.wrm.draftstore.servlets.busca.BuscaSubCategoria;
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
        ConexaoBDJavaDB conexaoBD = new ConexaoBDJavaDB("draftCliente");
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

    public void cadastrarProduto(String razaoSocial, Produto p, Usuario u) {
        ConexaoBDJavaDB conexaoBD = new ConexaoBDJavaDB("draftCliente");
        PreparedStatement stmt = null;
        Connection conn = null;

        String sql = "INSERT INTO TB_PRODUTO"
                + "(PRECO_VENDA,"
                + " PRECO_PROMO,"
                + " PERCENTUAL_LUCRO,"
                + " MODELO,"
                + " MARCA,"
                + " CUSTO,"
                + " FK_CATEGORIA,"
                + " FK_SUBCATEGORIA,"
                + " QUANTIDADE,"
                + " CAMINHO_IMAGEM,"
                + " DESCRICAO,"
                + " FK_FORNECEDOR,"
                + " FK_FUNCIONARIO,"
                + " DATA_CRIACAO,"
                + " NOME_FORNECEDOR,"
                + " NOME_USUARIO,"
                + " DESCRICAO_IMAGEM,"
                + " DATA_EVENTO) \n"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            conn = conexaoBD.obterConexao();
            stmt = conn.prepareStatement(sql);

            stmt.setFloat(1, p.getPrecoVenda());
            stmt.setFloat(2, p.getPrecoPromo());
            stmt.setFloat(3, p.getPercentualLucro());
            stmt.setString(4, p.getModelo());
            stmt.setString(5, p.getMarca());
            stmt.setFloat(6, p.getCusto());
            stmt.setInt(7, p.getIdCategoria());
            stmt.setInt(8, p.getIdSubCategoria());
            stmt.setInt(9, p.getQuantidade());
            stmt.setString(10, p.getCaminhoImagem());
            stmt.setString(11, p.getDescricao());
            stmt.setInt(12, p.getIdFornecedor());
            stmt.setString(13, u.getIdUsuario());
            // Criando um Timestamp atual do sistema
            stmt.setTimestamp(14, new Timestamp(new Date().getTime()));

            stmt.setString(15, p.getNomeFornecedor());
            stmt.setString(16, u.getNomeDoFuncionario());
            stmt.setString(17, p.getDescImagem().replace("\t", "<br>"));
            stmt.setDate(18, p.getDataEvento());

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

        int idCategoria = Integer.parseInt(request.getParameter("Tipo"));
        int idSubCategoria = Integer.parseInt(request.getParameter("subTipo"));
        int fkFornecedor = Integer.parseInt(request.getParameter("Fornecedor"));
        String nomeFornecedor = request.getParameter(String.valueOf(fkFornecedor));
        String marca = request.getParameter("Marca");
        String modelo = request.getParameter("Modelo");
        String stringCusto = request.getParameter("Custo");
        String stringPercLucro = request.getParameter("lucro");
        String stringPrecoVenda = request.getParameter("preco");
        String stringPrecoPromo = request.getParameter("precoPromo");
        float precoVenda = 0;
        float percentualLucro = 0;
        float custo = 0;
        float precoPromo = 0;
        java.sql.Date dataPromo = null;
        try {
            precoVenda = (Long) NumberFormat.getIntegerInstance().parse(stringPrecoVenda.substring(3, stringPrecoVenda.length() - 3));
            precoPromo = (Long) NumberFormat.getIntegerInstance().parse(stringPrecoPromo);
            percentualLucro = (Long) NumberFormat.getNumberInstance().parse(stringPercLucro);
            custo = (Long) NumberFormat.getNumberInstance().parse(stringCusto);
            dataPromo = java.sql.Date.valueOf(request.getParameter("dataPromo"));
        } catch (ParseException ex) {
            Logger.getLogger(CadastrarProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
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

        Produto p = new Produto(0, precoVenda, precoPromo, percentualLucro, modelo, marca, custo, fkFornecedor, idCategoria, idSubCategoria, dataCriacao, nomeFornecedor, nomeUsuario, fkFuncionario, quantidade, descricao, caminhoImagem, descImagem, dataPromo);
        cadastrarProduto(nomeFornecedor, p, usuario);
        response.sendRedirect("CadastrarProduto");

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
        request.setAttribute("CatProduto", listarCategorias());
        request.setAttribute("SubCatProduto", listarSubCategorias());
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

    private List<Categoria> listarCategorias() {
        ConexaoBDJavaDB conexaoBD = new ConexaoBDJavaDB("draftCliente");
        Statement stmt = null;
        Connection conn = null;

        String sql = "SELECT ID_CATEGORIA, NOME_CATEGORIA FROM TB_CATEGORIA";
        try {
            conn = conexaoBD.obterConexao();
            stmt = conn.createStatement();
            ResultSet resultados = stmt.executeQuery(sql);

            List<Categoria> lista = new ArrayList<>();

            while (resultados.next()) {
                Categoria c = new Categoria();
                c.setNome(resultados.getString("NOME_CATEGORIA"));
                c.setValue(resultados.getInt("ID_CATEGORIA"));

                lista.add(c);
            }

            return lista;

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(BuscaSubCategoria.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BuscaSubCategoria.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BuscaSubCategoria.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }

    private List<SubCategoria> listarSubCategorias() {
        ConexaoBDJavaDB conexaoBD = new ConexaoBDJavaDB("draftCliente");
        Statement stmt = null;
        Connection conn = null;

        String sql = "SELECT ID_SUBCATEGORIA, FK_CATEGORIA, NOME_SUBCATEGORIA FROM TB_SUBCATEGORIA";
        try {
            conn = conexaoBD.obterConexao();
            stmt = conn.createStatement();
            ResultSet resultados = stmt.executeQuery(sql);

            List<SubCategoria> lista = new ArrayList<>();

            while (resultados.next()) {
                SubCategoria sc = new SubCategoria();
                sc.setNome(resultados.getString("NOME_SUBCATEGORIA"));
                sc.setValue(resultados.getInt("ID_SUBCATEGORIA"));
                sc.setFkValue(resultados.getInt("FK_CATEGORIA"));

                lista.add(sc);
            }

            return lista;

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(BuscaSubCategoria.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BuscaSubCategoria.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BuscaSubCategoria.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }
}
