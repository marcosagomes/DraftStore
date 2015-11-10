/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wrm.draftstore.servlets.autenticacao;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.wrm.draftstore.classes.*;
import com.wrm.draftstore.database.ConexaoBDJavaDB;
import com.wrm.draftstore.servlets.busca.BuscarFornecedor;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fernando.tsuda
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/Login"})
public class LoginServlet extends HttpServlet {

    private static String nome, senha;

    // OBS: Usuarios mantidos em um mapa somente como exemplo.
    // A validação deve ser feita com os dados armazenados no BD.
    private static Map<String, Usuario> USUARIOS_CADASTRADOS;

    public static HashMap<String, Usuario> carregarUsuarios(String nome, String senha) {

        String hashSenha = "";
        ConexaoBDJavaDB conexaoBD = new ConexaoBDJavaDB("draftCliente");
        Statement stmt = null;
        Connection conn = null;

        //Gerar hash da senha digitada para buscar o usuário no banco.
        try {
            char[] c = Usuario.gerarHashSenhaPBKDF2(senha);
            for (char carac : c) { //Iterar no array retornado, para montar a string que está salva na base
                hashSenha += carac;
            }
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            Logger.getLogger(BuscarFornecedor.class.getName()).log(Level.SEVERE, null, ex);
        }

        String sql = "SELECT ID_FUNCIONARIO, NOME, EMAIL, SENHA, NOME_PAPEL\n"
                + "    FROM TB_FUNCIONARIO\n"
                + "    JOIN TB_PAPEL\n"
                + "    ON FK_PAPEL = ID_PAPEL\n"
                + "    WHERE EMAIL = '" + nome + "'\n"
                + "    AND SENHA = '" + hashSenha + "' AND ATIVO = TRUE";
        try {
            conn = conexaoBD.obterConexao();
            stmt = conn.createStatement();
            ResultSet resultados = stmt.executeQuery(sql);

            HashMap<String, Usuario> mapa = new HashMap<>();

            while (resultados.next()) {
                Usuario user = new Usuario();
                user.setIdUsuario(resultados.getString("ID_FUNCIONARIO"));
                user.setLogin(resultados.getString("EMAIL"));
                user.setHashSenha(resultados.getString("SENHA").toCharArray());
                user.setNomeDoFuncionario(resultados.getString("NOME"));
                user.setPapel(resultados.getString("NOME_PAPEL"));

                mapa.put(user.getLogin(), user);
            }

            return mapa;

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

    static {
//    USUARIOS_CADASTRADOS = new HashMap<String, Usuario>();
//    USUARIOS_CADASTRADOS.put("fulano", new Usuario("fulano", "abcd1234", new String[]{"ADMIN", "BASICO"}));
//    USUARIOS_CADASTRADOS.put("ciclano", new Usuario("ciclano", "abcd1234", new String[]{"BASICO"}));
//    USUARIOS_CADASTRADOS.put("ramonh", new Usuario("ramonh", "senac15", new String[]{"ADMIN"}));    
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
        nome = request.getParameter("login");
        senha = request.getParameter("password");

        USUARIOS_CADASTRADOS = carregarUsuarios(nome, senha);

        // Validar nome de usuário e senha.
        Usuario usuario = validar(nome, senha);
        if (usuario != null) {
            HttpSession sessao = request.getSession(false);
            if (sessao != null) {
                // Força invalidação da sessão anterior.
                sessao.invalidate();
            }
            sessao = request.getSession(true);
            sessao.setAttribute("usuario", usuario);

            // Redireciona para a a tela principal
            response.sendRedirect("Home");
            return;
            // FIM CASO SUCESSO
        }
        response.sendRedirect("erroLogin.jsp");

    }

    // Implementar aqui a validação do usuário com os dados
    // armazenados no banco de dados.
    private Usuario validar(String nome, String senha) {
        Usuario usuario = USUARIOS_CADASTRADOS.get(nome);
        if (usuario != null && usuario.autenticar(nome, senha)) {
            return usuario;
        }
        return null;
    }

}
