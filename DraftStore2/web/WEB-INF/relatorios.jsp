<%-- 
    Document   : home
    Created on : 12/05/2015, 00:58:50
    Author     : Marcos
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="pt">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <title>Draftstore - Registro de venda</title>

        <!-- Bootstrap -->
        <link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jasny-bootstrap/3.1.3/css/jasny-bootstrap.min.css">

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
        <!--<link href="../resources/css/estiloRegistra.css" type="text/css" rel="stylesheet">-->
        <link href="../resources/css/estiloRelatorio.css" type="text/css" rel="stylesheet">
        <link href="../bootstrap-table/bootstrap-table.css" type="text/css" rel="stylesheet">
    </head>
    <body>
        <header>
            <img id="logo" src="../resources/img/wrm-logoteste.png">
            <div class="texto" id="titulo"><h1> Relatórios</h1></div>
        </header>
        <div class="row">
            <div class="col-sm-3">
                <div class="sidebar-nav">
                    <div class="navbar navbar-default" role="navigation">
                        <div class="navbar-header">
                            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-navbar-collapse">
                                <span class="sr-only">Toggle navigation</span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
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
                                            <a href="BuscarFuncionario">Buscar </a>
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
                                            <a href="BuscarFornecedor" >Buscar </a>
                                        </li>
                                        <li>
                                            <a href="CadastrarFornecedor">Cadastrar</a>
                                        </li>
                                    </ul>
                                </li>
                                <li>
                                    <a href="Log">Relatórios</a>
                                </li>
                            </ul>
                        </div>
                        <!--/.nav-collapse -->
                    </div>
                </div>
            </div>
            <div class="col-sm-9">
                <div id="tdImg">
                    <div class="log">
                        <a href="" onclick="javascript:getString(this, '${Caminho}');">
                            <img src="../resources/img/log.png">   
                            <p class="nome" > Logs </p>
                        </a>

                    </div>
                    <div class="desempenho">
                        <a href="RelatorioFuncionarios">
                            <img src="../resources/img/desempenho.png">   
                            <p class="nome">Desempenho </p>
                        </a>                
                    </div>
                    <div class="despesas">
                        <a href="RelatorioVendas">
                            <img src="../resources/img/gastos.png">   
                            <p class="nome">Lucro mensal </p>
                        </a>                
                    </div>

                </div>


            </div>
            <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
            <!-- Include all compiled plugins (below), or include individual files as needed -->
            <script src="../bootstrap/js/bootstrap.min.js"></script>
            <script src="../resources/js/jsonLog.js" type="text/javascript"></script>
    </body>
</html>