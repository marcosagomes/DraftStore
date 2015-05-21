// validacoes
var campoTipoProduto = document.getElementById("campoTipoProduto");
var campoTipoFornecedor = document.getElementById("campoFornecedor");
var campoMarca = document.getElementById("campoMarca");
var campoModelo = document.getElementById("campoModelo");
var campoLucro = document.getElementById("campoLucro");
var campoCusto = document.getElementById("campoCusto");


function validaTipoProduto() {
    var comboNome = document.getElementById("selectTipo");
    if (comboNome.options[comboNome.selectedIndex].value === "") {
        campoTipoProduto.classList.add("has-error");
        return false;
    } else {
        campoTipoProduto.classList.remove("has-error");
        campoTipoProduto.classList.add("has-success");
        return true;
    }
}
function validaFornecedor() {
    var comboNome = document.getElementById("selectFornecedor");
    if (comboNome.options[comboNome.selectedIndex].value === "") {
        campoTipoFornecedor.classList.add("has-error");
        return false;
    } else {
        campoTipoFornecedor.classList.remove("has-error");
        campoTipoFornecedor.classList.add("has-success");
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


function valida(form) {

   if(!validaTipoProduto()){
       form.Tipo.focus();
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
    }if (!validaPreco()) {
        form.Custo.focus();
        return false;
    }
    if (!validaLucro()) {
        form.lucro.focus();
        return false;
    }

    if (!confirm('Tem certeza que deseja efetuar o registro com essas informações?')) {
        return false;
    } else {
        alert('Operação realizada com sucesso!');
    }

}
$("#selectTipo").blur(validaTipoProduto);
$("#selectFornecedor").blur(validaFornecedor);
$("#inputMarca").blur(validaMarca);
$("#inputModelo").blur(validaModelo);
$("#inputCusto").blur(validaPreco);
$("#inputLucro").blur(validaLucro);

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