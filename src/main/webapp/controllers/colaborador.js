
$(document).ready(function () {
    $("#header").load(getPath() + "pages/infra/header.html");
    $("#footer").load(getPath() + "pages/infra/footer.html");
    load(getUser());

    $("#btnVoltar").click(function () {
        $(window.document.location).attr('href', getPath() + "pages/configuracoes/bodyConfiguracoes.html");
    });
    
    $("input").blur(function () {
        if ($(this).val() === "" || $(this).val().length > 8)
        {
            $(this).css({"border": "1px solid #F00", "padding": "2px"});
        }
        else {
            $(this).css({"border": "1px solid #ccc", "padding": "6px 12px"});
        }
    });
    
    $("#btnSalvar").click(function () {
        var cont = 0;
        var pessoa = JSON.stringify({
                            id: $("#id").val(),
                            matricula: $("#matricula").val(),
                            primeiroNome: $("#primeiroNome").val(),
                            segundoNome: $("#segundoNome").val(),
                            email: $("#email").val()}); 
                            
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
            $.ajax
                    ({
                        type: "POST",
                        url: getPath() + "services/colaborador",
                        dataType: 'html',
                        contentType: 'application/json',
                        beforeSend: function () {
                            dialog.setSize(BootstrapDialog.SIZE_SMALL);
                            dialog.setType(BootstrapDialog.TYPE_INFO);
                            dialog.open();
                        },
                        async: true,
                        headers: {"token": getUser().token, "email":getPessoa().email},
                        data: pessoa,
                        success: function (result) {
                            if (result === "<p>A operação foi realizada com sucesso.</p>") {
                                $("#alert_exception").hide();
                                $("#alert_error").hide();
                                $("#alert_sucess").html(result);
                                $("#alert_sucess").show();
                                //atualizarDados();
                                sessionStorage.setItem("pessoa", pessoa);
                                dialog.close();
                                $("#matricula").focus();
                            }
                            else {
                                $("#alert_sucess").hide();
                                $("#alert_error").hide();
                                $("#alert_exception").html(result);
                                $("#alert_exception").show();
                                dialog.close();
                                $("#matricula").focus();
                            }
                        },
                        error: function (jqXHR, textStatus, errorThrown) {
                            $("#alert_error").html("Houve um problema no processamento da solicitação" + " (" + jqXHR.statusText + ")");
                            $("#alert_sucess").hide();
                            $("#alert_exception").hide();
                            $("#alert_error").show();
                            dialog.close();
                            $("#matricula").focus();
                        }
                    }).done(function (data) {
                dialog.close();
            });
        }
    });
})

function atualizarControles(pessoa) {
    if (pessoa !== null) {
        //edicao
        $("#id").val(pessoa.id);
        $("#matricula").val(pessoa.matricula);
        $("#primeiroNome").val(pessoa.primeiroNome);
        $("#segundoNome").val(pessoa.segundoNome);
        $("#email").val(pessoa.email);
        $("#matricula").focus();
    }
}

function load(user) {
    if (user === null) {
        $(window.document.location).attr('href', getPath());
    }
    else {
        atualizarControles(getPessoa());
    }
}
        