/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
        } else {
            $(this).css({"border": "1px solid #ccc", "padding": "6px 12px"});
        }
    });

    $("#btnSalvar").click(function () {
        var cont = 0;
        var user = JSON.stringify({
            id: getUser().id,
            senha: $("#senhaAtual").val(),
            token: getUser.token,
            pessoa: getPessoa()
        });

        $(".form-control").each(function () {
            if ($(this).val() === "") {
                $(this).css({"border": "1px solid #F00", "padding": "2px"});
                $(this).focus();
                cont++;
            } else {
                if ($("#novaSenha").val() !== $("#confirmaSenha").val()) {
                    $("#novaSenha").css({"border": "1px solid #F00", "padding": "2px"});
                    $("#confirmaSenha").css({"border": "1px solid #F00", "padding": "2px"});
                    cont++
                } else {
                    $("#novaSenha").css({"border": "1px solid #ccc", "padding": "6px 12px"});
                    $("#confirmaSenha").css({"border": "1px solid #ccc", "padding": "6px 12px"});
                }
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
                        url: getPath() + "services/usuario/validar",
                        dataType: 'json',
                        contentType: 'application/json',
                        headers: {"token": getUser().token, "email":getPessoa().email},
                        data: user,
                        beforeSend: function () {
                            dialog.setSize(BootstrapDialog.SIZE_SMALL);
                            dialog.setType(BootstrapDialog.TYPE_INFO);
                            dialog.open();
                        },
                        async: true,
                        success: function (result) {
                            if (result.senha!== "erro") {
                                atualizarSenha(user);
                            }
                            else {
                                $("#alert_sucess").hide();
                                $("#alert_error").hide();
                                $("#alert_exception").html("A senha atual informada esta incorreta!");
                                $("#alert_exception").show();
                                dialog.close();
                                $("#senhaAtual").focus();
                            }
                        },
                        error: function (jqXHR, textStatus, errorThrown) {
                            if (jqXHR.statusText === "Unauthorized") {
                                $("#alert_sucess").hide();
                                $("#alert_error").hide();
                                $("#alert_exception").html("A requisição precissa de autorização para executar a operação solicitada");
                                $("#alert_exception").show();
                                dialog.close();
                                $("#senhaAtual").focus();
                            } else {
                                $("#alert_sucess").hide();
                                $("#alert_exception").hide();
                                $("#alert_error").html("Houve um problema no processamento da solicitação" + " (" + jqXHR.statusText + ")");
                                $("#alert_error").show();
                                dialog.close();
                                $("#senhaAtual").focus();
                            }
                        }
                    }).done(function (data) {
                dialog.close();
            });
        }
    });
});

function atualizarSenha() {
    var dialog = BootstrapDialog.show({
        title: "Aguarde!",
        autospin: true,
        message: "<div style='text-align: center'><h4>Processando solicitação...</h4></div> <div style='text-align: center'><img src=../../img/img-loading.gif></img> </div>",
        animate: true,
        closable: false
    });

    var user = JSON.stringify({
        id: getUser().id,
        senha: $("#confirmaSenha").val(),
        token: getUser.token,
        pessoa: getPessoa()
    });

    $.ajax
            ({
                type: "POST",
                url: getPath() + "services/usuario/alterarsenha",
                dataType: 'html',
                contentType: 'application/json',
                async: true,
                data: user,
                beforeSend: function () {
                    dialog.setSize(BootstrapDialog.SIZE_SMALL);
                    dialog.setType(BootstrapDialog.TYPE_INFO);
                    dialog.open();
                },
                success: function (result) {
                    if (result === "<p>A operação foi realizada com sucesso.</p>") {
                        $("#alert_exception").hide();
                        $("#alert_error").hide();
                        $("#alert_sucess").html(result);
                        $("#alert_sucess").show();
                        dialog.close();
                        limparControles();
                        $("#senhaAtual").focus();
                    } else {
                        $("#alert_sucess").hide();
                        $("#alert_error").hide();
                        $("#alert_exception").html(result);
                        $("#alert_exception").show();
                        dialog.close();
                        limparControles();
                        $("#senhaAtual").focus();
                    }
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    if (jqXHR.statusText === "Unauthorized") {
                        $("#alert_sucess").hide();
                        $("#alert_error").hide();
                        $("#alert_exception").html("A requisição precissa de autorização para executar a operação solicitada");
                        $("#alert_exception").show();
                        dialog.close();
                        limparControles();
                        $("#senhaAtual").focus();
                    } else {
                        $("#alert_sucess").hide();
                        $("#alert_exception").hide();
                        $("#alert_error").html("Houve um problema no processamento da solicitação" + " (" + jqXHR.statusText + ")");
                        $("#alert_error").show();
                        dialog.close();
                        limparControles();
                        $("#senhaAtual").focus();
                    }
                }
            }).done(function (data) {
        dialog.close();
    });
}

function limparControles(){
    $("#senhaAtual").val("");
    $("#novaSenha").val("");
    $("#confirmaSenha").val("");
}

function load(user) {
    if (user === null) {
        $(window.document.location).attr('href', getPath());
    }
}