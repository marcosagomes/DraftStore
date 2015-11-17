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
    <title>Draftstore - Cadastro de produto</title>

    <!-- Bootstrap -->
    <link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jasny-bootstrap/3.1.3/css/jasny-bootstrap.min.css">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <link href="../resources/css/estiloProduto.css" type="text/css" rel="stylesheet">
</head>
<body>
    <header>
        <img id="logo" src="../resources/img/wrm-logoteste.png">
        <div class="texto" id="titulo"><h1>Cadastrar Produto</h1></div>
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

            <form 
                id="formulario"
                class="form-inline"
                onSubmit="return valida(this)"
                action="CadastrarProduto"
                method="POST"
                name="produtoForm">

                <div id="campoTipoProduto" class="normal form-group" >
                    <label class="col-sm-3 control-label" for="Categoria"> Categoria </label>
                    <div class="col-sm-2 from-group">
                        <select class="form-control" id="selectCategoria"
                                name="selectCategoria" onchange="SubCatProduto(this)">
                            <option  selected="selected" value=""> Selecione </option>
                            <c:forEach items="${CatProduto}" var="cat" varStatus="stat">
                                <option value="${cat.value}"> ${cat.nome} </option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <div id="campoSubTipoProduto" class="normal form-group ">
                    <label class="col-sm-3 control-label" for="subCategoria"> Sub Categoria </label>
                    <div class="col-sm-2">
                        <select class="form-control" id="selectSubCategoria" name="subTipo">
                            <option selected="selected"  value="">Selecione</option>
                        </select>
                    </div>
                </div>

                <div id="campoFornecedor" class="normal form-group">
                    <label class="col-sm-3 control-label" for="selectFornecedor"> Fornecedor </label>
                    <div class="col-sm-2 from-group">
                        <select class="form-control" id="selectFornecedor" name="Fornecedor">
                            <option selected="selected" value="">Selecione</option>
                            <c:forEach items="${lista}" var="fornec" varStatus="stats">
                                <option value="${fornec.idFornecedor}">${fornec.razaoSocial}</option>
                            </c:forEach>
                        </select>
                        <c:forEach items="${lista}" var="fornec" varStatus="stats">
                            <input type="hidden" name="${fornec.idFornecedor}" value="${fornec.razaoSocial}">
                        </c:forEach>
                    </div>
                </div>


                <div id="campoMarca" class="normal form-group ">
                    <label class="col-sm-3 control-label" for="Marca"> Marca </label>
                    <div class="col-sm-2">
                        <input type="text" id="inputMarca" class="form-control" name="Marca">
                        <!--<span class="glyphicon glyphicon-remove form-control-feedback"></span>-->
                    </div>
                </div>

                <div id="campoModelo" class="normal form-group ">
                    <label class="col-sm-3 control-label" for="Modelo"> Modelo </label>
                    <div class="col-sm-2">
                        <input type="text" id="inputModelo" class="form-control" name="Modelo">
                        <!--<span class="glyphicon glyphicon-remove form-control-feedback"></span>-->
                    </div>
                </div>

                <div id="campoCusto" class="normal form-group ">
                    <label class="col-sm-3 control-label" for="Custo"> Custo </label>
                    <div class="col-sm-2">
                        <input type="text"  id="inputCusto" class="form-control" name="Custo" onkeypress="return SomenteNumero(event);">
                        <!--<span class="glyphicon glyphicon-remove form-control-feedback"></span>-->
                    </div>
                </div>

                <div id="campoLucro" class="normal form-group">
                    <label class="col-sm-2 control-label" for="Lucro"> % de Lucro </label>
                    <div class="col-sm-3">
                        <input type="text"  id="inputLucro" class="form-control" name="lucro" onkeypress="return SomenteNumero(event);">
                        <!--<span class="glyphicon glyphicon-remove form-control-feedback"></span>-->
                    </div>
                </div>

                <div id="campoPreco" class="normalidade form-group">
                    <label class="col-sm-3 control-label" for="Preco"> Preco de Venda </label>
                    <div class="col-sm-2">
                        <input type="text" id="inputPreco" class="form-control" name="preco" placeholder="R$ 00,00" readonly>
                        <!--<span class="glyphicon glyphicon-remove form-control-feedback"></span>-->
                    </div>
                </div>

                <div id="campoPrecoPromo" class="normalidade form-group">
                    <label class="col-sm-3 control-label" for="precoPromo"> Preco Promocional </label>
                    <div class="col-sm-2">
                        <input type="text" id="inputPrecoPromo" class="form-control" name="precoPromo" placeholder="R$ 00,00">
                        <!--<span class="glyphicon glyphicon-remove form-control-feedback"></span>-->
                    </div>
                </div>

                <div id="campoDataIni" class="normalidade form-group">
                    <label class="col-sm-3 control-label" for="dataEventoIni"> Data Início da Promoção </label>
                    <div class="col-sm-2">
                        <input type="text" id="inputDataEventoIni" class="form-control" name="dataEventoIni" data-mask="99/99/9999">
                        <!--<span class="glyphicon glyphicon-remove form-control-feedback"></span>-->
                    </div>
                </div>

                <div id="campoDataFim" class="normalidade form-group">
                    <label class="col-sm-3 control-label" for="dataEventoFim"> Data Fim da Promoção </label>
                    <div class="col-sm-2">
                        <input type="text" id="inputDataEventoFim" class="form-control" name="dataEventoFim" data-mask="99/99/9999">
                        <!--<span class="glyphicon glyphicon-remove form-control-feedback"></span>-->
                    </div>
                </div>

                <div id="campoQuantidade" class="normal form-group" >
                    <label class="col-sm-3 control-label" for="quantidade"> Quantidade </label>
                    <div class="col-sm-2 from-group">
                        <input type="text" class="form-control" id="numberQuantidade" name="quantidade"  onkeypress="return SomenteNumero(event);">
                    </div>
                </div>

                <div id="campoCaminhoImagem" class="normal form-group" >
                    <label class="col-sm-3 control-label" for="imagem"> Caminho da imagem </label>
                    <div class="col-sm-2 from-group">
                        <input type="text" class="form-control" id="imageCaminho" name="imagem">
                    </div>
                </div>



                <div id="campoPreview" class="normal form-group" >
                    <label class="col-sm-3 control-label" for="btnTeste"> Preview </label>
                    <div class="col-sm-2 from-group">
                        <input type="button" class="form-control" id="btnTeste" name="btnTeste" onclick="setSourceOnImg()" value="Abrir">              
                        <input type="button" class="form-control" id="btnTeste2" name="btnTeste2" onclick="fecharPreview()" value="Fechar" style="display: none">
                    </div>
                </div>

                <div id="campoImagem" class="normal form-group" >
                    <div class="col-sm-2 from-group">
                        <img id="imgTeste"/>

                    </div>
                </div>

                <div id="campoDescImagem" class="normal form-group" >
                    <label class="col-sm-3 control-label" for="descImagem"> Descrição da imagem </label>
                    <div class="col-sm-2 from-group">
                        <input type="text" class="form-control" id="descImagem" name="descImagem">
                    </div>
                </div>

                <div id="campoDescricaoProduto" class="normal form-group" >
                    <label class="col-sm-3 control-label" for="descricao"> Descricao do Produto</label>
                    <div class="col-sm-2 from-group">
                        <textarea class="form-control" id="textareaDescricao" name="descricao"></textarea>
                    </div>
                </div>

                <div class="botoesFormulario">
                    <button type="reset" class="btn btn-default" >Cancelar</button>
                    <button type="submit" class="btn btn-primary" id="btCadastrar">Cadastrar</button>
                </div>
            </form>
        </div>
    </div>
    <script>
        function SubCatProduto(select) {
            var categoria = document.getElementById('selectSubCategoria');
        <c:choose>
            <c:when test="${SubCatProduto == null}"></c:when>
            <c:otherwise>
            categoria.innerHTML = '';
                <c:forEach items="${SubCatProduto}" var="sub" varStatus="stat">
            if (select.value === "${sub.fkValue}") {
                var opt = document.createElement('option');
                opt.value = ${sub.value};
                opt.innerHTML = '${sub.nome}';
                categoria.appendChild(opt);
            }
                </c:forEach>
            </c:otherwise>
        </c:choose>
        }
        function setSourceOnImg() {
            var url = document.getElementById("imageCaminho").value;
            var desc = document.getElementById("descImagem").value;
            var img = document.getElementById("imgTeste");
            var close = document.getElementById("btnTeste2");

            img.setAttribute("src", url);
            img.setAttribute("alt", desc);
            close.style.display = "block";
        }
        function fecharPreview() {
            var img = document.getElementById("imgTeste");
            var close = document.getElementById("btnTeste2");

            img.setAttribute("src", "");
            img.setAttribute("alt", "");
            close.style.display = "none";
        }
    </script>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jasny-bootstrap/3.1.3/js/jasny-bootstrap.min.js"></script>
    <script src="../resources/js/validaProduto.js"></script>
    <script src="../resources/js/previsaoPrecoVenda.js"></script>
</body>
</html>

