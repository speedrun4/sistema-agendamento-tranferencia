var URL = 'http://localhost:8080/sgt-web';

chamarServico = function (servico, parametro, callBackSuccess, callBackError) {
    var jsonParseParametro = JSON.stringify(parametro);

    $.support.cors = true;
    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: URL + servico,
        dataType: 'json',
        data: jsonParseParametro,

        success: function (data, textStatus, jqXHR) {
            callBackSuccess(data,textStatus,jqXHR);
        },

        error: function (jqXHR, textStatus, errorThrown) {
            callBackError(jqXHR, textStatus, errorThrown);
        }
    });
}