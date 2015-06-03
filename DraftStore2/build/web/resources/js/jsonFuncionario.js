// Requisitando o JSON do Servlet
var xhr = new XMLHttpRequest();
xhr.open("GET", "JsonFuncionariosServlet", true);
xhr.setRequestHeader("Content-type", "application/json");
xhr.addEventListener("load", carregarJSONFornecedores);
xhr.send();

var funcionarios;
var idFuncionario;
var barraSearch = document.getElementsByClassName("pull-right search");

function carregarJSONFornecedores() {
    funcionarios = JSON.parse(this.responseText);
//    console.log("JSON Object:"+funcionarios);
//    console.log("JSON String:"+JSON.stringify(funcionarios));

    $(function () {
        $('#tabelita').bootstrapTable({
            data: funcionarios
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

 $('#tabelita').on('click', 'tr' , function (event) {
//    console.log(this.childNodes[1].textContent);
    idFuncionario = this.childNodes[1].textContent;
    var inputEditar = document.getElementById("inputHiddenEditar");
    inputEditar.setAttribute("value", idFuncionario);
    var inputRemover = document.getElementById("inputHiddenRemover");
    inputRemover.setAttribute("value", idFuncionario);
});