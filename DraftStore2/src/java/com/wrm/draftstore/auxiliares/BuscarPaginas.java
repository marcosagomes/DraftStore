/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wrm.draftstore.auxiliares;

import com.wrm.draftstore.classes.Usuario;
import com.wrm.draftstore.database.ConexaoBDJavaDB;
import com.wrm.draftstore.servlets.busca.BuscarFornecedor;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ramonhonorio
 */
public class BuscarPaginas {
  
  // TESTE
  public static void main(String[] args) {
    BuscarPaginas bp = new BuscarPaginas();
    String[] papeis = {"RETAGUARDA"};
    Usuario user = new Usuario("2", "2", papeis);
    
    List<String> abc = bp.listaDePaginas(user);
    for (String s : abc) {
      System.out.println(s);
    }
  }

  public List<String> listaDePaginas(Usuario usuario) {
    ConexaoBDJavaDB conexaoBD = new ConexaoBDJavaDB("DraftOfficeDB");
    Statement stmt = null;
    Connection conn = null;
    
    String sqlIdPapel = "SELECT ID_PAPEL\n" +
    "    FROM TB_PAPEL\n" +
    "    WHERE TB_PAPEL.NOME_PAPEL = '"+usuario.getPapel()+"'";

    try {
      conn = conexaoBD.obterConexao();
      stmt = conn.createStatement();
      ResultSet resultados = stmt.executeQuery(sqlIdPapel);
      
      int idPapel = 0;
      while(resultados.next()){
        idPapel = resultados.getInt("ID_PAPEL");
      }
      
      String sqlPagina = "SELECT NOME_PAGINA\n" +
      "    FROM TB_PAGINA\n" +
      "    JOIN TB_PAPEL\n" +
      "    ON TB_PAGINA.FK_PAPEL = TB_PAPEL.ID_PAPEL\n" +
      "    WHERE TB_PAGINA.FK_PAPEL = "+idPapel+" ";
      
      resultados = stmt.executeQuery(sqlPagina);

      List<String> lista = new ArrayList<>();
      
      while (resultados.next()) {
        lista.add(resultados.getString("NOME_PAGINA"));
      }

      return lista;

    } catch (SQLException | ClassNotFoundException ex) {
      Logger.getLogger(BuscarPaginas.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      if (stmt != null) {
        try {
          stmt.close();
        } catch (SQLException ex) {
          Logger.getLogger(BuscarPaginas.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
      if (conn != null) {
        try {
          conn.close();
        } catch (SQLException ex) {
          Logger.getLogger(BuscarPaginas.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    }
    return null;
  }
}
