/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var strData = new Date();

$(document).ready(function () {
    $("#header").load(getPath() + "pages/infra/header.html");
    $("#footer").load(getPath() + "pages/infra/footer.html");
    initLoad(getUser());                
});

function initLoad(user){
    if(user===null){
        $(window.document.location).attr('href', getPath());        
    }
    else{
        popularUsuario(getPessoa());
        popularLancamentos();
    }
}

function popularUsuario(pessoa){
    $("#lblUsuario").html("<span class='" + "glyphicon glyphicon-user" + "'></span>  Olá. " + pessoa.primeiroNome + ", seja bem vindo(a).");
    $("#lblNomeCompleto").html("<span class='" + "glyphicon glyphicon-chevron-right" + "'></span>  Nome: " + pessoa.primeiroNome + " " + pessoa.segundoNome);
}

function popularLancamentos(){
    var strDataAtual = strData.toLocaleDateString(); 
    $("#lblLancamentoAtual").html("<span class='" + "glyphicon glyphicon-time" + "'></span>  Registros de Hoje: " + strDataAtual);
}