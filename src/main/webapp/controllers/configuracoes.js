/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
    $("#lnkVerMenosPeriodosFrequencia").hide();
    $("#header").load(getPath() + "pages/infra/header.html");
    $("#footer").load(getPath() + "pages/infra/footer.html");
    load(getUser());
});

$("#lnkAlterarSenha").click(function () {
    $(window.document.location).attr('href', getPath() + "pages/colaborador/detalhe_colaborador_altera_senha.html");
});

$("#lnkEditarDadosPessoais").click(function () {
    $(window.document.location).attr('href', getPath() + "pages/colaborador/detalhe_colaborador.html");
});

$("#lnkEditarConfigPonto").click(function (){
   $(window.document.location).attr('href', getPath() + "pages/colaborador/detalhe_configuracao_ponto.html"); 
});

$("#lnkAdicionarPeriodoFrequencia").click(function () {
    $(window.document.location).attr('href', getPath() + "pages/configuracoes/detalhe_frequencia.html");
});

$("#lnkVerMaisPeriodosFrequencia").click(function () {
    var result = sessionStorage.getItem("lstFrequencia");
    result = JSON.parse(result);

    $("#lstPeriodosFrequencia tbody").html("");
    for (var i in result) {
        var frequencia = result[i];
        $("#lstPeriodosFrequencia tbody").append("<tr>");
        $("#lstPeriodosFrequencia tbody").append("<td style='text-align: center'>" + frequencia.id + "</td>");
        $("#lstPeriodosFrequencia tbody").append("<td style='text-align: center'>" + frequencia.dataInicio + "</td>");
        $("#lstPeriodosFrequencia tbody").append("<td style='text-align: center'>" + frequencia.dataTermino + "</td>");
        $("#lstPeriodosFrequencia tbody").append("<td style='text-align: center'>" + frequencia.descricao + "</td>");
        $("#lstPeriodosFrequencia tbody").append("<td style='text-align: center'><button name='Excluir' alt=" + frequencia.id + " style='font-size: small; text-align: left' class='btn-link'> <span class='glyphicon glyphicon-trash'></span></button></td>");
        $("#lstPeriodosFrequencia tbody").append("/<tr>");
    }
    $("#lnkVerMenosPeriodosFrequencia").show();
    $("#lnkVerMaisPeriodosFrequencia").hide();
});

$("#lnkVerMenosPeriodosFrequencia").click(function () {
    var result = sessionStorage.getItem("lstFrequencia");
    result = JSON.parse(result);

    $("#lstPeriodosFrequencia tbody").html("");
    for (var i in result) {
        if (i <= 2) {
            var frequencia = result[i];
            $("#lstPeriodosFrequencia tbody").append("<tr>");
            $("#lstPeriodosFrequencia tbody").append("<td style='text-align: center'>" + frequencia.id + "</td>");
            $("#lstPeriodosFrequencia tbody").append("<td style='text-align: center'>" + frequencia.dataInicio + "</td>");
            $("#lstPeriodosFrequencia tbody").append("<td style='text-align: center'>" + frequencia.dataTermino + "</td>");
            $("#lstPeriodosFrequencia tbody").append("<td style='text-align: center'>" + frequencia.descricao + "</td>");
            $("#lstPeriodosFrequencia tbody").append("<td style='text-align: center'><button name='Excluir' alt=" + frequencia.id + " style='font-size: small; text-align: left' class='btn-link'> <span class='glyphicon glyphicon-trash'></span></button></td>");
            $("#lstPeriodosFrequencia tbody").append("/<tr>");
        }
    }
    $("#lnkVerMenosPeriodosFrequencia").hide();
    $("#lnkVerMaisPeriodosFrequencia").show();
});

$("#lstPeriodosFrequencia").on("click", ".btn-link", function () {
    var indice_selecionado = parseInt($(this).attr("alt"));

    var dialog = BootstrapDialog.show({
        title: 'Confirma Operação?',
        message: 'Confima a exclusão do Periodo de Frequência?',
        size: BootstrapDialog.SIZE_SMALL,
        type: BootstrapDialog.TYPE_WARNING,
        buttons: [{
                label: 'OK',
                cssClass: 'btn-warning',
                action: function (dialogItself) {
                    excluirFrequencia(indice_selecionado);
                    dialogItself.close();
                }
            }, {
                label: 'Cancelar',
                action: function (dialogItself) {
                    dialogItself.close();
                }
            }]
    });
});

function load(user) {
    if (user === null) {
        $(window.document.location).attr('href', getPath());
    } else {
        $("#identificador").append(getPessoa().id);
        $("#matricula").append(getPessoa().matricula);
        $("#nome").append(getPessoa().primeiroNome + " " + getPessoa().segundoNome);
        $("#email").append(getPessoa().email);
        popularConfiguracoes(getPessoa());
        popularFrequencia(getPessoa());
    }
}

function popularConfiguracoes(pessoa) {
    var dialog = BootstrapDialog.show({
        title: "Aguarde!",
        autospin: true,
        message: "<div style='text-align: center'><h4>Processando solicitação...</h4></div> <div style='text-align: center'><img src=../../img/img-loading.gif></img> </div>",
        animate: true,
        closable: false
    });

    $.ajax
            ({
                type: "GET",
                url: getPath() + "services/configuracao/colaborador/" + pessoa.id,
                dataType: 'json',
                contentType: 'application/json',
                beforeSend: function () {
                    dialog.setSize(BootstrapDialog.SIZE_SMALL);
                    dialog.getModalHeader().hide();
                    dialog.open();
                },
                async: true,
                success: function (result) {
                    $("#alert_error").hide();
                    $("#horarioEntrada").append(result.horaEntrada);
                    $("#horarioSaida").append(result.horaSaida);
                    $("#totalHorarioAlmoco").append(result.intervalo);
                    $("#toleranciaDia").append(result.toleranciaDia);                                  
                    $("#totalDia").append(result.cargaHorariaDia);                    
                    sessionStorage.setItem("CongiguracoesPonto", JSON.stringify(result)); 
                },
                error: function (jqXHR) {
                    if (jqXHR.statusText === "Unauthorized") {
                        $("#alert_error").hide();
                        dialog.close();
                    }
                    if (jqXHR.statusText === "Not Found") {
                        $("#alert_error").hide();
                        $("#horarioEntrada").append("00:00");
                        $("#horarioSaida").append("00:00");
                        $("#totalHorarioAlmoco").append("00:00");
                        $("#toleranciaDia").append("00:00");
                        $("#totalDia").append("00:00");                        
                        dialog.close();
                    }
                    else {
                        dialog.close();
                        $("#alert_error").html("Houve um problema no processamento da solicitação" + " (" + jqXHR.statusText + ")");
                        $("#alert_error").show();
                    }
                }
            }).done(function (data) {
        dialog.close();
    });
}

function popularFrequencia(pessoa) {
    $.ajax
            ({
                type: "GET",
                url: getPath() + "services/frequencia/colaborador/" + pessoa.id,
                dataType: 'json',
                contentType: 'application/json',
                beforeSend: function () {
                },
                async: true,
                success: function (result) {
                    $("#alert_error").hide();
                    if (result.length === 0) {
                        $("#alert_error").hide();
                        $("#lstPeriodosFrequencia thead").hide();
                        $("#lnkVerMaisPeriodosFrequencia").hide();
                        $("#lstPeriodosFrequencia").html("<div style='text-align: center'>Nenhuma Frequência encontrada!</div>");
                    }
                    else {                       
                        $("#lstPeriodosFrequencia tbody").html("");
                        for (var i in result) {
                            if (i <= 2) {
                                var frequencia = result[i];
                                $("#lstPeriodosFrequencia tbody").append("<tr>");
                                $("#lstPeriodosFrequencia tbody").append("<td style='text-align: center'>" + frequencia.id + "</td>");
                                $("#lstPeriodosFrequencia tbody").append("<td style='text-align: center'>" + frequencia.dataInicio + "</td>");
                                $("#lstPeriodosFrequencia tbody").append("<td style='text-align: center'>" + frequencia.dataTermino + "</td>");
                                $("#lstPeriodosFrequencia tbody").append("<td style='text-align: center'>" + frequencia.descricao + "</td>");
                                $("#lstPeriodosFrequencia tbody").append("<td style='text-align: center'><button name='Excluir' alt=" + frequencia.id + " style='font-size: small; text-align: left' class='btn-link'> <span class='glyphicon glyphicon-trash'></span></button></td>");
                                $("#lstPeriodosFrequencia tbody").append("/<tr>");                                               
                            }
                        }
                        if (result.length > 3) {
                            $("#lnkVerMaisPeriodosFrequencia").show();
                            $("#lnkVerMenosPeriodosFrequencia").hide();                            
                        }
                        else {
                            $("#lnkVerMaisPeriodosFrequencia").hide();
                            $("#lnkVerMenosPeriodosFrequencia").hide();                            
                        }
                    }
                    sessionStorage.setItem("lstFrequencia", JSON.stringify(result));                                             
                },
                error: function (jqXHR) {
                    if (jqXHR.statusText === "Unauthorized") {
                        $("#alert_error").hide();
                    }
                    else {
                        $("#alert_error").html("Houve um problema no processamento da solicitação" + " (" + jqXHR.statusText + ")");
                        $("#alert_error").show();
                    }
                }
            }).done(function (data) {
    });
}

function excluirFrequencia(id) {
    var dialog = BootstrapDialog.show({
        title: "Aguarde!",
        autospin: true,
        message: "<div style='text-align: center'><h4>Processando solicitação...</h4></div> <div style='text-align: center'><img src=../../img/img-loading.gif></img> </div>",
        animate: true,
        closable: false
    });
    $.ajax
            ({
                type: "DELETE",
                url: getPath() + "services/frequencia/" + id,
                dataType: 'html',
                contentType: 'application/json',
                beforeSend: function () {
                    dialog.getModalHeader().hide;                    
                    dialog.setSize(BootstrapDialog.SIZE_SMALL);
                    dialog.open();
                },
                async: true,
                success: function (result) {  
                    if(result==="<p>A operação foi realizada com sucesso.</p>"){
                        BootstrapDialog.show({
                            title: "Sucesso!",
                            message: result,
                            animate: true
                        });                                                                
                        popularFrequencia(getPessoa());
                        $("#alert_error").hide();                        
                    }
                    else{
                        BootstrapDialog.show({
                            title: "Atenção!",
                            message: result,
                            animate: true,
                            type: BootstrapDialog.TYPE_WARNING,                            
                        });                                                                                        
                        popularFrequencia(getPessoa());
                        $("#alert_error").hide();                                                
                    }
                },
                error: function (jqXHR) {
                    if (jqXHR.statusText === "Unauthorized") {
                        $("#alert_error").hide();
                        dialog.close();                        
                    }
                    if (jqXHR.statusText === "Not Found") {
                        $("#alert_error").hide();
                        dialog.close();                        
                    }
                    else {
                        dialog.close();
                        $("#alert_error").html("Houve um problema no processamento da solicitação" + " (" + jqXHR.statusText + ")");
                        $("#alert_error").show();                        
                    }
                }
            }).done(function (data) {
                dialog.close();                
    });
}