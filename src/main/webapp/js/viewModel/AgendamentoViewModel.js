sgt.viewModel.AgendamentoViewModel = function () {
    var self = this;

    self.agendamentoModel = ko.observable(new sgt.model.AgendamentoModel());
    self.listaAgendamento = ko.observableArray();
    self.agendamentoService = new sgt.service.AgendamentoService();
    self.exibirAdicionarAgendamento = ko.observable(false);
    self.exibirMsgCamposObrigatorios = ko.observable(false);
    self.exibirConfirmacao = ko.observable(false);
    self.msgConfirmacao = ko.observable('');

    self.processarMsgConfirmacaoAlerta = function () {
        self.msgConfirmacao("A taxa calculada para o agendamento de " + self.agendamentoModel().taxa + " deseja prosseguir ?");
    }

    self.validarCamposObrigatorios = function () {
        var valido = false;

        valido = 
            self.agendamentoModel().contaOrigem && 
            self.agendamentoModel().contaDestino && 
            self.agendamentoModel().valorTransferencia && 
            self.agendamentoModel().dataAgendamento && 
            self.agendamentoModel().tipoAgendamento;

        return valido;            
    }

    self.habilitarAdicionarAgendamento = function () {
        self.agendamentoModel(new sgt.model.AgendamentoModel());
        self.exibirAdicionarAgendamento(true);
        self.exibirMsgCamposObrigatorios(false);
    }

    self.desabilitarAdicionarAgendamento = function () {
        self.exibirAdicionarAgendamento(false);
    }

    self.desabilitarConfirmacao = function () {
        self.exibirConfirmacao(false);
    }

    self.adicionarAgendamento = function () {
        self.agendamentoService.calcularTaxa( self.agendamentoModel(), function (resultado) {
            if (self.validarCamposObrigatorios()) {
                self.agendamentoModel().taxa = resultado;
                self.processarMsgConfirmacaoAlerta();
                self.exibirConfirmacao(true);
                self.exibirMsgCamposObrigatorios(false);
            } else {
                self.exibirMsgCamposObrigatorios(true);
            }
        });
    }

    self.listarAgendamentos = function () {
        self.agendamentoService.listarAgendamentos( function (resultado) {
            if (resultado) {
                self.listaAgendamento(resultado);
            }
        });        
    } 

    self.confirmarAgendamento = function () {
        self.agendamentoService.salvarAgendamento( self.agendamentoModel(), function (resultado) {
            if (resultado) {
                self.listarAgendamentos();
                self.desabilitarAdicionarAgendamento();
                self.desabilitarConfirmacao();
            } 
        });
    }

    <!-- CONSTRUTOR -->
        self.listarAgendamentos();
    <!-- CONSTRUTOR -->

};

$( document ).ready(function() {
    window.__agendamentoViewModel = new sgt.viewModel.AgendamentoViewModel();
    ko.applyBindings(__agendamentoViewModel);
});