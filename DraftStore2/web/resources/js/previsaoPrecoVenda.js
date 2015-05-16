// ----------------- CALCULAR PRECO PREVISTO -----------------
var custo = document.getElementsByClassName("inputCusto")[0];
var percentLucro = document.getElementsByClassName("inputLucro")[0];
var previsto = document.getElementsByClassName("inputPrevisto")[0];

custo.addEventListener("input", atualizarPrevisto);
percentLucro.addEventListener("input", atualizarPrevisto);

function atualizarPrevisto(){
    var valor = parseFloat(custo.value) + parseFloat(custo.value) * parseFloat(percentLucro.value) / 100;
    previsto.value = "R$ "+valor.toFixed(2);
}
// ------------------------------------------------------------