package br.com.fmyfabio.sgt.service.impl;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fmyfabio.sgt.enums.TipoAgendamento;
import br.com.fmyfabio.sgt.exception.NegocioException;
import br.com.fmyfabio.sgt.model.AgendamentoModel;
import br.com.fmyfabio.sgt.repository.core.RepositorySingleton;
import br.com.fmyfabio.sgt.service.AgendamentoService;

@Service
public class AgendamentoServiceImpl implements AgendamentoService {

	@Autowired
	private RepositorySingleton repositorySingleton;
	
	public void salvarAgendamento(AgendamentoModel agendamentoModel) throws NegocioException {
		this.validarAgendamento(agendamentoModel);
		this.repositorySingleton.salvarNoRepositorio(agendamentoModel);
	}

	public Collection<AgendamentoModel> listarAgendamentos() {
		return this.repositorySingleton.obterListaDoRepositorio(AgendamentoModel.class);
	}	
	
	public Double calcularTaxaAgendamento(AgendamentoModel agendamentoModel) {
		Double taxa = null;
		
		if (TipoAgendamento.A.equals(agendamentoModel.getTipoAgendamento())) {
			taxa = calcularTaxacaoTipoA(agendamentoModel);
		} else if (TipoAgendamento.B.equals(agendamentoModel.getTipoAgendamento())) {
			taxa = calcularTaxacaoTipoB(agendamentoModel);
		} else if (TipoAgendamento.C.equals(agendamentoModel.getTipoAgendamento())) {
			taxa = calcularTaxacaoTipoC(agendamentoModel);
		} else if (TipoAgendamento.D.equals(agendamentoModel.getTipoAgendamento())) {
			taxa = calcularTaxacaoTipoD(agendamentoModel);
		}
		
		return taxa;
	}
	
	public void validarTaxaAgendamento(AgendamentoModel agendamentoModel) throws NegocioException {
		Double taxaReal = this.calcularTaxaAgendamento(agendamentoModel);
		
		if (taxaReal.doubleValue() != agendamentoModel.getTaxa()) {
			throw new NegocioException("Valor da taxa esta incorreto.");
		}
	}
	
	private void validarAgendamento(AgendamentoModel agendamentoModel) throws NegocioException {
		this.validarTaxaAgendamento(agendamentoModel);
	}
	
	private Double calcularDiferencaDeDias(Date dataMenor, Date dataMaior) {
		return new Double((dataMaior.getTime() - dataMenor.getTime()) / 86400000L);
	}
	
	private Double calcularTaxacaoTipoA(AgendamentoModel agendamentoModel) {
		return (2 + (agendamentoModel.getValorTransferencia() * 0.03)) ;
	}

	private Double calcularTaxacaoTipoB(AgendamentoModel agendamentoModel) {
		Double diferencaDeDias = this.calcularDiferencaDeDias(Calendar.getInstance().getTime(), agendamentoModel.getDataAgendamento());
		Double taxa = null;
		
		if (diferencaDeDias <= 30) {
			taxa = (agendamentoModel.getValorTransferencia() * 0.1);
		} else {
			taxa = (agendamentoModel.getValorTransferencia() * 0.08);
		}
		
		return taxa;
	}

	private Double calcularTaxacaoTipoC(AgendamentoModel agendamentoModel) {
		Double diferencaDeDias = this.calcularDiferencaDeDias(Calendar.getInstance().getTime(), agendamentoModel.getDataAgendamento());
		Double taxa = null;
		
		if (diferencaDeDias > 30) {
			
			taxa = (agendamentoModel.getValorTransferencia() * 0.012);
			
		} else if ((diferencaDeDias > 25) && (diferencaDeDias <= 30)) {
			
			taxa = (agendamentoModel.getValorTransferencia() * 0.021);
			
		} else if ((diferencaDeDias > 20) && (diferencaDeDias <= 25)) {
			
			taxa = (agendamentoModel.getValorTransferencia() * 0.043);
			
		} else if ((diferencaDeDias > 15) && (diferencaDeDias <= 20)) {
			
			taxa = (agendamentoModel.getValorTransferencia() * 0.054);
			
		} else if ((diferencaDeDias > 10) && (diferencaDeDias <= 15)) {
			
			taxa = (agendamentoModel.getValorTransferencia() * 0.067);
			
		} else if ((diferencaDeDias > 5) && (diferencaDeDias <= 10)) {
			
			taxa = (agendamentoModel.getValorTransferencia() * 0.074);
			
		} else if ((diferencaDeDias > 0) && (diferencaDeDias <= 5)) {
			
			taxa = (agendamentoModel.getValorTransferencia() * 0.083);
			
		}
		
		return taxa;
	}	
	
	private Double calcularTaxacaoTipoD(AgendamentoModel agendamentoModel) {
		Double taxa = null;
		Double valorTransferencia = agendamentoModel.getValorTransferencia();
		
		if (valorTransferencia.doubleValue() <= 25000.00) {
			
			taxa = this.calcularTaxacaoTipoA(agendamentoModel);
			
		} else if ((valorTransferencia.doubleValue() > 25000.00) && (valorTransferencia.doubleValue() <= 120000.00)) {
			
			taxa = this.calcularTaxacaoTipoB(agendamentoModel);
			
		} else if (valorTransferencia.doubleValue() > 120000.00) {
			
			taxa = this.calcularTaxacaoTipoC(agendamentoModel);
			
		}
		
		return taxa;
	}	
	
}

