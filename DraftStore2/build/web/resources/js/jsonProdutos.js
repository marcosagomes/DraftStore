// Requisitando o JSON do Servlet
var xhr = new XMLHttpRequest();
xhr.open("GET", "JsonProdutosServlet", true);
xhr.setRequestHeader("Content-type", "application/json");
xhr.addEventListener("load", carregarJSONProduto);
xhr.send();

var produtos;
var idprod;
var barraSearch = document.getElementsByClassName("pull-right search");

function carregarJSONProduto() {
    produtos = JSON.parse(this.responseText);

    $(function () {
        $('#tabelitaProduto').bootstrapTable({
            data: produtos
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

 $('#tabelitaProduto').on('click', 'tr' , function () {
//    console.log(this.childNodes[1].textContent);
    idprod = this.childNodes[1].textContent;
    var inputEditar = document.getElementById("inputHiddenEditar");
    inputEditar.setAttribute("value", idprod);
    var inputRemover = document.getElementById("inputHiddenRemover");
    inputRemover.setAttribute("value", idprod);
});