sgt.service.AgendamentoService = function () {
    var self = this;

    self.salvarAgendamento = function(agendamentoModel, callBack) {

        chamarServico('/rest/json/salvarAgendamento', agendamentoModel, 
            function (data, textStatus, jqXHR) {
                callBack(true);
            },
            function (jqXHR, textStatus, errorThrown) {
                throw new sgt.exception.SistemaException('Erro:\n' + jqXHR.responseText + '\t ' + errorThrown);
            }
        );

    }

    self.listarAgendamentos = function (callBack) {

        chamarServico('/rest/json/listarAgendamentos', null, 
            function (data, textStatus, jqXHR) {

                if (data) {

                    var arrayResultado = new Array();
                    var controlador = new Array();

                    $.each(data, function(i, obj) {
                        controlador.push($.Deferred());
                    });     

                    $.each(data, function(i, obj) {
                        arrayResultado.push( new sgt.model.AgendamentoModel(obj) );
                        controlador.pop().resolve();
                    });     

                    $.when.apply(null, controlador).done(function(){
                        callBack(arrayResultado);
                    });                    

                }
           
            },
            function (jqXHR, textStatus, errorThrown) {
                throw new sgt.exception.SistemaException('Erro:\n' + jqXHR.responseText + '\t ' + errorThrown);
            }
        );

    }

    self.calcularTaxa = function (agendamentoModel, callBack) {

        chamarServico('/rest/json/calcularTaxa', agendamentoModel, 
            function (data, textStatus, jqXHR) {
                callBack(data);
            },
            function (jqXHR, textStatus, errorThrown) {
                throw new sgt.exception.SistemaException('Erro:\n' + jqXHR.responseText + '\t ' + errorThrown);
            }
        );

    }

};

sgt.service.AgendamentoService.constructor = sgt.service.AgendamentoService;