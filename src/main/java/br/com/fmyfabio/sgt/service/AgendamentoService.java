package br.com.fmyfabio.sgt.service;

import java.util.Collection;

import br.com.fmyfabio.sgt.exception.NegocioException;
import br.com.fmyfabio.sgt.model.AgendamentoModel;

public interface AgendamentoService {

	public Double calcularTaxaAgendamento(AgendamentoModel agendamentoModel);

	public void validarTaxaAgendamento(AgendamentoModel agendamentoModel)
			throws NegocioException;

	public void salvarAgendamento(AgendamentoModel agendamentoModel)
			throws NegocioException;

	public Collection<AgendamentoModel> listarAgendamentos();
	
}
