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
    var objDate = new Date($("#inputDataEventoIni").val());
    objDate.getDate() + 1;
    var valor = $("#inputPrecoPromo").val();
    if (objDate.isValid()) { // verifica se a data de inicio está preenchida, se estiver entra na condição.
        if (valor <= 0) {
            campoPromo.classList.add("has-error");
            return false;
        } else {
            campoPromo.classList.remove("has-error");
            campoPromo.classList.add("has-success");
            return true;
        }
    }
    campoDataPromoIni.classList.remove("has-error");
    campoDataPromoIni.classList.remove("has-success");
    return true;
}

function validaDataPromoInicio() {
    var stringData = document.getElementById("inputDataEventoIni").value;
    var objDate = new Date(stringData);
    var data = objDate.getDate() + 1;
    objDate.setDate(data) + 1;
    var today = new Date();
    console.log(objDate.isValid());
    if (objDate.isValid()) {
        if (objDate < today) {
            campoDataPromoIni.classList.add("has-error");
            return false;
        }
        else {
            campoDataPromoIni.classList.remove("has-error");
            campoDataPromoIni.classList.add("has-success");
            return true;
        }
    }
    campoDataPromoIni.classList.remove("has-error");
    campoDataPromoIni.classList.remove("has-success");
    return true;

}

function validaDataPromoFim() {
    var stringData = document.getElementById("inputDataEventoIni").value;
    var objDate = new Date(stringData);
    var data = objDate.getDate() + 1;
    objDate.setDate(data) + 1;
    var dataFim = new Date($("#inputDataEventoFim").val());
    data = dataFim.getDate() + 1;
    dataFim.setDate(data);
    dataFim.getDate() + 1;
    if (objDate.isValid()) {
        if (dataFim < objDate || !dataFim.isValid()) {
            campoDataPromoFim.classList.add("has-error");
            return false;
        }
        else {
            campoDataPromoFim.classList.remove("has-error");
            campoDataPromoFim.classList.add("has-success");
            return true;
        }
    }
    campoDataPromoFim.classList.remove("has-error");
    campoDataPromoFim.classList.remove("has-success");
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
    if (!regex.test(valorDoCampo)) {
        campoImagem.classList.add("has-error");
        return false;
    }
    if (!tamanhoImagem(valorDoCampo)) {
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
        if (tecla === 8 || tecla === 0)
            return true;
        else
            return false;
    }
}



function validaDatas() {
    var objDate = new Date($("#inputDataEventoIni").val());
    var dataFim = new Date($("#inputDataEventoFim").val());

    if (dataFim.getDate() + 1 >= objDate.getDate() + 1) {
        return true;
    } else {
        return false;
    }
}


function tamanhoImagem(url) {
    var myImage = new Image();
    myImage.src = url;
    var largura = myImage.width;
    var altura = myImage.height;
    console.log(largura + "*" + altura);
    if (largura < 500 || altura < 500) {
        alert("Largura e Altura deve ultrapassar dos 500px");
        return false;
    }
    return true;
}

Date.prototype.isValid = function () {
    // An invalid date object returns NaN for getTime() and NaN is the only
    // object not strictly equal to itself.
    return this.getTime() === this.getTime();
};
