package br.com.fmyfabio.sgt.integracao.rest;


import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import br.com.fmyfabio.sgt.exception.NegocioException;
import br.com.fmyfabio.sgt.model.AgendamentoModel;
import br.com.fmyfabio.sgt.service.AgendamentoService;

/**
 * Classe central de integracao dos servicos rest.
 * @author fabio.santos
 */
@Component
@Path("/rest/json") 
public class IntegracacaoRest extends SpringBeanAutowiringSupport {

		@Context
		private HttpServletResponse	response;

		@Autowired
		private AgendamentoService agendamentoService;
		
		private void allowControlOrigin() {
			this.response.addHeader("Access-Control-Allow-Origin", "*");
		}			
		
		@OPTIONS
		@Path("/{path:.*}")
		public
				Response
				headerRequest(
						@HeaderParam("Access-Control-Request-Method") final String requestMethod,
						@HeaderParam("Access-Control-Request-Headers") final String requestHeaders) {
			final ResponseBuilder retValue = Response.ok();
			if (requestHeaders != null) {
				retValue.header("Access-Control-Allow-Headers", requestHeaders);
			}
			if (requestMethod != null) {
				retValue.header("Access-Control-Allow-Methods", requestMethod);
			}
			retValue.header("Access-Control-Allow-Origin", "*");
			
			return retValue.build();
		}

		@POST
		@Path("/calcularTaxa")
		@Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
		@Consumes(MediaType.APPLICATION_JSON)
		public Double calcularTaxa(AgendamentoModel agendamentoModel) {
			return this.agendamentoService.calcularTaxaAgendamento(agendamentoModel);
		}		

		@POST
		@Path("/salvarAgendamento")
		@Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
		@Consumes(MediaType.APPLICATION_JSON)
		public void salvarAgendamento(AgendamentoModel agendamentoModel) throws NegocioException {
			this.agendamentoService.salvarAgendamento(agendamentoModel);
		}		

		@POST
		@Path("/listarAgendamentos")
		@Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
		@Consumes(MediaType.APPLICATION_JSON)
		public Collection<AgendamentoModel> listarAgendamentos() throws NegocioException {
			return this.agendamentoService.listarAgendamentos();
		}				
		
}
