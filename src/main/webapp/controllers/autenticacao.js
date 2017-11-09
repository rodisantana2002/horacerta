/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
    $("#footer").load(getPath() + "pages/infra/footer.html");

    $("input").blur(function () {
        if ($(this).val() === "")
        {
            $(this).css({"border": "1px solid #F00", "padding": "2px"});
        }
        else {
            $(this).css({"border": "1px solid #ccc", "padding": "6px 12px"});
        }
    });

    $("#btnAcessar").click(function () {
        var cont = 0;
        var user = JSON.stringify({senha: $("#password").val(),
            pessoa: {
                email: $("#email").val()
            }
        });

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
                message: "<div style='text-align: center'><h4>Processando solicitação...</h4></div> <div style='text-align: center'><img src=img/img-loading.gif></img> </div>",
                animate: true,
                closable: false
            });
            $.ajax
                    ({
                        type: "POST",
                        url: getPath() + "services/autentication/login",
                        dataType: 'json',
                        contentType: 'application/json',
                        beforeSend: function () {
                            dialog.setSize(BootstrapDialog.SIZE_SMALL);
                            dialog.setType(BootstrapDialog.TYPE_INFO);
                            dialog.open();
                        },
                        async: true,
                        data: user,
                        success: function (result) {
                            if (result.pessoa.email !== "") {
                                sessionStorage.setItem("user", JSON.stringify(result));
                                sessionStorage.setItem("pessoa", JSON.stringify(result.pessoa));
                                $("#alert_exception").hide();
                                $("#alert_error").hide();
                                $(window.document.location).attr('href', getPath() + "pages/infra/home.html");
                            }
                        },
                        error: function (jqXHR, textStatus, errorThrown) {
                            if (jqXHR.statusText === "Unauthorized") {
                                $("#alert_sucess").hide();
                                $("#alert_error").hide();
                                $("#alert_exception").html("Usuário ou senha incorretos!");
                                $("#alert_exception").show();
                                dialog.close();
                                $("#email").focus();
                            }
                            else {
                                $("#alert_sucess").hide();
                                $("#alert_exception").hide();
                                $("#alert_error").html("Houve um problema no processamento da solicitação" + " (" + jqXHR.statusText + ")");
                                $("#alert_error").show();
                                dialog.close();
                                $("#email").focus();
                            }
                        }
                    }).done(function (data) {
                dialog.close();
            });
        }
    });
});