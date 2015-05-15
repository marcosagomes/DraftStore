<%-- 
    Document   : registroDeVenda
    Created on : 09/05/2015, 21:02:09
    Author     : Marcos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>
        Draftstore - Registro de vendas
    </title>

    <!-- Bootstrap -->
    <link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <link href="../resources/css/estiloCadastro.css" type="text/css" rel="stylesheet">
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
    <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="../resources/css/estiloRegistra.css">
    <link rel="stylesheet" type="text/css" href="../resources/css/estiloCadastro.css">
    <link rel="icon" href="../resources/img/draft.ico" type="image/x-icon">
    <link rel="shortcut icon" href="../resources/img/draft.ico" type="image/x-icon">
</head>
<body>
    <header>
        <img id="logo" src="../resources/img/wrm-logoteste.png">
        <div class="texto" id="titulo"><h1>Registro de Venda</h1></div>
    </header>
    <div class="row">
        <div class="col-sm-3">
            <div class="sidebar-nav">
                <div class="navbar navbar-default" role="navigation">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-navbar-collapse">
                            <span class="sr-only">Menu</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <span class="visible-xs navbar-brand">Menu de navegação</span>
                    </div>
                    <div class="navbar-collapse collapse sidebar-navbar-collapse">
                        <ul class="nav navbar-nav">
                             <li>
                                    <a href="Home">Home</a>
                                </li>
                            <li>
                                <a href="RegistroVenda">Registro de vendas</a>
                            </li>
                            <li class="dropdown">
                                <a href="RegistroVenda"class="dropdown-toggle" data-toggle="dropdown">Produtos <b class="caret"></b></a>
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
                                <a href="#">Relatórios <span class="badge">4</span></a>
                            </li>
                        </ul>
                    </div>
                    <!--/.nav-collapse -->
                </div>
            </div>
        </div>
        <div class="col-sm-9">

            <caption>Buscar por produto</caption>
            <form action="#" 
                  class="form-search">
                <div class="submit-line">
                    <input type="text" name="buscaProduto" />
                    <button class="submit-lente" type="submit">
                        <i class="fa fa-search"></i>
                    </button>
                </div>
                
                <!--estou testando uma coisa aqui-->
                <div class="search-container">
                    <div class="ui-widget">
                        <input type="text" id="search" name="search" class="search" />
                    </div>
                </div>
                
            </form>

            <table class="table table">
                <caption>Carrinho</caption>
                <thead>
                    <tr>
                        <th>Tipo de produto</th>
                        <th>Quantidade</th>
                        <th>Preço</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${lista}" var="produto" varStatus="stats">
                    <tr id="lista[${stats.index}]">
                        <td>${produto.tipoProduto}</td>
                        <td>${produto.marca}</td>
                        <td>${produto.modelo}</td>
                        <td>${produto.precoVenda}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="form2">
            <form action="action" class="form-inline">
                <div>
                    <label for="data"> Data </label>
                    <input type="date" class="iptData" name="data">
                    <div class="quebra">
                        <label id="labelTotal"> Total </label>
                        <input type="number" placeholder="R$" class="iptTotal"> 
                    </div>
                    <br>
                </div>
                <div class="botao">
                    <button class="btn btn-small btn-primary" type="button">Cancelar</button>
                    <button class="btn btn-small" type="button">Confirmar Registro</button>

                </div>


            </form>
        </div>



    </div>       


    <script src="../resources/js/autoComplete.js"></script>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="../bootstrap/js/bootstrap.min.js"></script>
    <script src="../resources/js/autoComplete"></script>

</body>        
</html>
