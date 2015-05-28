<%-- 
Document   : cadastrarFornecedores
Created on : 23/04/2015, 21:24:58
Author     : ramon.ahonorio
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head
        content must come *after* these tags -->
        <title>
            Draftstore - Busca de funcionário
        </title>
        <!-- Bootstrap -->
        <link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media
        queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file://
        -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js">
                                                
          </script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js">
                                                
          </script>
        <![endif]-->
        <link href="../resources/css/estiloBuscarFuncionario.css" type="text/css" rel="stylesheet">
        <link href="../bootstrap-table/bootstrap-table.css" type="text/css" rel="stylesheet">
        <link rel="icon" href="../resources/img/draft.ico" type="image/x-icon">
        <link rel="shortcut icon" href="../resources/img/draft.ico" type="image/x-icon">
    </head>

    <body>
        <header>
            <img id="logo" src="../resources/img/wrm-logoteste.png">
            <div class="texto" id="titulo">
                <h1>
                    Buscar Funcionário
                </h1>
            </div>
        </header>
        <div class="row">
            <div class="col-sm-3">
                <div class="sidebar-nav">
                    <div class="navbar navbar-default" role="navigation">
                        <div class="navbar-header">
                            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-navbar-collapse">
                                <span class="sr-only">
                                    Toggle navigation
                                </span>
                                <span class="icon-bar">
                                </span>
                                <span class="icon-bar">
                                </span>
                                <span class="icon-bar">
                                </span>
                            </button>
                            <span class="visible-xs navbar-brand">Menu de navegação</span>
                        </div>
                        <div class="navbar-collapse collapse sidebar-navbar-collapse">
                            <ul class="nav navbar-nav">
                                <li>
                                    <a href="../Home">Home</a>
                                </li>
                                <li>
                                    <a href="RegistroVenda">Registro de vendas</a>
                                </li>
                                <li class="dropdown">
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Produtos <b class="caret"></b></a>
                                    <ul class="dropdown-menu dropdown-menu-right">
                                        <li>
                                            <a href="BuscarProduto">Buscar </a>
                                        </li>
                                        <li>
                                            <a href="CadastrarProduto">Cadastrar</a>
                                        </li>
                                    </ul>
                                </li>
                                <li class="dropdown">
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Funcionários <b class="caret"></b></a>
                                    <ul class="dropdown-menu dropdown-menu-right">
                                        <li>
                                            <a href="BuscarFuncionario">Buscar</a>
                                        </li>
                                        <li>
                                            <a href="CadastrarFuncionario">Cadastrar</a>
                                        </li>

                                    </ul>
                                </li>
                                <li class="dropdown">
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">Fornecedores <b class="caret"></b></a>
                                    <ul class="dropdown-menu dropdown-menu-right">
                                        <li>
                                            <a href="BuscarFornecedor">Buscar</a>
                                        </li>
                                        <li>
                                            <a href="CadastrarFornecedor">Cadastrar</a>
                                        </li>
                                    </ul>
                                </li>
                                <li>
                                    <a href="#">Relatórios</a>
                                </li>
                            </ul>
                        </div>
                        <!--/.nav-collapse -->
                    </div>
                </div>
            </div>
            <div class="col-sm-9">

                <table id="tabelita" 
                       data-click-to-select="true"
                       data-formatter="stateFormatter"
                       data-select-item-name="radioSelecionado"
                       data-pagination="true"
                       data-search="true"
                       data-height="460"
                       data-toolbar="#toolbar"
                       data-show-toggle="true"
                       data-show-columns="true"
                       data-query-params="queryParams"
                       data-response-handler="responseHandler"
                       data-sort-name="idFuncionario" data-sort-order="asc">
                    <thead>
                        <tr>
                            <th data-field="state" data-radio="true"></th>
                            <th data-field="idFuncionario" data-sortable="true">ID</th>
                            <th data-field="nome" data-sortable="true">Nome</th>
                            <th data-field="cpf" data-sortable="true">CPF</th>
                            <th data-field="email" data-sortable="true">Email</th>
                        </tr>
                    </thead>
                </table>


                <div class="button-container">
                    <form id="formEditar"
                          class="form-search"
                          method="GET"
                          action="EditarFuncionario">
                        <input id="inputHiddenEditar" type="hidden" name="idFuncionario" value="">
                        <div class="botao">
                            <button class="btn btn-primary botaoDeAcao" type="submit">Editar</button>
                        </div>	
                    </form>

                    <form id="formRemover" 
                          action="RemoverFuncionario"
                          method="GET"
                          class="form-search" 
                          onsubmit="return removerFornecedor(this)">
                        <input id="inputHiddenRemover" type="hidden" name="idFuncionario" value="">
                        <div class="botao">
                            <button class="btn btn-small btn-default botaoDeAcao" type="submit">Desativar</button>
                        </div>	
                    </form>
                </div>
            </div>
        </div>


        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
        <script type="text/javascript" src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script type="text/javascript" src="../bootstrap/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="../bootstrap-table/bootstrap-table.min.js"></script>
        <script src="../resources/js/selecionarBusca.js"></script>
        <script src="../resources/js/jsonFuncionario.js"></script>

    </body>

</html>