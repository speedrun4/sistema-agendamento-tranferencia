package br.com.fmyfabio.sgt.model;

import java.util.Date;

import br.com.fmyfabio.sgt.enums.TipoAgendamento;

public class AgendamentoModel extends BaseModel {

	private static final long serialVersionUID = 817952460879306195L;
	
	private String contaOrigem;
	private String contaDestino;
	private Double valorTransferencia;
	private Double taxa;
	private Date dataAgendamento;
	private TipoAgendamento tipoAgendamento;
	
	// Get & Set
	public String getContaDestino() {
		return contaDestino;
	}
	public void setContaDestino(String contaDestino) {
		this.contaDestino = contaDestino;
	}
	public Double getValorTransferencia() {
		return valorTransferencia;
	}
	public void setValorTransferencia(Double valorTransferencia) {
		this.valorTransferencia = valorTransferencia;
	}
	public Double getTaxa() {
		return taxa;
	}
	public void setTaxa(Double taxa) {
		this.taxa = taxa;
	}
	public Date getDataAgendamento() {
		return dataAgendamento;
	}
	public void setDataAgendamento(Date dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}
	public TipoAgendamento getTipoAgendamento() {
		return tipoAgendamento;
	}
	public void setTipoAgendamento(TipoAgendamento tipoAgendamento) {
		this.tipoAgendamento = tipoAgendamento;
	}
	public String getContaOrigem() {
		return contaOrigem;
	}
	public void setContaOrigem(String contaOrigem) {
		this.contaOrigem = contaOrigem;
	}	
	
	// Equals & HashCode
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((contaDestino == null) ? 0 : contaDestino.hashCode());
		result = prime * result
				+ ((contaOrigem == null) ? 0 : contaOrigem.hashCode());
		result = prime * result
				+ ((dataAgendamento == null) ? 0 : dataAgendamento.hashCode());
		result = prime * result + ((taxa == null) ? 0 : taxa.hashCode());
		result = prime * result
				+ ((tipoAgendamento == null) ? 0 : tipoAgendamento.hashCode());
		result = prime
				* result
				+ ((valorTransferencia == null) ? 0 : valorTransferencia
						.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AgendamentoModel other = (AgendamentoModel) obj;
		if (contaDestino == null) {
			if (other.contaDestino != null)
				return false;
		} else if (!contaDestino.equals(other.contaDestino))
			return false;
		if (contaOrigem == null) {
			if (other.contaOrigem != null)
				return false;
		} else if (!contaOrigem.equals(other.contaOrigem))
			return false;
		if (dataAgendamento == null) {
			if (other.dataAgendamento != null)
				return false;
		} else if (!dataAgendamento.equals(other.dataAgendamento))
			return false;
		if (taxa == null) {
			if (other.taxa != null)
				return false;
		} else if (!taxa.equals(other.taxa))
			return false;
		if (tipoAgendamento != other.tipoAgendamento)
			return false;
		if (valorTransferencia == null) {
			if (other.valorTransferencia != null)
				return false;
		} else if (!valorTransferencia.equals(other.valorTransferencia))
			return false;
		return true;
	}
	
}
