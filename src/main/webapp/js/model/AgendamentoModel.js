sgt.model.AgendamentoModel = function(obj) {
 	var self = this;

	self.contaOrigem = obj ? obj.contaOrigem : '';
    self.contaDestino = obj ? obj.contaDestino : '';
    self.valorTransferencia = obj ? obj.valorTransferencia : 0;
    self.taxa = obj ? obj.taxa : 0;
    self.dataAgendamento = obj ? new Date(obj.dataAgendamento) : new Date();
    self.tipoAgendamento = obj ? obj.tipoAgendamento : '';

    self.dataAgendamentoComputar =  ko.computed({
        read: function () {
            return self.dataAgendamento;
        },
        write: function (value) {
            self.dataAgendamento = new Date(value + " 00:00 ");
        }
    });

    self.contaOrigemComputar =  ko.computed({
        read: function () {
            return self.contaOrigem;
        },
        write: function (value) {
            self.contaOrigem = value;
        }
    });

    self.contaDestinoComputar =  ko.computed({
        read: function () {
            return self.contaDestino;
        },
        write: function (value) {
            self.contaDestino = value;
        }
    });
    
};

sgt.model.AgendamentoModel.constructor = sgt.model.AgendamentoModel;

