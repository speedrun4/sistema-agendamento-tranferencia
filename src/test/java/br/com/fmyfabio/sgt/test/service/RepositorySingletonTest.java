package br.com.fmyfabio.sgt.test.service;

import java.util.Collection;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.fmyfabio.sgt.model.BaseModel;
import br.com.fmyfabio.sgt.repository.core.RepositorySingleton;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext-test.xml" })
public class RepositorySingletonTest {
	
	// Inner Class para o Teste
	static class TesteRepositorySingle extends BaseModel {

		private static final long serialVersionUID = -5947972045903731444L;
		
		public TesteRepositorySingle() {
			
		}
		
		public TesteRepositorySingle(String valor) {
			this.valor = valor;
		}
		
		private String valor;
		
		public String getValor() {
			return this.valor;
		}
		
		public void setValor(String valor) {
			this.valor = valor;
		}
		
	}	
	
	@Autowired
	private RepositorySingleton repositorySingleton;
	
	@BeforeClass
    public static void oneTimeSetUp() {
		defirFixtures();
    }  
	
	@Test
	public void mecanismoRepositorySingletonTest() {	
		Fixture.of(TesteRepositorySingle.class).addTemplate("valido", new Rule(){{
		    add("valor", random("1", "2", "3"));
		}});
		
		TesteRepositorySingle testeRepositorySingle1 = Fixture.from(TesteRepositorySingle.class).gimme("valido");
		TesteRepositorySingle testeRepositorySingle2 = Fixture.from(TesteRepositorySingle.class).gimme("valido");
		
		this.repositorySingleton.salvarNoRepositorio(testeRepositorySingle1);
		this.repositorySingleton.salvarNoRepositorio(testeRepositorySingle2);
		
		Collection<TesteRepositorySingle> colecao = this.repositorySingleton.obterListaDoRepositorio(TesteRepositorySingle.class);
		
		Assert.assertNotNull(colecao);
		Assert.assertTrue(colecao.size() == 2);
	}

	private static void defirFixtures() {
		Fixture.of(TesteRepositorySingle.class).addTemplate("valido", new Rule(){{
		    add("valor", random("1", "2", "3"));
		}});
	}
	
}
