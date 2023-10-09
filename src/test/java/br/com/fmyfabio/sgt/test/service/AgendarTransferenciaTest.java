package br.com.fmyfabio.sgt.test.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.fmyfabio.sgt.enums.TipoAgendamento;
import br.com.fmyfabio.sgt.exception.NegocioException;
import br.com.fmyfabio.sgt.model.AgendamentoModel;
import br.com.fmyfabio.sgt.service.AgendamentoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext-test.xml" })
public class AgendarTransferenciaTest {

	@Autowired
	private AgendamentoService agendamentoService;
	
	@Test
	public void taxaAgendamentoTipoATest() throws NegocioException {
		AgendamentoModel agendamentoModel = this.criarAgendamento(new Date(), TipoAgendamento.A, 100.00);		
		agendamentoModel.setTaxa( this.agendamentoService.calcularTaxaAgendamento(agendamentoModel) );		
		this.agendamentoService.validarTaxaAgendamento(agendamentoModel);
		
		Assert.assertEquals(new Double(2 + (agendamentoModel.getValorTransferencia() * 0.03)), agendamentoModel.getTaxa());
	}

	@Test
	public void taxaAgendamentoTipoBTest() throws NegocioException {
		Date dataAte30Dias = this.adicionarDia( new Date() , 15);
		Date dataDemais = this.adicionarDia( new Date() , 35);
		
		AgendamentoModel agendamentoAte30Dias = this.criarAgendamento(dataAte30Dias, TipoAgendamento.B, 100.00);
		AgendamentoModel agendamentoDemais = this.criarAgendamento(dataDemais, TipoAgendamento.B, 100.00);
		
		agendamentoAte30Dias.setTaxa( this.agendamentoService.calcularTaxaAgendamento(agendamentoAte30Dias) );
		agendamentoDemais.setTaxa( this.agendamentoService.calcularTaxaAgendamento(agendamentoDemais) );
		
		this.agendamentoService.validarTaxaAgendamento(agendamentoAte30Dias);
		this.agendamentoService.validarTaxaAgendamento(agendamentoDemais);
		
		Assert.assertEquals(new Double(agendamentoAte30Dias.getValorTransferencia() * 0.1), agendamentoAte30Dias.getTaxa());
		Assert.assertEquals(new Double(agendamentoDemais.getValorTransferencia() * 0.08), agendamentoDemais.getTaxa());
	}	
	
	@Test
	public void taxaAgendamentoTipoCTest() throws NegocioException {
		AgendamentoModel agendamentoMaior30Dias = this.criarAgendamento( this.adicionarDia( new Date() , 35) , TipoAgendamento.C, 100.00);
		AgendamentoModel agendamentoAte30Dias = this.criarAgendamento( this.adicionarDia( new Date() , 30) , TipoAgendamento.C, 100.00);
		AgendamentoModel agendamentoAte25Dias = this.criarAgendamento( this.adicionarDia( new Date() , 22) , TipoAgendamento.C, 100.00);
		AgendamentoModel agendamentoAte20Dias = this.criarAgendamento( this.adicionarDia( new Date() , 19) , TipoAgendamento.C, 100.00);
		AgendamentoModel agendamentoAte15Dias = this.criarAgendamento( this.adicionarDia( new Date() , 13) , TipoAgendamento.C, 100.00);
		AgendamentoModel agendamentoAte10Dias = this.criarAgendamento( this.adicionarDia( new Date() , 8) , TipoAgendamento.C, 100.00);
		AgendamentoModel agendamentoAte5Dias = this.criarAgendamento( this.adicionarDia( new Date() , 3) , TipoAgendamento.C, 100.00);		
		
		List<AgendamentoModel> listaAgendamentos = new ArrayList<AgendamentoModel>();
		listaAgendamentos.add( agendamentoMaior30Dias );
		listaAgendamentos.add( agendamentoAte30Dias );
		listaAgendamentos.add( agendamentoAte25Dias );
		listaAgendamentos.add( agendamentoAte20Dias );
		listaAgendamentos.add( agendamentoAte15Dias );
		listaAgendamentos.add( agendamentoAte10Dias );
		listaAgendamentos.add( agendamentoAte5Dias );

		for (AgendamentoModel agendamentoModel : listaAgendamentos) {
			agendamentoModel.setTaxa( this.agendamentoService.calcularTaxaAgendamento(agendamentoModel) );
			this.agendamentoService.validarTaxaAgendamento(agendamentoModel);
		}		
		
		Assert.assertEquals(new Double(agendamentoMaior30Dias.getValorTransferencia() * 0.012), agendamentoMaior30Dias.getTaxa());
		Assert.assertEquals(new Double(agendamentoAte30Dias.getValorTransferencia() * 0.021), agendamentoAte30Dias.getTaxa());
		Assert.assertEquals(new Double(agendamentoAte25Dias.getValorTransferencia() * 0.043), agendamentoAte25Dias.getTaxa());
		Assert.assertEquals(new Double(agendamentoAte20Dias.getValorTransferencia() * 0.054), agendamentoAte20Dias.getTaxa());
		Assert.assertEquals(new Double(agendamentoAte15Dias.getValorTransferencia() * 0.067), agendamentoAte15Dias.getTaxa());
		Assert.assertEquals(new Double(agendamentoAte10Dias.getValorTransferencia() * 0.074), agendamentoAte10Dias.getTaxa());
		Assert.assertEquals(new Double(agendamentoAte5Dias.getValorTransferencia() * 0.083), agendamentoAte5Dias.getTaxa());	
	}
	
	@Test
	public void taxaAgendamentoTipoDTest() throws NegocioException {
		// ATE 25000
		AgendamentoModel agendamentoAte25000 = this.criarAgendamento( this.adicionarDia( new Date() , 0) , TipoAgendamento.D, 20000.00);
		// ATE 120000
		AgendamentoModel agendamentoAte10000030Dias = this.criarAgendamento(this.adicionarDia( new Date() , 15), TipoAgendamento.D, 100000.00);
		AgendamentoModel agendamentoAte100000Demais = this.criarAgendamento(this.adicionarDia( new Date() , 35), TipoAgendamento.D, 100000.00);
		// MAIOR 120000		
		AgendamentoModel agendamentoMaior120000Maior30Dias = this.criarAgendamento( this.adicionarDia( new Date() , 35) , TipoAgendamento.D, 150000.00);
		AgendamentoModel agendamentoMaior120000Ate30Dias = this.criarAgendamento( this.adicionarDia( new Date() , 30) , TipoAgendamento.D, 150000.00);		
		AgendamentoModel agendamentoMaior120000Ate25Dias = this.criarAgendamento( this.adicionarDia( new Date() , 22) , TipoAgendamento.D, 150000.00);
		AgendamentoModel agendamentoMaior120000Ate20Dias = this.criarAgendamento( this.adicionarDia( new Date() , 19) , TipoAgendamento.D, 150000.00);
		AgendamentoModel agendamentoMaior120000Ate15Dias = this.criarAgendamento( this.adicionarDia( new Date() , 13) , TipoAgendamento.D, 150000.00);
		AgendamentoModel agendamentoMaior120000Ate10Dias = this.criarAgendamento( this.adicionarDia( new Date() , 8) , TipoAgendamento.D, 150000.00);
		AgendamentoModel agendamentoMaior120000Ate5Dias = this.criarAgendamento( this.adicionarDia( new Date() , 3) , TipoAgendamento.D, 150000.00);		
		
		List<AgendamentoModel> listaAgendamentos = new ArrayList<AgendamentoModel>();
		listaAgendamentos.add( agendamentoAte25000 );
		listaAgendamentos.add( agendamentoAte10000030Dias );
		listaAgendamentos.add( agendamentoAte100000Demais );
		listaAgendamentos.add( agendamentoMaior120000Maior30Dias );
		listaAgendamentos.add( agendamentoMaior120000Ate30Dias );
		listaAgendamentos.add( agendamentoMaior120000Ate25Dias );
		listaAgendamentos.add( agendamentoMaior120000Ate20Dias );
		listaAgendamentos.add( agendamentoMaior120000Ate15Dias );
		listaAgendamentos.add( agendamentoMaior120000Ate10Dias );
		listaAgendamentos.add( agendamentoMaior120000Ate5Dias );

		for (AgendamentoModel agendamentoModel : listaAgendamentos) {
			agendamentoModel.setTaxa( this.agendamentoService.calcularTaxaAgendamento(agendamentoModel) );
			this.agendamentoService.validarTaxaAgendamento(agendamentoModel);
		}		
		
		Assert.assertEquals(new Double(2 + (agendamentoAte25000.getValorTransferencia() * 0.03)), agendamentoAte25000.getTaxa());
		Assert.assertEquals(new Double(agendamentoAte10000030Dias.getValorTransferencia() * 0.1), agendamentoAte10000030Dias.getTaxa());
		Assert.assertEquals(new Double(agendamentoAte100000Demais.getValorTransferencia() * 0.08), agendamentoAte100000Demais.getTaxa());
		Assert.assertEquals(new Double(agendamentoMaior120000Maior30Dias.getValorTransferencia() * 0.012), agendamentoMaior120000Maior30Dias.getTaxa());
		Assert.assertEquals(new Double(agendamentoMaior120000Ate30Dias.getValorTransferencia() * 0.021), agendamentoMaior120000Ate30Dias.getTaxa());
		Assert.assertEquals(new Double(agendamentoMaior120000Ate25Dias.getValorTransferencia() * 0.043), agendamentoMaior120000Ate25Dias.getTaxa());
		Assert.assertEquals(new Double(agendamentoMaior120000Ate20Dias.getValorTransferencia() * 0.054), agendamentoMaior120000Ate20Dias.getTaxa());		
		Assert.assertEquals(new Double(agendamentoMaior120000Ate15Dias.getValorTransferencia() * 0.067), agendamentoMaior120000Ate15Dias.getTaxa());
		Assert.assertEquals(new Double(agendamentoMaior120000Ate10Dias.getValorTransferencia() * 0.074), agendamentoMaior120000Ate10Dias.getTaxa());
		Assert.assertEquals(new Double(agendamentoMaior120000Ate5Dias.getValorTransferencia() * 0.083), agendamentoMaior120000Ate5Dias.getTaxa());
	}	
	
	private Date adicionarDia(Date date, int qtdDias) {
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(date);
		calendario.add(Calendar.DAY_OF_MONTH, qtdDias);
		return calendario.getTime();
	}

	private AgendamentoModel criarAgendamento(Date dataAgendamento, TipoAgendamento tipoAgendamento, Double valorTransferencia) {
		AgendamentoModel agendamentoModel = new AgendamentoModel();
		agendamentoModel.setContaDestino("000000");
		agendamentoModel.setValorTransferencia(valorTransferencia);
		agendamentoModel.setDataAgendamento(dataAgendamento);
		agendamentoModel.setTipoAgendamento(tipoAgendamento);		
		return agendamentoModel;
	}	
	
}
