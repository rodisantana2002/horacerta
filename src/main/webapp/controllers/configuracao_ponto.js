$(document).ready(function () {
    $("#header").load(getPath() + "pages/infra/header.html");
    $("#footer").load(getPath() + "pages/infra/footer.html");
    load(getUser());

    $("#btnVoltar").click(function () {
        $(window.document.location).attr('href', getPath() + "pages/configuracoes/bodyConfiguracoes.html");
    });

    $("input").blur(function () {
        if ($(this).val() === "" || $(this).val().length > 5)
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
            if ($(this).val() === "")
            {
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

            var configuracao = JSON.stringify({
                id: $("#id").val(),
                horaEntrada: converterHora($("#horarioEntrada").val()),
                horaSaida: converterHora($("#horarioSaida").val()),
                cargaHorariaDia: converterHora($("#totalDia").val()),
                intervalo: converterHora($("#horarioAlmoco").val()),
                toleranciaDia: converterHora($("#toleranciaDiaria").val()),
                pessoa: getPessoa()
            });
            $.ajax
                    ({
                        type: "POST",
                        url: getPath() + "services/configuracao",
                        dataType: 'html',
                        contentType: 'application/json',
                        beforeSend: function () {
                            dialog.setSize(BootstrapDialog.SIZE_SMALL);
                            dialog.setType(BootstrapDialog.TYPE_INFO);
                            dialog.open();
                        },
                        async: true,
                        headers: {"Authorization": "token xxxx, email " + $("#email").val()},
                        data: configuracao,
                        success: function (result) {
                            if (result === "<p>A operação foi realizada com sucesso.</p>") {
                                $("#alert_exception").hide();
                                $("#alert_error").hide();
                                $("#alert_sucess").html(result);
                                $("#alert_sucess").show();
                                dialog.close();
                                $("#horarioEntrada").focus();
                            }
                            else {
                                $("#alert_sucess").hide();
                                $("#alert_error").hide();
                                $("#alert_exception").html(result);
                                $("#alert_exception").show();
                                dialog.close();
                                $("#horarioEntrada").focus();
                            }
                        },
                        error: function (jqXHR, textStatus, errorThrown) {
                            $("#alert_error").html("Houve um problema no processamento da solicitação" + " (" + jqXHR.statusText + ")");
                            $("#alert_sucess").hide();
                            $("#alert_exception").hide();
                            $("#alert_error").show();
                            dialog.close();
                            $("#horarioEntrada").focus();
                        }
                    }).done(function (data) {
                dialog.close();
            });
        }
    });
});

function atualizarControles(user) {
    if (user !== null) {
        var config = getConfiguracoesPonto();
        if (config !== null) {
            $("#id").val(config.id);
            $("#horarioEntrada").val(config.horaEntrada);            
            $("#horarioSaida").val(config.horaSaida);
            $("#totalDia").val(config.cargaHorariaDia);
            $("#horarioAlmoco").val(config.intervalo);
            $("#toleranciaDiaria").val(config.toleranciaDia);
        }
        else{
            $("#id").val("null");
        }
    }
}

function atualizarDados() {
    var config = JSON.stringify({
        id: $("#id").val(),
        horaEntrada: $("#horarioEntrada").val(),
        horaSaida: $("#horarioSaida").val(),
        cargaHorariaDia: $("#totalDia").val(),
        intervalo: $("#horarioAlmoco").val(),
        toleranciaDia: $("#toleranciaDiaria").val(),
        pessoa: getPessoa()
    });
    return config;
}


function load(user) {
    if (user === null) {
        $(window.document.location).attr('href', getPath());
    }
    else {
        atualizarControles(user);
    }
}


