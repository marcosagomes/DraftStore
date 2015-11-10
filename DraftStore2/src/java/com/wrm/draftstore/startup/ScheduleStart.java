/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wrm.draftstore.startup;

import com.wrm.draftstore.auxiliares.ScheduleMain;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 *
 * @author Wilson A. Oliveira
 */
@WebServlet(name = "ScheduleStart", urlPatterns = {"/Servlet/ScheduleStart"})
public class ScheduleStart extends HttpServlet {

    @Override
    public void init() throws ServletException {
        ScheduleMain sm = new ScheduleMain();
    }
}
