/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wrm.draftstore.database;

/**
 *
 * @author Fernando
 */
public class ConexaoBDJavaDB extends ConexaoBD {
  
  private static final String JDBC_DRIVER = "org.apache.derby.jdbc.ClientDataSource";
  
  private static final String CONN_STRING = "jdbc:derby://localhost:1527/";
  
  private String nomeBanco;
  
  public ConexaoBDJavaDB(String nomeBanco) {
    this.nomeBanco = nomeBanco;
  }

  @Override
  protected String getJDBCDriver() {
    return JDBC_DRIVER;
  }

  @Override
  protected String getBancoUrl() {
    return CONN_STRING + nomeBanco + ";SecurityMechanism=3";
  }

  @Override
  protected String[] getCredenciaisAcesso() {
    String[] credenciais = new String[2];
    credenciais[0] = "root"; // Nome do usuario do BD
    credenciais[1] = "root"; // Senha do BD
    return credenciais;
  }
}
