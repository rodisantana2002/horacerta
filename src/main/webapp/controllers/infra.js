/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function getPath(){
    var strCaminho = "http://horacerta-santana2002.rhcloud.com/";    
   // var strCaminho = "http://localhost:8080/horacerta/";        
    return strCaminho;
}

function getUser(){
    var user = sessionStorage.getItem("user");    
    user = JSON.parse(user);
    return user;
}

function getPessoa(){
    var pessoa = sessionStorage.getItem("pessoa");
    pessoa = JSON.parse(pessoa);
    return pessoa;
}

function getConfiguracoesPonto(){
    var config = sessionStorage.getItem("CongiguracoesPonto");    
    config = JSON.parse(config);
    return config;
}

function converterData(data){
    var strData = new Date(data);
    //dia
    var strDia = strData.getUTCDate();
    if(strDia<=9){
        strDia = "0" + strDia;
    }
    //mes
    var strMes = strData.getUTCMonth();
    if(strMes<=9){
        if ((strMes + 1)>=10){
            strMes = (strMes + 1);                                
        }
        else{
            strMes = "0" + (strMes + 1);                    
        }
    }    
    //ano
    var strAno = strData.getUTCFullYear();
    
    return strDia + "/" + strMes + "/" + strAno; 
}

function converterHora(hora){
    var minHoras=0;
    var minMinutos=0;
        
    minHoras   = hora.substring(0,2);
    minMinutos = hora.substring(3,5);        
    return (parseInt((minHoras*60)) + parseInt((minMinutos).valueOf()));     
}