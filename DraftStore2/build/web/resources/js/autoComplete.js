/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function () {
    var esportes = [
        "Natação",
        "Futebol",
        "Vôlei",
        "Basquete"
    ];
    $("#esporte").autocomplete({
        source: esportes
    });
});