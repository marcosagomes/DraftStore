/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wrm.draftstore.servlets.registroVenda;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.wrm.draftstore.classes.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Marcos
 */
@WebServlet(name = "RegistroVenda", urlPatterns = {"/Servlet/RegistroVenda"})
public class RegistroVenda2 extends HttpServlet {

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
    request.getRequestDispatcher("../WEB-INF/registroDeVenda.jsp").forward(request, response);
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
    processRequest(request, response);

    String jsonCarrinho = request.getParameter("jsonCarrinho");
//    String teste = "{\"idProduto\":5,\"quantidade\":3,\"precoUni\":120,\"preco\":360,\"nomeProduto\":\"Placa-mÃ£e 03\"}";
    System.out.println("JSON DO CARRINHO! -> " + jsonCarrinho);

//    Gson gson = new Gson();
////    JsonArray lista = gson.fromJson(jsonCarrinho, JsonArray.class);
////    for (JsonElement l : lista) {
////      
////    }
//    Map<String, Object> javaRootMapObject = new Gson().fromJson(teste, Map.class);
//
////    System.out.println(
////            javaRootMapObject.get("idProduto")
////    );

    Type listType = new TypeToken<List<ItemVenda>>(){}.getType();
    List<ItemVenda> target = new LinkedList<>();
    
    Gson gson = new Gson();
    List<ItemVenda> lista = gson.fromJson(jsonCarrinho, listType);
    
    for (ItemVenda item : lista) {
      System.out.print("AE MLK! "+item.getNomeProduto());
      System.out.println("// QTD"+item.getQuantidade());
    }
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
