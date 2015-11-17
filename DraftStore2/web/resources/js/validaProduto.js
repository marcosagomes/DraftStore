// validacoes
var campoSubCategoria = document.getElementById("campoSubTipoProduto");
var campoCategoria = document.getElementById("campoTipoProduto");
var campoTipoFonecedor = document.getElementById("campoFornecedor");
var campoMarca = document.getElementById("campoMarca");
var campoModelo = document.getElementById("campoModelo");
var campoLucro = document.getElementById("campoLucro");
var campoCusto = document.getElementById("campoCusto");
var campoPromo = document.getElementById("campoPrecoPromo");
var campoDataPromoIni = document.getElementById("campoDataIni");
var campoDataPromoFim = document.getElementById("campoDataFim");
var campoImagem = document.getElementById("campoCaminhoImagem");
var campoQuantidade = document.getElementById("campoQuantidade");
var campoDescImg = document.getElementById("campoDescImagem");
var campoDescProd = document.getElementById("campoDescricaoProduto");


function validaCategoria() {
    var subCategoria = document.getElementById("selectSubCategoria");
    if (subCategoria.options[subCategoria.selectedIndex].value === "") {
        campoCategoria.classList.add("has-error");
        return false;
    } else {
        campoCategoria.classList.remove("has-error");
        campoCategoria.classList.add("has-success");
        return true;
    }
}
function validaSubCategoria() {
    var subCategoria = document.getElementById("selectSubCategoria");
    if (subCategoria.options[subCategoria.selectedIndex].value === "") {
        campoSubCategoria.classList.add("has-error");
        return false;
    } else {
        campoSubCategoria.classList.remove("has-error");
        campoSubCategoria.classList.add("has-success");
        return true;
    }
}
function validaFornecedor() {
    var fornecedor = document.getElementById("selectFornecedor");
    if (fornecedor.options[fornecedor.selectedIndex].value === "") {
        campoTipoFonecedor.classList.add("has-error");
        return false;
    } else {
        campoTipoFonecedor.classList.remove("has-error");
        campoTipoFonecedor.classList.add("has-success");
        return true;
    }
}
function validaMarca() {
    var nome = $("#inputMarca").val();
    if (nome === "" && nome.length < 3) {
        campoMarca.classList.add("has-error");
        return false;
    } else {
        campoMarca.classList.remove("has-error");
        campoMarca.classList.add("has-success");
        return true;
    }
}
function validaModelo() {
    var valorModelo = $("#inputModelo").val();
    if (valorModelo === "" && valorModelo.length < 3) {
        campoModelo.classList.add("has-error");
        return false;
    } else {
        campoModelo.classList.remove("has-error");
        campoModelo.classList.add("has-success");
        return true;
    }
}
function validaPreco() {
    var valor = $("#inputCusto").val();
    if (valor <= 0 || valor === "") {
        campoCusto.classList.add("has-error");
        return false;
    } else {
        campoCusto.classList.remove("has-error");
        campoCusto.classList.add("has-success");
        return true;
    }
}

function validaLucro() {
    var valor = $("#inputLucro").val();
    if (valor <= 0 || valor === "") {
        campoLucro.classList.add("has-error");
        return false;
    } else {
        campoLucro.classList.remove("has-error");
        campoLucro.classList.add("has-success");
        return true;
    }
}
function validaPromocao() {
    var valorDoCampo = $("#inputDataEventoIni").val();
    var objDate = new Date();
    var valor = $("#inputPrecoPromo").val();
    if (objDate.setDate(valorDoCampo.split("/")[2]) > 0) { // verifica se a data de inicio está preenchida, se estiver entra na condição.
        if (valor <= 0 || valor === "") {
            campoPromo.classList.add("has-error");
            return false;
        } else {
            campoPromo.classList.remove("has-error");
            campoPromo.classList.add("has-success");
            return true;
        }
    }
    return true;
}

function validaDataPromoInicio() {
    var valorDoCampo = $("#inputDataEventoIni").val();
    var objDate = new Date();
    if (objDate.setDate(valorDoCampo.split("/")[2]) > 0) {
        if (!dataValida(valorDoCampo) || !dataToday(valorDoCampo)) {
            campoDataPromoIni.classList.add("has-error");
            return false;
        }
        else {
            campoDataPromoIni.classList.remove("has-error");
            campoDataPromoIni.classList.add("has-success");
            return true;
        }
    }
    if (objDate.setDate(valorDoCampo.split("/")[2]) <= 0) {
        campoDataPromoIni.classList.remove("has-error");
        return true;
    }
    return true;
}

function validaDataPromoFim() {
    var valorDoCampoIni = $("#inputDataEventoIni").val();
    var valorDoCampoFim = $("#inputDataEventoFim").val();
    var objDate = new Date();
    if (objDate.setDate(valorDoCampoIni.split("/")[2]) >= 1) {
        if (!dataValida(valorDoCampoFim) || dataFimInicio(valorDoCampoIni, valorDoCampoFim)) {
            campoDataPromoFim.classList.add("has-error");
            return false;
        }
        else {
            campoDataPromoFim.classList.remove("has-error");
            campoDataPromoFim.classList.add("has-success");
            return true;
        }
    }
    return true;
}
function validaQuantidade() {
    var quant = $("#numberQuantidade").val();
    if (quant <= 0) {
        campoQuantidade.classList.add("has-error");
        return false;
    } else {
        campoQuantidade.classList.remove("has-error");
        campoQuantidade.classList.add("has-success");
        return true;
    }
}
function validaCaminhoImg() {
    var regex = /\b(https?|ftp|file):\/\/[-A-Z0-9+&@#\/%?=~_|!:,.;]*[-A-Z0-9+&@#\/%=~_|]/i;
    var valorDoCampo = $("#imageCaminho").val();
    if (!regex.test(valorDoCampo) || !tamanhoImagem() ) {
        campoImagem.classList.add("has-error");
        return false;
    } else {
        campoImagem.classList.remove("has-error");
        campoImagem.classList.add("has-success");
        return true;
    }
}
function validaDescricaoImg() {
    var descImg = $("#descImagem").val();
    if (descImg === "") {
        campoDescImg.classList.add("has-error");
        return false;
    } else {
        campoDescImg.classList.remove("has-error");
        campoDescImg.classList.add("has-success");
        return true;
    }

}
function validaDescricaoProd() {
    var descProd = $("#textareaDescricao").val();
    if (descProd.length < 20 || descProd === "") {
        campoDescProd.classList.add("has-error");
        return false;
    } else {
        campoDescProd.classList.remove("has-error");
        campoDescProd.classList.add("has-success");
        return true;
    }

}

function valida(form) {

    if (!validaCategoria()) {
        form.selectCategoria.focus();
        return false;
    }
    if (!validaSubCategoria()) {
        form.subTipo.focus();
        return false;
    }
    if (!validaFornecedor()) {
        form.Fornecedor.focus();
        return false;
    }
    if (!validaMarca()) {
        form.Marca.focus();
        return false;
    }
    if (!validaModelo()) {
        form.Modelo.focus();
        return false;
    }
    if (!validaPreco()) {
        form.Custo.focus();
        return false;
    }
    if (!validaLucro()) {
        form.lucro.focus();
        return false;
    }
    if (!validaPromocao()) {
        form.precoPromo.focus();
        return false;
    }
    if (!validaDataPromoInicio()) {
        form.dataEventoIni.focus();
        return false;
    }
    if (!validaDataPromoFim()) {
        form.dataEventoFim.focus();
        return false;
    }
    if (!validaQuantidade()) {
        form.quantidade.focus();
        return false;
    }
    if (!validaCaminhoImg()) {
        form.imagem.focus();
        return false;
    }
    if (!validaDescricaoImg()) {
        form.descImagem.focus();
        return false;
    }
    if (!validaDescricaoProd()) {
        form.descricao.focus();
        return false;
    }

    if (!confirm('Tem certeza que deseja efetuar o registro com essas informações?')) {
        return false;
    } else {
        alert('Operação realizada com sucesso!');
    }

}
$("#selectCategoria").blur(validaCategoria);
$("#selectSubCategoria").blur(validaSubCategoria);
$("#selectFornecedor").blur(validaFornecedor);
$("#inputMarca").blur(validaMarca);
$("#inputModelo").blur(validaModelo);
$("#inputCusto").blur(validaPreco);
$("#inputLucro").blur(validaLucro);
$("#inputPrecoPromo").blur(validaPromocao);
$("#inputDataEventoIni").blur(validaDataPromoInicio);
$("#inputDataEventoFim").blur(validaDataPromoFim);
$("#numberQuantidade").blur(validaQuantidade);
$("#imageCaminho").blur(validaCaminhoImg);
$("#descImagem").blur(validaDescricaoImg);
$("#textareaDescricao").blur(validaDescricaoProd);

function SomenteNumero(e) {
    var tecla = (window.event) ? event.keyCode : e.which;
    if ((tecla > 47 && tecla < 58))
        return true;
    else {
        if (tecla == 8 || tecla == 0)
            return true;
        else
            return false;
    }
}


function dataValida(dts) {
    var date = dts;
    var ardt = new Array;
    var ExpReg = new RegExp("(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/[12][0-9]{3}");
    ardt = date.split("/");
    if (date.search(ExpReg) == -1) {
        return false;
    }
    else if (((ardt[1] == 4) || (ardt[1] == 6) || (ardt[1] == 9) || (ardt[1] == 11)) && (ardt[0] > 30))
        return false
    else if (ardt[1] == 2) {
        if ((ardt[0] > 28) && ((ardt[2] % 4) != 0))
            return false;
        if ((ardt[0] > 29) && ((ardt[2] % 4) == 0))
            return false
    }

    return true;
}

function dataToday(dts) {
    var data = dts;
    var objDate = new Date();
    objDate.setYear(data.split("/")[2]);
    objDate.setMonth(data.split("/")[1] - 1);//- 1 pq em js é de 0 a 11 os meses
    objDate.setDate(data.split("/")[0]);

    if (objDate.getTime() < new Date().getTime()) {
        return false;
    } else if (objDate.getTime() >= new Date().getTime()) {
        return true;
    }
}
function dataFimInicio(dts, dts2) {
    var data = dts;
    var data2 = dts2;
    var objDate = new Date();
    var objDate2 = new Date();
    objDate.setYear(data.split("/")[2]);
    objDate.setMonth(data.split("/")[1] - 1);//- 1 pq em js é de 0 a 11 os meses
    objDate.setDate(data.split("/")[0]);
    // populando a segunda data

    objDate.setYear(data2.split("/")[2]);
    objDate.setMonth(data2.split("/")[1] - 1);//- 1 pq em js é de 0 a 11 os meses
    objDate.setDate(data2.split("/")[0]);

    if (objDate.getTime() > objDate2.getTime()) {
        return false;
    } else {
        return true;
    }

}
function tamanhoImagem() {
    var url = $("#imageCaminho").val();
    var myImage = new Image();
    myImage.src = url;
    var largura = parseInt(myImage.width);
    var altura = parseInt(myImage.height);

    if (largura < 500 && altura < 500) {
        alert("Largura e Altura deve passar dos 500px")
        return false;
    }
    return true;

}

