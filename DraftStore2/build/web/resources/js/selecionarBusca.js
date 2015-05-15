/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    $("tbody tr").click(function () {
        $(this).closest("tr").siblings().removeClass("highlighted");
        $(this).toggleClass("highlighted");
        $("#inputHiddenEditar").val($(this).find(".hidden").html());
        $("#inputHiddenRemover").val($(this).find(".hidden").html());
    });
});
