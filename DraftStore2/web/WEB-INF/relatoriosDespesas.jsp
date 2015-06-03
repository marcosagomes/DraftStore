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
        <script type="text/javascript" src="../chartjs/Chart.js"></script>
        <link href="../resources/css/relatorioVendas.css" rel="stylesheet" type="text/css"/>
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
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
                <!-- Include all compiled plugins (below), or include individual files as needed -->
                <script src="../bootstrap/js/bootstrap.min.js"></script>
                <script src="../amcharts/amcharts.js" type="text/javascript"></script>
                <script src="../amcharts/serial.js" type="text/javascript"></script>
                <script src="../amcharts/themes/light.js" type="text/javascript"></script>
                <form  class="form-inline" >
                    <div class="meses">
                    <div  clas="form-group" id="primeiro">
                        <label class="col-sm-2 control-label"> 1º Data </label>
                        <input type="number"class="form-control"min="1" max="12" > 
                    </div>
                    <div clas="form-group" id="segundo">
                        <label class="col-sm-2 control-label"> 2º Data </label>
                        <input type="number" class="form-control" min="1" max="12" > 
                    </div>
                    </div>
                    <div >
                        <button id="botaoRelatorio" class="btn btn-primary" type="submit">Gerar Relatório</button>
                    </div>
                </form>
                <div id="chartdiv"></div>
                <div class="container-fluid">
                    <div class="row text-center" style="overflow:hidden;">
                        <div class="col-sm-3" style="float: none !important;display: inline-block;">
                            <label class="text-left">Angulo:</label>
                            <input class="chart-input" data-property="angle" type="range" min="0" max="89" value="30" step="1"/>	
                        </div>

                        <div class="col-sm-3" style="float: none !important;display: inline-block;">
                            <label class="text-left">Profundidade:</label>
                            <input class="chart-input" data-property="depth3D" type="range" min="1" max="120" value="20" step="1"/>
                        </div>
                    </div>
                </div>	
                <script>
                    var chart = AmCharts.makeChart("chartdiv", {
                        "theme": "light",
                        "type": "serial",
                        "path": "http://www.amcharts.com/lib/3/",
                        "dataProvider": [{
                                "year": "${mesAnterior}",
                                "income": ${vendaMesAnterior}
                            }, {
                                "year": "${mesAtual}",
                                "income": ${vendaMesAtual}
                            }],
                        "valueAxes": [{
                                "title": "Relatório de vendas"
                            }],
                        "graphs": [{
                                "balloonText": "Encontra-se em: [[category]]:[[value]]",
                                "fillAlphas": 1,
                                "lineAlpha": 0.2,
                                "title": "Income",
                                "type": "column",
                                "valueField": "income"
                            }],
                        "depth3D": 20,
                        "angle": 30,
                        "rotate": true,
                        "categoryField": "year",
                        "categoryAxis": {
                            "gridPosition": "start",
                            "fillAlpha": 0.05,
                            "position": "left"
                        },
                        "export": {
                            "enabled": true
                        }
                    });
                    jQuery('.chart-input').off().on('input change', function () {
                        var property = jQuery(this).data('property');
                        var target = chart;
                        chart.startDuration = 0;

                        if (property == 'topRadius') {
                            target = chart.graphs[0];
                            if (this.value == 0) {
                                this.value = undefined;
                            }
                        }

                        target[property] = this.value;
                        chart.validateNow();
                    });
                </script>
            </div>
        </div>
        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->

    </body>
</html>