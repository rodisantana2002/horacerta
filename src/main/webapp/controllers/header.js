/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
});

$("#btnSair").click(function () {
    var user = sessionStorage.getItem("user");
    user = JSON.parse(user);    
    
    var dialog = BootstrapDialog.show({
         title:"Aguarde!",
         autospin: true,
         message: "<div style='text-align: center'><h4>Processando solicitação...</h4></div> <div style='text-align: center'><img src=../../img/img-loading.gif></img> </div>",
         animate:true,
         closable:false        
     });
     
    $.ajax({
        type: "POST",
        url: getPath() + "services/autentication/logout",
        contentType: 'application/json',
        beforeSend: function () {
             dialog.setSize(BootstrapDialog.SIZE_SMALL);      
             dialog.setType(BootstrapDialog.TYPE_INFO);
             dialog.open();                                                         
        },        
        async: true,
        data: JSON.stringify(user),        
        success: function (result) {
            sessionStorage.setItem("user", null);
            sessionStorage.setItem("pessoa", null);
            $(window.document.location).attr('href', getPath());
            dialog.close();
            $("#email").focus();                                                              
        },
        error: function (jqXHR, textStatus, errorThrown) {
            dialog.close();            
            $("#email").focus();   
        }                           
    });
});            

$("#btnDadosPessoais").click(function (){
    editarDadosPessoais();
}); 

$("#lnkDadosPessoais").click(function (){
    editarDadosPessoais();
});

$("#btnHome").click(function (){
    $(window.document.location).attr('href', getPath() + "/pages/infra/home.html");        
});

function editarDadosPessoais(){   
    if(getUser()===null){
        $(window.document.location).attr('href', getPath());        
    }
    else{
        $(window.document.location).attr('href', getPath() + "pages/configuracoes/bodyConfiguracoes.html");        
    }           
}