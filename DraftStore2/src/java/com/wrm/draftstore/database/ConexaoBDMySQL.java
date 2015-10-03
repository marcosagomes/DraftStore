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
public class ConexaoBDMySQL extends ConexaoBD {
  
  private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
  
  private static final String CONN_STRING = "jdbc:mysql://localhost:3306/";
  
  private String nomeBanco;
  
  public ConexaoBDMySQL(String nomeBanco) {
    this.nomeBanco = nomeBanco;
  }

  @Override
  protected String getJDBCDriver() {
    return JDBC_DRIVER;
  }

  @Override
  protected String getBancoUrl() {
    return CONN_STRING + nomeBanco + "?zeroDateTimeBehavior=convertToNull";
  }

  @Override
  protected String[] getCredenciaisAcesso() {
    String[] credenciais = new String[2];
    credenciais[0] = "root"; // Nome do usuario do BD
    credenciais[1] = "root"; // Senha do BD
    return credenciais;
  }
}
