/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wrm.draftstore.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe de exemplo para obter uma conexão JDBC com banco de dados
 * Baseado em http://www.tutorialspoint.com/jdbc/jdbc-sample-code.htm
 * @author Fernando
 */
public abstract class ConexaoBD {

  protected abstract String getJDBCDriver();

  protected abstract String getBancoUrl();

  protected abstract String[] getCredenciaisAcesso();

  public Connection obterConexao() throws SQLException, ClassNotFoundException {
    Connection conn = null;
    // Passo 1: Registrar driver JDBC.
    Class.forName(getJDBCDriver());

    // Passo 2: Abrir a conexão
    System.out.println("Connecting to database...");
    String[] credenciais = getCredenciaisAcesso();
    if (credenciais.length == 2) {
      conn = DriverManager.getConnection(getBancoUrl(), credenciais[0], credenciais[1]);
    }
    return conn;
  }
}
