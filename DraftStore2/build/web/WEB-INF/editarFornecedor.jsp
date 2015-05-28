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
    <title>Draftstore - Edicao de Fornecedor</title>

    <!-- Bootstrap -->
    <link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jasny-bootstrap/3.1.3/css/jasny-bootstrap.min.css">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <link href="../resources/css/estiloFornecedor.css" type="text/css" rel="stylesheet">
    <link rel="icon" href="../resources/img/draft.ico" type="image/x-icon">
    <link rel="shortcut icon" href="../resources/img/draft.ico" type="image/x-icon">
</head>
<body>
    <header>
        <img id="logo" src="../resources/img/wrm-logoteste.png">
        <div class="texto" id="titulo"><h1>Editar Fornecedor</h1></div>
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

            <div class="button-container">
                <form id="formulario"
                      action="EditarFornecedor"
                      class="form-search"
                      method="POST"
                      onSubmit="return valida(this)">

                    <div id="campoRazaoSocial" class="normalidade form-group ">
                        <label class="col-sm-3 control-label" for="RazaoSocial"> Razão Social </label>
                        <div class="col-sm-7">
                            <input type="text" id="inputRazaoSocial" 
                                   class="form-control" name="RazaoSocial" value="<c:out value="${Fornecedor.razaoSocial}"/>">
                            <!--<span class="glyphicon glyphicon-remove form-control-feedback"></span>-->
                        </div>
                    </div>

                    <div id="campoCNPJ" class="normal form-group">
                        <label class="col-sm-2 control-label cnpjMask" for="Cnpj"> CNPJ </label>
                        <div class="col-sm-3">
                            <input type="text" id="inputCNPJ" class="form-control" data-mask="99.999.999/9999-99" 
                                   placeholder="__.___.___/____-__" name="Cnpj" value="<c:out value="${Fornecedor.cnpj}"/>">
                            <!--<span class="glyphicon glyphicon-remove form-control-feedback"></span>-->
                        </div>
                    </div>

                    <div id="campoCep" class="normal form-group">
                        <label id="labelCep" class="col-sm-2 control-label" for="Cep"> CEP </label>
                        <div class="col-sm-3">
                            <input type="text" id="inputCep" class="form-control" data-mask="99999-999" 
                                   placeholder="_____-___" name="Cep" value="<c:out value="${Fornecedor.cep}"/>">
                            <!--<span class="glyphicon glyphicon-remove form-control-feedback"></span>-->
                        </div>
                    </div>

                    <div id="campoEndereco" class="normalidade form-group campoEndereco">
                        <label class="col-sm-3 control-label" for="Endereco"> Endereço </label>
                        <div class="col-sm-7">
                            <input type="text" id="inputEndereco" class="form-control" 
                                   name="Endereco" value="<c:out value="${Fornecedor.endereco}"/>" readonly>
                            <!--<span class="glyphicon glyphicon-remove form-control-feedback"></span>-->
                        </div>
                    </div>

                    <div id="campoNumero" class="normal form-group">
                        <label class="col-sm-2 control-label" for="Numero"> Numero </label>
                        <div class="col-sm-3">
                            <input type="text" id="inputNumero" class="form-control" 
                                   value="<c:out value="${Fornecedor.numero}"/>" name="Numero">
                            <!--<span class="glyphicon glyphicon-remove form-control-feedback"></span>-->
                        </div>
                    </div>

                    <div id="campoBairro" class="normal form-group">
                        <label id="labelBairro" class="col-sm-2 control-label" for="Bairro"> Bairro </label>
                        <div class="col-sm-3">
                            <input type="text" id="inputBairro" class="form-control" name="Bairro" 
                                   value="<c:out value="${Fornecedor.bairro}"/>" readonly>
                            <!--<span class="glyphicon glyphicon-remove form-control-feedback"></span>-->
                        </div>
                    </div>

                    <div id="campoCidade" class="normal form-group">
                        <label class="col-sm-2 control-label" for="Cidade"> Cidade </label>
                        <div class="col-sm-3">
                            <input type="text" id="inputCidade" class="form-control" 
                                   value="<c:out value="${Fornecedor.cidade}"/>" name="Cidade" readonly>
                            <!--<span class="glyphicon glyphicon-remove form-control-feedback"></span>-->
                        </div>
                    </div>

                    <div id="campoUf" class="normal form-group">
                        <label id="labelUf" class="col-sm-2 control-label" for="UF"> UF </label>
                        <div class="col-sm-3">
                            <input type="text" id="inputUF" class="form-control" 
                                   value="<c:out value="${Fornecedor.estado}"/>" name="UF" readonly>
                            <!--<span class="glyphicon glyphicon-remove form-control-feedback"></span>-->
                        </div>
                    </div>

                    <div id="campoTelefone" class="normal form-group">
                        <label class="col-sm-2 control-label" for="Telefone"> Telefone </label>
                        <div class="col-sm-3">
                            <input type="text" id="inputTelefone" class="form-control" 
                                   data-mask="(99) 9999-9999" placeholder="(__) ____-____" name="Telefone"
                                   value="<c:out value="${Fornecedor.telefone}"/>">
                            <!--<span class="glyphicon glyphicon-remove form-control-feedback"></span>-->
                        </div>
                    </div>

                    <div id="campoEmail" class="normal form-group">
                        <label id="labelEmail" class="col-sm-2 control-label" for="Email"> Email </label>
                        <div class=" col-sm-3 ">
                            <input type="text" id="inputEmail" class="form-control" name="Email"
                                   value="<c:out value="${Fornecedor.email}"/>">
                            <!--<span class="glyphicon glyphicon-remove form-control-feedback"></span>-->
                        </div>
                    </div>

                    <div id="campoSite" class="normalidade form-group">
                        <label class="col-sm-3 control-label" for="Site"> URL do Site </label>
                        <div class="col-sm-7">
                            <input type="text" id="inputSite" class="form-control" name="Site"
                                   value="<c:out value="${Fornecedor.site}"/>">
                            <!--<span class="glyphicon glyphicon-remove form-control-feedback"></span>-->
                        </div>
                    </div>


                    <input id="inputHiddenEditar" type="hidden" name="idFornecedor" value="<c:out value="${idFornec}"/>">
                    <div class="botao">
                        <button id="btnEditar" class="btn btn-default botaoDeAcao efetuar" type="submit">Efetuar mudanças</button>
                    </div>
                </form>

                <form id="formVoltar" 
                      action="BuscarFornecedor"
                      class="form-search"
                      onsubmit="return true">
                    <div class="botao">
                        <button id="btnCancelar" class="btn btn-small btn-default botaoDeAcao cancelar" type="submit">Cancelar</button>
                    </div>	
                </form>
            </div>

        </div>

    </div>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jasny-bootstrap/3.1.3/js/jasny-bootstrap.min.js"></script>
    <script src="../resources/js/validacoesCampos.js"></script>
    <script src="../resources/js/jsonFornecedores.js"></script>
</body>
</html>