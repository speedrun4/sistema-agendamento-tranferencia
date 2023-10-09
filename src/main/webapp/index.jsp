<!DOCTYPE html>
<html lang="pt-br">

	<head>
		<meta charset="utf-8" />

		<title>SGT - Sistema de Agendamento de Transferencia</title>

		<!-- CSS-->
		<link rel="stylesheet" href="css/normalize.css" />		
		<link rel="stylesheet" href="css/padrao-geral.css" />	
		<link rel="stylesheet" href="css/bootstrap.min.css" />	
		<link rel="stylesheet" href="css/bootstrap-theme.min.css" />		

		<!-- IMPORT LIB-->
		<script src="js/lib/knockout-2.2.1.js"></script>
		<script src="js/lib/jquery-1.10.2.min.js"></script>
		<script src="js/lib/moment.js"></script>
		<script src="js/lib/jquery.maskedinput.js"></script>

		<!-- IMPORT COMMON-->
		<script src="js/common/packagePath.js"></script>
		<script src="js/common/integracao.js"></script>
		<script src="js/common/custom-bidding.js"></script>

		<!-- IMPORT Exception-->
		<script src="js/exception/SistemaException.js"></script>		

		<!-- IMPORT MODEL-->
		<script src="js/model/AgendamentoModel.js"></script>

		<!-- IMPORT Service-->
		<script src="js/service/AgendamentoService.js"></script>				

		<!-- IMPORT ViewModel-->
		<script src="js/viewModel/AgendamentoViewModel.js"></script>				
	</head>

	<body>
		<div id="principal">
			<header>
				<h1>Sistema de Agendamento de Transferencia</h1>
			</header>

			<section data-bind="visible: !exibirAdicionarAgendamento()">
				<a href="#" class="btn btn-primary botaoMarginBottomGrande" data-bind="click: habilitarAdicionarAgendamento">Adicionar Agendamento</a>			
			</section>

			<section data-bind="visible: exibirConfirmacao">
				<div class="alert alert-info">
					<span data-bind="text: msgConfirmacao"></span>

					<a href="#" class="btn btn-primary" data-bind="click: confirmarAgendamento">Sim</a>
					<a href="#" class="btn btn-default" data-bind="click: desabilitarConfirmacao">Nao</a>		
				</div>
			</section>

			<section data-bind="visible: exibirAdicionarAgendamento() && !exibirConfirmacao()">
				<fieldset>
					<legend>Adicionar Agendamento</legend>

					<div class="alert alert-danger" data-bind="visible: exibirMsgCamposObrigatorios">
						<span>Preencha todos os campos.</span>
					</div>

					<div class="form-horizontal">
						<div class="form-group">
	    					<label class="col-sm-2 control-label">Conta Origem</label>
	    					<div class="col-sm-10">
								<input id="idContaOrigem" type="text" class="form-control" autofocus placeholder="Conta Origem" data-bind="value: agendamentoModel().contaOrigemComputar, mask: '******-*'" />
							</div>
						</div>	
						<div class="form-group">
							<label class="col-sm-2 control-label">Conta Destino</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" placeholder="Conta Destino" data-bind="value: agendamentoModel().contaDestinoComputar, mask: '******-*'" />
							</div>	
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">Tipo de Agendamento</label>
							<div class="col-sm-10">
								<select class="form-control" data-bind="value: agendamentoModel().tipoAgendamento">
								  <option value="A">A</option>
								  <option value="B">B</option>
								  <option value="C">C</option>
								  <option value="D">D</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">Data do Agendamento</label>
							<div class="col-sm-10">
								<input id="idDataAgendamento" type="date" class="form-control" data-bind="value: agendamentoModel().dataAgendamentoComputar" />
							</div>
						</div>
						<div class="form-group">	
							<label class="col-sm-2 control-label">Valor da Transferencia</label>
							<div class="col-sm-10">
								<input type="number" class="form-control" step="0.01" data-bind="value: agendamentoModel().valorTransferencia" />				
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">							
								<a href="#" class="btn btn-default" data-bind="click: desabilitarAdicionarAgendamento">Cancelar</a>

								<a href="#" class="btn btn-success" data-bind="click: adicionarAgendamento">Prosseguir</a>							
							</div>
						</div>
					</div>
				</fieldset>	
			</section>
			

			<section data-bind="visible: listaAgendamento().length > 0">
				<fieldset>
					<legend>Lista de Agendamentos</legend>

					<table class="table alinharTexto">
						<thead>
							<tr>
								<th>Conta Origem</th> 
			                 	<th>Conta Destino</th>
			                 	<th>Tipo de Transferencia</th>
			                 	<th>Data do Agendamento</th>
			                 	<th>Valor</th>
			                 	<th>Taxa</th>
			                </tr> 
						</thead>

						<tbody data-bind="foreach: listaAgendamento">
							<tr>
								<td><span data-bind="text: contaOrigem"> </span></td>
						   		<td><span data-bind="text: contaDestino"> </span></td>
						   		<td><span data-bind="text: tipoAgendamento"> </span></td>
						   		<td><span data-bind="text: moment(dataAgendamento).format('DD/MM/YYYY')"> </span></td>
						   		<td><span data-bind="text: valorTransferencia"></span></td>
						   		<td><span data-bind="text: taxa"> </span></td>
						   </tr>	
						</tbody>   	
					</table>
				</fieldset>								    				
			</section>

			<footer>
				<h4>Desafio SGT - Fabio Santos</h4>
			</footer>
		</div>
	</body>

</html>




