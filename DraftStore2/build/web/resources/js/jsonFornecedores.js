// Requisitando o JSON do Servlet
var xhr = new XMLHttpRequest();
xhr.open("GET", "TesteJsonFornecedores", true);
xhr.setRequestHeader("Content-type", "application/json");
xhr.addEventListener("load", carregarJSONFornecedores);
xhr.send();

var fornecedores;
var idFornec;
var barraSearch = document.getElementsByClassName("pull-right search");

function carregarJSONFornecedores() {
    fornecedores = JSON.parse(this.responseText);

    $(function () {
        $('#tabelita').bootstrapTable({
            data: fornecedores
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

var data = [
    {"razaoSocial": "Fornecedor X",
        "cnpj": "111.456.789-00"},
    {"razaoSocial": "Fornecedor Y",
        "cnpj": "222.456.789-00"},
    {"razaoSocial": "Fornecedor Z",
        "cnpj": "333.456.789-00"},
    {"razaoSocial": "Fornecedor K",
        "cnpj": "444.456.789-00"}
];

 $('#tabelita').on('click', 'tr' , function (event) {
//    console.log(this.childNodes[1].textContent);
    idFornec = this.childNodes[1].textContent;
    var inputEditar = document.getElementById("inputHiddenEditar");
    inputEditar.setAttribute("value", idFornec);
    var inputRemover = document.getElementById("inputHiddenRemover");
    inputRemover.setAttribute("value", idFornec);
});