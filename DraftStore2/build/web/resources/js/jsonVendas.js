// Requisitando o JSON do Servlet
var xhr = new XMLHttpRequest();
xhr.open("GET", "JsonVendasServlet", true);
xhr.setRequestHeader("Content-type", "application/json");
xhr.addEventListener("load", carregarJsonProdutos);
xhr.send();

var idProduto;
var jsonProdutos;
var produtoSelecionado;
var barraSearch = document.getElementsByClassName("pull-right search");

function carregarJsonProdutos() {
    jsonProdutos = JSON.parse(this.responseText);

    $(function () {
        $('#tabelita').bootstrapTable({
            data: jsonProdutos
        });
    });

    $(function () {
        $('#carrinho').bootstrapTable({
            data: dadosCarrinho
        });
    });

    barraSearch[0].classList.add("col-xs-5");
}

function queryParams() {
    return {
        type: 'owner',
        sort: 'updated',
        direction: 'desc',
        per_page: 3,
        page: 1
    };
}

var dadosCarrinho = [];
//
//$('#tabelita').on('click', 'tr', function (event) {
////    console.log(this.childNodes[1].textContent);
//    idProduto = this.childNodes[1].textContent;
//    var inputEditar = document.getElementById("inputHiddenEditar");
//    inputEditar.setAttribute("value", idProduto);
//    var inputRemover = document.getElementById("inputHiddenRemover");
//    inputRemover.setAttribute("value", idProduto);
//});

function operateFormatter(value, row, index) {
    return [
        '<a class="add" href="javascript:void(0)" title="Adicionar">',
        '<i class="glyphicon glyphicon-plus"></i>'
    ].join('');
}

function operateFormatter2(value, row, index) {
    return [
        '<a class="remover" href="javascript:void(0)" title="Remover">',
        '<i class="glyphicon glyphicon-remove"></i>'
    ].join('');
}

window.operateEvents = {
    'click .add': function (e, value, row, index) {
//        // Informacoes
//        alert('You click add icon, row: ' + JSON.stringify(row));
//        console.log(value, row, index);
//        console.log(dadosCarrinho);
//        console.log("ID: "+produtoSelecionado);
        produtoSelecionado = row;

        var jaEstaNoCarrinho = false;
        var posicaoElemento = 0;
        if (dadosCarrinho.length > 0) {
            for (var i in dadosCarrinho) {
                if (dadosCarrinho[i].idProduto == produtoSelecionado.idProduto) {
                    jaEstaNoCarrinho = true;
                    posicaoElemento = i;
                    break;
                }
            }
        }

        if (jaEstaNoCarrinho) {
            dadosCarrinho[posicaoElemento].quantidade++;
            dadosCarrinho[posicaoElemento].preco =
                    dadosCarrinho[posicaoElemento].precoUni * dadosCarrinho[posicaoElemento].quantidade;
        } else {
            dadosCarrinho.push({
                "idProduto": produtoSelecionado.idProduto,
                "quantidade": 1,
                "precoUni": produtoSelecionado.precoVenda,
                "preco": produtoSelecionado.precoVenda,
                "nomeProduto": produtoSelecionado.modelo,
            });
        }

        updateData(dadosCarrinho);
    },
    'click .remover': function (e, value, row, index) {
//        // Informacoes
//        alert('You click remove icon, row: ' + JSON.stringify(row));
        console.log(value, row, index);
        console.log(row.idProduto);
//        dadosCarrinho = removeFunction(dadosCarrinho, "idProduto", row.idProduto);
        produtoSelecionado = row;
        
        for (var i in dadosCarrinho) {
            if (dadosCarrinho[i].idProduto == produtoSelecionado.idProduto) {
                if(dadosCarrinho[i].quantidade > 1) {
                    dadosCarrinho[i].quantidade--;
                    dadosCarrinho[i].preco = dadosCarrinho[i].precoUni * dadosCarrinho[i].quantidade;
                } else {
                    dadosCarrinho.splice(i, 1);
//                    delete dadosCarrinho[i];
                }
                break;
            }
        }

        updateData(dadosCarrinho);
    }
};

function updateData(myData) {
    $('#carrinho').bootstrapTable("load", dadosCarrinho);

}

function removeFunction(meuArray, propriedade, valor)
{
    return meuArray.filter(function (val) {
        return val[propriedade] !== valor;
    });
}

function removerCarinha(meuArray, propriedade, valor) {
    for (var x in meuArray) {
        if (x.idProduto == valor) {
            console.log('found something! ' + x.idProduto + ' -- ' + valor);
        }
    }
}