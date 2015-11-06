/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function removerFornecedor() {
    if (confirm('Deseja realmente remover o fornecedor selecionado ?')) {
        return true;
    }
    return false;
}

function removerProduto() {
    if (confirm('Deseja realmente remover o fornecedor selecionado ?')) {
        return true;
    }
    return false;
}

function getLogout(s, e) {
    if (confirm("Deseja realmente encessar sua sessão ?")) {
        s.removeAttribute('href');
        s.setAttribute('href', e);
    }
    else {
        s.removeAttribute('href');
        s.setAttribute('href', '');
    }
}