/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
    $("#header").load(getPath() + "pages/infra/header.html");
    $("#footer").load(getPath() + "pages/infra/footer.html");
    load(getUser());
    
    $("#btnNova").click(function () {
        $("#alert_exception").hide();
        $("#alert_error").hide();
        $("#alert_sucess").hide();
        
        $("#dtInicio").val("");
        $("#dtTermino").val("");
        $("#descricao").val("");
        $("#dtInicio").focus();
    });

    $("#btnVoltar").click(function () {
        $(window.document.location).attr('href', getPath() + "pages/configuracoes/bodyConfiguracoes.html");
    });

    $("input").blur(function () {
        if ($(this).val() === "")
        {
            $(this).css({"border": "1px solid #F00", "padding": "2px"});
        }
        else {
            $(this).css({"border": "1px solid #ccc", "padding": "6px 12px"});
        }
    });

    $("#btnSalvar").click(function () {
        var cont = 0;

        $(".form-control").each(function () {
            if ($(this).val() === "") {
                $(this).css({"border": "1px solid #F00", "padding": "2px"});
                $(this).focus();
                cont++;
            }
            else {
                $(this).css({"border": "1px solid #ccc", "padding": "6px 12px"});
            }
        });
        if (cont === 0) {
            var dialog = BootstrapDialog.show({
                title: "Aguarde!",
                autospin: true,
                message: "<div style='text-align: center'><h4>Processando solicitação...</h4></div> <div style='text-align: center'><img src=../../img/img-loading.gif></img> </div>",
                animate: true,
                closable: false
            });
            var frequencia = JSON.stringify({
                id: "null",
                dataInicio: converterData($("#dtInicio").val()),
                dataTermino: converterData($("#dtTermino").val()),
                descricao: $("#descricao").val(),
                pessoa: getPessoa()
            });
            $.ajax
                    ({
                        type: "PUT",
                        url: getPath() + "services/frequencia/add",
                        dataType: 'html',
                        contentType: 'application/json',
                        beforeSend: function () {
                            dialog.setSize(BootstrapDialog.SIZE_SMALL);
                            dialog.setType(BootstrapDialog.TYPE_INFO);
                            dialog.open();
                        },
                        async: true,
                        data: frequencia,
                        success: function (result) {
                            if (result === "<p>A operação foi realizada com sucesso.</p>") {
                                $("#alert_exception").hide();
                                $("#alert_error").hide();
                                $("#alert_sucess").html(result);
                                $("#alert_sucess").show();
                                dialog.close();
                            }
                            else {
                                $("#alert_sucess").hide();
                                $("#alert_error").hide();
                                $("#alert_exception").html(result);
                                $("#alert_exception").show();
                                dialog.close();
                            }
                        },
                        error: function (jqXHR, textStatus, errorThrown) {
                            $("#alert_error").html("Houve um problema no processamento da solicitação" + " (" + jqXHR.statusText + ")");
                            $("#alert_sucess").hide();
                            $("#alert_exception").hide();
                            $("#alert_error").show();
                            dialog.close();
                        }
                    }).done(function (data) {
                dialog.close();
            });
        }
    });
});

function load(user) {
    if (user === null) {
        $(window.document.location).attr('href', getPath());
    }
}
