/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wrm.draftstore.auxiliares;

import com.wrm.draftstore.database.ConexaoBDJavaDB;
import com.wrm.draftstore.servlets.editar.EditarFornecedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wilson A. Oliveira
 */
public class ScheduleProdutos extends TimerTask {

    Date now; // to display current time

    // Add your task here
    public void run() {
        
        ConexaoBDJavaDB conexaoBD = new ConexaoBDJavaDB("DraftOfficeDB");
        PreparedStatement stmt = null;
        Connection conn = null;

        String updateSql = "UPDATE TB_PRODUTO\n"
                + "            SET DATA_CRIACAO  = " + new Timestamp(new Date().getTime()) + ", \n"
                + "          WHERE ID_PRODUTO = 1";
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
}
