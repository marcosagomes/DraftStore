/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wrm.draftstore.servlets.autenticacao;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.wrm.draftstore.classes.*;

/**
 * http://www.journaldev.com/1933/java-servlet-filter-example-tutorial
 *
 * @author fernando.tsuda
 */
@WebFilter(filterName = "AutorizacaoFilter", urlPatterns = "/Servlet/*")
public class AutorizacaoFilter implements Filter {
  
  // Listagem das paginas validas para cada tipo de usuario
  static String[] listaPaginasVendas = {"BuscarProduto", "CadastrarProduto"};
  static String[] listaPaginasRetaguarda = {"BuscarFornecedor", "CadastrarFornecedor"};
  static String[] listaPaginasSuporte = {"BuscarFuncionario", "CadastrarFuncionario"};
  
  @Override
  public void doFilter(ServletRequest request, ServletResponse response,
          FilterChain chain)
          throws IOException, ServletException {

    // 1) OBTEM AS INFORMACOES DO USUARIO DA SESSAO
    // A) CAST DOS PARÂMETROS RECEBIDOS
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    HttpServletResponse httpResponse = (HttpServletResponse) response;

    // B) TENTA RECUPERAR A SESSÃO DO USUÁRIO
    HttpSession sessao = httpRequest.getSession();
    Usuario usuario = (Usuario) sessao.getAttribute("usuario");

    // 2) NA LÓGICA IMPLEMENTADA, SE EXISTIR OBJETO DO USUÁRIO SIGNIFICA
    //    QUE USUÁRIO ESTÁ LOGADO
    //    CASO CONTRÁRIO, REDIRECIONA PARA TELA DE LOGIN
    if (usuario == null) {
      httpResponse.sendRedirect("../login.jsp");
      return;
    }

    try {
      // 3) VERIFICAR SE USUARIO PODE ACESSAR PAGINA
      if (verificarAcesso(usuario, httpRequest, httpResponse)) {
        // CHAMADA QUE ENVIA A REQUISIÇÃO PARA O PRÓXIMO FILTRO OU SERVLET
            chain.doFilter(request, response);
      } else {
        // SE NAO PODER ACESSAR, APRESENTA ERRO
        httpResponse.sendRedirect("../erroNaoAutorizado.jsp");
      }
    } catch (IOException | ServletException t) {
      t.printStackTrace();
    }
  }

  private boolean verificarAcesso(Usuario usuario, HttpServletRequest req, HttpServletResponse resp) {
    String pagina = req.getRequestURI();
    if (checarPaginaAtual(pagina, listaPaginasRetaguarda) && usuario.autorizado("RETAGUARDA")){
      return true;
    } else if (checarPaginaAtual(pagina, listaPaginasSuporte) && usuario.autorizado("SUPORTE")) {
      return true;
    } else if (checarPaginaAtual(pagina, listaPaginasVendas) && usuario.autorizado("VENDAS")) {
      return true;
    } else if (usuario.autorizado("ADMIN")) {
      return true;
    }
    return false;
  }
  
  private boolean checarPaginaAtual(String pagina, String[] listaPaginasValidas){
    for (String p : listaPaginasValidas) {
      if(pagina.endsWith(p))
        return true;
    }
    return false;
  }

  /**
   * ROTINA PARA DESTRUIÇÃO DO FILTRO
   */
  @Override
  public void destroy() {
  }

  /**
   * ROTINA DE INICIALIZAÇÃO DO FILTRO
   */
  @Override
  public void init(FilterConfig filterConfig) {
  }

}
