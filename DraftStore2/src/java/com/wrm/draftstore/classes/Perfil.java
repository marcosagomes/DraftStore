/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wrm.draftstore.classes;

import java.util.ArrayList;

/**
 *
 * @author ramonhonorio
 */
public class Perfil {
    
    // Propriedades
    private String idPerfil;
    private ArrayList<String> telasDeAcesso;
    
    // Metodos acessores
    public String getIdPerfil() {
        return idPerfil;
    }
    public void setIdPerfil(String idPerfil) {
        this.idPerfil = idPerfil;
    }

    public ArrayList<String> getTelasDeAcesso() {
        return telasDeAcesso;
    }
    public void setTelasDeAcesso(ArrayList<String> telasDeAcesso) {
        this.telasDeAcesso = telasDeAcesso;
    }

    
}
