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
    <title>Draftstore - Cadastro de Funcionario</title>

    <!-- Bootstrap -->
    <link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jasny-bootstrap/3.1.3/css/jasny-bootstrap.min.css">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <link href="../resources/css/estiloFuncionario.css" type="text/css" rel="stylesheet">
</head>
<body>
    <header>
        <img id="logo" src="../resources/img/wrm-logoteste.png">
        <div class="texto" id="titulo"><h1>Cadastrar Funcionário</h1></div>
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
                                <a href="#">Relatórios</a>
                            </li>
                        </ul>
                    </div>
                    <!--/.nav-collapse -->
                </div>
            </div>
        </div>
        <div class="col-sm-9">
            <!-- ********************** Inserir o conteudo aqui! ********************** -->

            <form id="formulario" class="form-inline" onSubmit="return validar(this)" method="POST" action="CadastrarFuncionario">

                <div id="campoNome" class="normalidade form-group ">
                    <label class="col-sm-3 control-label" for="Nome"> Nome </label>
                    <div class="col-sm-7">
                        <input type="text" id="inputRazaoSocial" class="form-control" name="Nome">
                        <!--<span class="glyphicon glyphicon-remove form-control-feedback"></span>-->
                    </div>
                </div>

                <div id="campoDtNascimento" class="normal form-group">
                    <label class="col-sm-2 control-label" for="Data"> Nascimento </label>
                    <div class="col-sm-3">
                        <input type="text" id="inputData" class="form-control" name="Data" data-mask="99/99/9999">
                        <!--<span class="glyphicon glyphicon-remove form-control-feedback"></span>-->
                    </div>
                </div>
                <div id="campoSexo" class="normal form-group">
                    <label class="col-sm-2 control-label" for="Sexo"> Sexo </label>
                    <div class="col-sm-3 from-group">
                        <select class="form-control" id="selectSexo" name="Sexo">
                            <option selected="selected"  value="">Selecione</option>
                            <option value="Masculino"> Masculino </option>
                            <option value="Feminino"> Feminino </option>
                        </select>
                    </div>
                </div>

                <div id="campoCPF" class="normal form-group">
                    <label class="col-sm-2 control-label" for="CPF"> CPF </label>
                    <div class="col-sm-3">
                        <input type="text" id="inputCPF" class="form-control" name="CPF" placeholder="___.___.___-__"  data-mask = "999.999.999-99">
                        <!--<span class="glyphicon glyphicon-remove form-control-feedback"></span>-->
                    </div>
                </div>

                <div id="campoRG" class="normal form-group">
                    <label class="col-sm-2 control-label" for="RG"> RG </label>
                    <div class="col-sm-3">
                        <input type="text" id="inputRg" class="form-control" name="RG" placeholder="__.___.___-_"  data-mask = "99.999.999-*">
                        <!--<span class="glyphicon glyphicon-remove form-control-feedback"></span>-->
                    </div>
                </div>

                <div id="campoTelefone" class="normal form-group">
                    <label class="col-sm-2 control-label" for="Telefone"> Telefone </label>
                    <div class="col-sm-3">
                        <input type="text" id="inputTelefone" class="form-control" name="Telefone"  data-mask="(99) 9999-9999" placeholder="( ) ____-____">
                        <!--<span class="glyphicon glyphicon-remove form-control-feedback"></span>-->
                    </div>
                </div>
                <div id="campoCelular" class="normal form-group">
                    <label class="col-sm-2 control-label" for="Telefone"> Celular </label>
                    <div class="col-sm-3">
                        <input type="text" id="inputCelular" class="form-control" name="Celular" data-mask="(99) 9999-9999?9">
                        <!--<span class="glyphicon glyphicon-remove form-control-feedback"></span>-->
                    </div>
                </div>
                <div id="campoCargo" class="normal form-group">
                    <label class="col-sm-2 control-label" for="Cargo"> Cargo </label>
                    <div class="col-sm-3 from-group">
                        <select class="form-control" id="selectCargo" name="Cargo">
                            <option selected="selected" value="">Selecione</option>
                            <option value="2"> Retaguarda </option>
                            <option value="3"> Suporte </option>
                            <option value="4"> Venda </option>
                        </select>
                    </div>
                </div>
                <div id="campoEmail" class="normal form-group ">
                    <label class="col-sm-3 control-label" for="Email"> Email </label>
                    <div class="col-sm-7">
                        <input type="text" id="inputEmail" class="form-control" name="Email">
                        <!--<span class="glyphicon glyphicon-remove form-control-feedback"></span>-->
                    </div>
                </div>
                <div id="campoSenha" class="normal form-group">
                    <label class="col-sm-2 control-label" for="Senha"> Senha </label>
                    <div class="col-sm-3">
                        <input type="password" id="inputSenha" class="form-control" name="Senha">
                        <!--<span class="glyphicon glyphicon-remove form-control-feedback"></span>-->
                    </div>
                </div>
                <div id="campoSenhaC" class="normal form-group">
                    <label class="col-sm-2 control-label" for="ConfirmeSenha"> Confirme </label>
                    <div class="col-sm-3">
                        <input type="password" id="inputSenhaC" class="form-control" name="ConfirmeSenha">
                        <!--<span class="glyphicon glyphicon-remove form-control-feedback"></span>-->
                    </div>
                </div>
                <div class="botoesFormulario">
                    <button type="reset" class="btn btn-default">Cancelar</button>
                    <button type="submit" class="btn btn-primary">Cadastrar</button>
                </div>


        </div>
    </form>

</div>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jasny-bootstrap/3.1.3/js/jasny-bootstrap.min.js"></script>
<script src="../resources/js/validaFuncionario.js"></script>
</body>
</html>