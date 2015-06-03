// ----------------- CALCULAR PRECO PREVISTO -----------------
var custo = document.getElementById("inputCusto");
var percentLucro = document.getElementById("inputLucro");
var previsto = document.getElementById("inputPreco");

custo.addEventListener("input", atualizarPrevisto);
percentLucro.addEventListener("input", atualizarPrevisto);

function atualizarPrevisto(){
    var valor = parseFloat(custo.value) + parseFloat(custo.value) * parseFloat(percentLucro.value) / 100;
    previsto.value = "R$ "+valor.toFixed(2);
}
// ------------------------------------------------------------