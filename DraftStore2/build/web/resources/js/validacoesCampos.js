// validacoes
var todosOsCamposValidos = false;

var formulario = document.getElementById("formulario");
var campoRazaoSocial = document.getElementById("campoRazaoSocial");
var campoCep = document.getElementById("campoCep");
var campoCNPJ = document.getElementById("campoCNPJ");
var campoTelefone = document.getElementById("campoTelefone");
var campoEmail = document.getElementById("campoEmail");
var campoSite = document.getElementById("campoSite");
var campoNumero = document.getElementById("campoNumero");

function validaNumero(){
    var valorDoCampo = $("#inputNumero").val();
    if(valorDoCampo == "" || valorDoCampo.length<1){
        campoNumero.classList.add("has-error");
        return false;
    } else {
        campoNumero.classList.remove("has-error");
        return true;
    }
}
function validaRazaoSocial(){
    var valorDoCampo = $("#inputRazaoSocial").val();
    if(valorDoCampo == "" || valorDoCampo.length<3){
        campoRazaoSocial.classList.add("has-error");
        return false;
    } else {
        campoRazaoSocial.classList.remove("has-error");
        return true;
    }
}
function validaCNPJ() {
 var regex = /^\d{2}\.\d{3}\.\d{3}\/\d{4}\-\d{2}$/;  
 var valorDoCampo = $("#inputCNPJ").val();
 if(!regex.test(valorDoCampo) || !isCNPJValid(valorDoCampo)) {
    campoCNPJ.classList.add("has-error");
     return false;
 } else {
    campoCNPJ.classList.remove("has-error");
     return true;
 }
}
function validaCEP() {
 var regex = /^\d{5}-\d{3}$/;  
 var valorDoCampo = $("#inputCep").val();
 if(!regex.test(valorDoCampo)) {
    campoCep.classList.add("has-error");
     return false;
 } else {
    campoCep.classList.remove("has-error");
     return true;
 }
}
function validaTelefone() {
 var regex = /\(\d{2}\) \d{4}-\d{4}/;  
 var valorDoCampo = $("#inputTelefone").val();
 if(!regex.test(valorDoCampo)) {
    campoTelefone.classList.add("has-error");
     return false;
 } else {
    campoTelefone.classList.remove("has-error");
     return true;
 }
}
function validaEmail() {
 var regex = /^.+@.+\..{2,3}$/;  
 var valorDoCampo = $("#inputEmail").val();
 if(!regex.test(valorDoCampo)) {
    campoEmail.classList.add("has-error");
     return false;
 } else {
    campoEmail.classList.remove("has-error");
     return true;
 }
}
function validaSite() {
 var regex = /^(https?):\/\/([a-zA-Z0-9_-]+)(\.[a-zA-Z0-9_-]+)+(\/[a-zA-Z0-9_-]+)*\/?$/gi;  
 var valorDoCampo = $("#inputSite").val();
 if(!regex.test(valorDoCampo)) {
    campoSite.classList.add("has-error");
     return false;
 } else {
    campoSite.classList.remove("has-error");
     return true;
 }
}

function valida(form){
    if(!validaRazaoSocial()){
        form.RazaoSocial.focus();
        return false;
    }
    if(!validaCNPJ()){
        form.Cnpj.focus();
        return false;
    } if(!validaNumero()){
        form.Numero.focus();
        return false;
    } if(!validaTelefone()){
        form.Telefone.focus();
        return false;
    } if(!validaEmail()){
        form.Email.focus();
        return false;
    } if(!validaSite()){
        form.Site.focus();
        return false;
    } if(!validaCEP()){
        form.Cep.focus();
        return false;
    } 
    
    if(!confirm('Tem certeza que deseja realizar o cadastro com essas informações?')){
        return false;
    } else {
        alert('Operação realizada com sucesso!');
    }
}

function buscarCep(){
    var cep_code = $(this).val();
    if( cep_code.length <= 0 ) return;
    $.get("http://apps.widenet.com.br/busca-cep/api/cep.json", { code: cep_code },
     function(result){
        if(result.status!=1 ){
           //alert(result.message || "Houve um erro desconhecido");
            console.log(result.message || "Houve um erro desconhecido");
           return;
        }
        $("input#inputCep").val( result.code );
        $("input#inputUF").val( result.state );
        $("input#inputCidade").val( result.city );
        $("input#inputBairro").val( result.district );
        $("input#inputEndereco").val( result.address );
     });
}
function isCNPJValid(cnpj) {
    cnpj = cnpj.replace(/[^\d]+/g, '');
    if (cnpj == '')
        return false;
    if (cnpj.length != 14)
        return false;
    // Elimina CNPJs invalidos conhecidos
    if (cnpj == "00000000000000" ||
            cnpj == "11111111111111" ||
            cnpj == "22222222222222" ||
            cnpj == "33333333333333" ||
            cnpj == "44444444444444" ||
            cnpj == "55555555555555" ||
            cnpj == "66666666666666" ||
            cnpj == "77777777777777" ||
            cnpj == "88888888888888" ||
            cnpj == "99999999999999")
        return false;

    // Valida DVs
    tamanho = cnpj.length - 2
    numeros = cnpj.substring(0, tamanho);
    digitos = cnpj.substring(tamanho);
    soma = 0;
    pos = tamanho - 7;
    for (i = tamanho; i >= 1; i--) {
        soma += numeros.charAt(tamanho - i) * pos--;
        if (pos < 2)
            pos = 9;
    }
    resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
    if (resultado != digitos.charAt(0))
        return false;

    tamanho = tamanho + 1;
    numeros = cnpj.substring(0, tamanho);
    soma = 0;
    pos = tamanho - 7;
    for (i = tamanho; i >= 1; i--) {
        soma += numeros.charAt(tamanho - i) * pos--;
        if (pos < 2)
            pos = 9;
    }
    resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
    if (resultado != digitos.charAt(1))
        return false;

    return true;
}

$("#inputRazaoSocial").blur(validaRazaoSocial);
$("#inputCNPJ").blur(validaCNPJ);
$("#inputCep").blur(validaCEP);
$("#inputCep").blur(buscarCep);
$("#inputTelefone").blur(validaTelefone);
$("#inputEmail").blur(validaEmail);
$("#inputSite").blur(validaSite);
$("#inputNumero").blur(validaNumero);

$("#inputTelefone").on("blur", function() {
    var last = $(this).val().substr( $(this).val().indexOf("-") + 1 );

    if( last.length == 3 ) {
        var move = $(this).val().substr( $(this).val().indexOf("-") - 1, 1 );
        var lastfour = move + last;
        var first = $(this).val().substr( 0, 9 );

        $(this).val( first + '-' + lastfour );
    }
});

