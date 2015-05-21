// validacoes
var campoNome = document.getElementById("campoNome");
var campoData = document.getElementById("campoDtNascimento");
var campoCPF = document.getElementById("campoCPF");
var campoRG = document.getElementById("campoRG");
var campoTelefone = document.getElementById("campoTelefone");
var campoCelular = document.getElementById("campoCelular");
var campoEmail = document.getElementById("campoEmail");
var campoSexo = document.getElementById("campoSexo");
var campoCargo = document.getElementById("campoCargo");
var campoSenha = document.getElementById("campoSenha");
var campoSenhaC = document.getElementById("campoSenhaC");
function validaRazaoSocial() {
    var valorDoCampo = $("#inputRazaoSocial").val();
    if (valorDoCampo == "" || valorDoCampo.length < 3) {
        campoNome.classList.add("has-error");
        return false;
    } else {
        campoNome.classList.remove("has-error");
        campoNome.classList.add("has-success");
        return true;
    }
}
function validaData() {
    var valorDoCampo = $("#inputData").val();
    if (!validData(valorDoCampo)) {
        campoData.classList.add("has-error");
        return false;
    } else {
        campoData.classList.remove("has-error");
        campoData.classList.add("has-success");
        return true;
    }
}
function validaSelect() {  
        var comboNome = document.getElementById("selectSexo");  
        if (comboNome.options[comboNome.selectedIndex].value == "" ){  
               campoSexo.classList.add("has-error");
                return false;
        }else {
            campoSexo.classList.remove("has-error");
            campoSexo.classList.add("has-success");
            return true;
        }  
    }      

function validaCPF() {
    var reg = /^[0-9]{3}.?[0-9]{3}.?[0-9]{3}-?[0-9]{2}/;
    var valorDoCampo = $("#inputCPF").val();
    if (!reg.test(valorDoCampo) || !validarCPF(valorDoCampo)) {
        campoCPF.classList.add("has-error");
        return false;
    } else {
        campoCPF.classList.remove("has-error");
        campoCPF.classList.add("has-success");
        return true;
    }
}
function validaRg() {
    var reg = /^[0-9]{2}.?[0-9]{3}.?[0-9]{3}-{1}/;
    var valorDoCampo = $("#inputRg").val();
    if (!reg.test(valorDoCampo)) {
        campoRG.classList.add("has-error");
        return false;
    } else {
        campoRG.classList.remove("has-error");
        campoRG.classList.add("has-success");
        return true;
    }

}

function validaTelefone() {
    var regex = /\(\d{2}\) \d{4}-\d{4}/;
    var valorDoCampo = $("#inputTelefone").val();
    if (!regex.test(valorDoCampo)) {
        campoTelefone.classList.add("has-error");
        return false;
    } else {
        campoTelefone.classList.remove("has-error");
        campoTelefone.classList.add("has-success");
        return true;
    }
}
function validaCelular() {
    var regex = /\(\d{2}\) \d{4}-\d{5}/;
    var regex2 = /\(\d{2}\) \d{4}-\d{4}/;
    var valorDoCampo = $("#inputCelular").val();
    if (!regex.test(valorDoCampo) || !regex2.test(valorDoCampo)) {
        campoCelular.classList.add("has-error");
        return false;
    } else {
        campoCelular.classList.remove("has-error");
        campoCelular.classList.add("has-success");
        return true;
    }
}
function validaSelectCargo() {
     var comboNome = document.getElementById("selectCargo");  
        if (comboNome.options[comboNome.selectedIndex].value == "" ){  
               campoCargo.classList.add("has-error");
                return false;
        }else {
            campoCargo.classList.remove("has-error");
            campoCargo.classList.add("has-success");
            return true;
        }  
}
function validaEmail() {
    var regex = /^.+@.+\..{2,3}$/;
    var valorDoCampo = $("#inputEmail").val();
    if (!regex.test(valorDoCampo)) {
        campoEmail.classList.add("has-error");
        return false;
    } else {
        campoEmail.classList.remove("has-error");
        campoEmail.classList.add("has-success");
        return true;
    }
}
function validaSenha() {
    var senha1 = $("#inputSenha").val();
    var senha2 = $("#inputSenhaC").val();
    if (!verificaSenha(senha1, senha2)) {
        campoSenha.classList.add("has-error");
        campoSenhaC.classList.add("has-error");
        return false;
    } else {
        campoSenha.classList.remove("has-error");
        campoSenhaC.classList.remove("has-error");
        campoSenha.classList.add("has-success");
        campoSenhaC.classList.add("has-success");
        return true;
    }

}
function validar(form) {
   if(!validaRazaoSocial()){
       form.Nome.focus();
       return false;
    }
    if(!validaData()){
        form.Data.focus();
        return false;
    }if(!validaSelect()){
        form.Sexo.focus();
        return false;
       }if(!validaCPF()){
        form.CPF.focus();
        return false;
    }if(!validaRg()){
        form.RG.focus();
        return false;
    }if(!validaTelefone()){
        form.Telefone.focus();
        return false;
    }if(!validaCelular()){
        form.Celular.focus();
        return false;
    }if(!validaSelectCargo()){
        form.Cargo.focus();
        return false;
    }if(!validaEmail()){
        form.Email.focus();
        return false;
    }if(!validaSenha()){
        if(form.Senha.value == ""){
            form.Senha.focus();
            return false;
        }else if (form.ConfirmeSenha.value === ""){
            form.ConfirmeSenha.focus();
            return false
        }
        
    }
    if (!confirm('Tem certeza que deseja efetuar o registro com essas informações?')) {
        return false;
    } else {
        alert('Operação realizada com sucesso!');
    }
  
}

function validarCPF(cpf) {
    cpf = cpf.replace(/[^\d]+/g, '');
    if (cpf == '')
        return false;
    // Elimina CPFs invalidos conhecidos    
    if (cpf.length != 11 ||
            cpf == "00000000000" ||
            cpf == "11111111111" ||
            cpf == "22222222222" ||
            cpf == "33333333333" ||
            cpf == "44444444444" ||
            cpf == "55555555555" ||
            cpf == "66666666666" ||
            cpf == "77777777777" ||
            cpf == "88888888888" ||
            cpf == "99999999999")
        return false;
    // Valida 1o digito 
    add = 0;
    for (i = 0; i < 9; i ++)
        add += parseInt(cpf.charAt(i)) * (10 - i);
    rev = 11 - (add % 11);
    if (rev == 10 || rev == 11)
        rev = 0;
    if (rev != parseInt(cpf.charAt(9)))
        return false;
    // Valida 2o digito 
    add = 0;
    for (i = 0; i < 10; i ++)
        add += parseInt(cpf.charAt(i)) * (11 - i);
    rev = 11 - (add % 11);
    if (rev == 10 || rev == 11)
        rev = 0;
    if (rev != parseInt(cpf.charAt(10)))
        return false;
    return true;
}

$("#inputRazaoSocial").blur(validaRazaoSocial);
$("#inputData").blur(validaData);
$("#selectSexo").blur(validaSelect);
$("#inputCPF").blur(validaCPF);
$("#inputRg").blur(validaRg);
$("#inputTelefone").blur(validaTelefone);
$("#inputCelular").blur(validaCelular);
$("#selectCargo").blur(validaSelectCargo);
$("#inputEmail").blur(validaEmail);
$("#inputSenha").blur(validaSenha);
$("#inputSenhaC").blur(validaSenha);
$("#inputTelefone").on("blur", function () {
    var last = $(this).val().substr($(this).val().indexOf("-") + 1);

    if (last.length == 3) {
        var move = $(this).val().substr($(this).val().indexOf("-") - 1, 1);
        var lastfour = move + last;
        var first = $(this).val().substr(0, 9);

        $(this).val(first + '-' + lastfour);
    }
});

function validData(dts) {
    var data = dts;
    var dia = data.substring(0, 2);
    var mes = data.substring(3, 5);
    var ano = data.substring(6, 10);
    var novaData = new Date(ano, (mes - 1), dia);

    var mesmoDia = parseInt(dia, 10) == parseInt(novaData.getDate());
    var mesmoMes = parseInt(mes, 10) == parseInt(novaData.getMonth()) + 1;
    var mesmoAno = parseInt(ano) == parseInt(novaData.getFullYear());

    if (!((mesmoDia) && (mesmoMes) && (mesmoAno))) {
        return false
    } else
        return true;
}
function verificaSenha(a, b) {

    if (a === b  && a.length > 6 ) {
        return true;
    }
    return false;
}