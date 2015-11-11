<%-- 
    Document   : cadastrarProdutos
    Created on : 16/04/2015, 20:11:40
    Author     : ramon.ahonorio
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
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
    <link href="../resources/css/estilos.css" type="text/css" rel="stylesheet">
    <link href="../bootstrap-table/bootstrap-table.css" type="text/css" rel="stylesheet">

</head>
<body>
    <header>
        <img id="logo" src="../resources/img/wrm-logoteste.png">
        <div class="texto" id="titulo"><h1>Registrar Venda</h1></div>
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
                                        <a href="BuscarFornecedor">Buscar </a>
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
            <!-- ********************** Inserir o conteudo aqui! ********************** -->
            <label>Pesquisa de produtos</label>

            <table id="tabelita" 
                   data-click-to-select="true"
                   data-formatter="stateFormatter"
                   data-select-item-name="radioSelecionado"
                   data-search="true"
                   data-height="175"
                   data-toolbar="#toolbar"
                   data-show-toggle="true"
                   data-show-columns="true"
                   data-query-params="queryParams"
                   data-response-handler="responseHandler">
                <thead>
                    <tr>
                        <th data-field="idProduto">ID</th>
                        <th data-field="marca">Marca</th>
                        <th data-field="modelo">Modelo</th>
                        <th data-field="precoVenda">Preço</th>
                        <th data-align="center" data-field="operate" data-formatter="operateFormatter" 
                            data-events="operateEvents">Adicionar</th>
                    </tr>
                </thead>
            </table>

            <br>
            <label>Carrinho de compras</label>

            <table id="carrinho" 
                   data-click-to-select="true"
                   data-formatter="stateFormatter"
                   data-select-item-name="radioSelecionado"
                   data-height="225"
                   data-toolbar="#toolbar"
                   data-show-toggle="true"
                   data-show-columns="true"
                   data-query-params="queryParams"
                   data-response-handler="responseHandler">
                <thead>
                    <tr>
                        <th data-field="idProduto">ID Produto</th>
                        <th data-field="nomeProduto">Modelo</th>
                        <th data-field="quantidade">Quantidade</th>
                        <th data-field="preco" >Preço</th>
                        <th data-align="center" data-field="operate" data-formatter="operateFormatter2" 
                            data-events="operateEvents">Remover</th>
                    </tr>
                </thead>
            </table>

            <div class="camposAbaixoCarrinho" >

                <form id="formVenda" 
                      action="RegistroVenda"
                      method="post"
                      charset=utf-8>

                    <div class="col-sm-2" id="campoRegistrar form-group">
                        <label for="totalVenda" id="labelTotalVenda">Total (R$)</label>
                        <div class="col-sm-3">
                            <input id="totalVenda" name="totalVenda" type="text" class="btn btn-default" readonly/>
                        </div>
                    </div>

                    <div class="col-sm-5" id="btRegistrar">
                        <button type="submit" class="btn btn-primary">Registrar</button>
                    </div>
                </form>
            </div>
        </div>


        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="../bootstrap-table/bootstrap-table.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jasny-bootstrap/3.1.3/js/jasny-bootstrap.min.js"></script>
        <!--<script src="../resources/js/validacoesCampos.js"></script>-->
        <script src="../resources/js/jsonVendas.js"></script>
</body>
</html>