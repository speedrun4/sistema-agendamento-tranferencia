package br.com.fmyfabio.sgt.repository.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.fmyfabio.sgt.model.BaseModel;


/**
 * Classe Impl no padrao singleton para gerenciar o repositorio em memoria.
 * @author fabio.santos
 * @param <T>
 */
@Component
@Scope("singleton")
public class RepositorySingletonImpl implements RepositorySingleton {
	
	private Map<Class<? extends BaseModel>, Collection<? extends BaseModel>> mapaRepositorioMemoria = 
	new HashMap<Class<? extends BaseModel>, Collection<? extends BaseModel>>();
	
	@SuppressWarnings("unchecked")
	public <T extends BaseModel> Collection<T> obterListaDoRepositorio(Class<T> classe) {				
		return (Collection<T>) this.getMapaRepositorioMemoria().get(classe);		
	}
	
	@SuppressWarnings("unchecked")
	public <T extends BaseModel> void salvarNoRepositorio(T baseModel) {
		
		Class<T> classe = (Class<T>) baseModel.getClass();
		Collection<T> colecao = this.obterListaDoRepositorio(classe);
		
		if (colecao == null) {
			
			colecao = new ArrayList<T>();
			colecao.add(baseModel);
			this.getMapaRepositorioMemoria().put(classe, colecao);
			
		} else {
			
			colecao.add(baseModel);
			
		}
		
	}

	// Get & Set
	public Map<Class<? extends BaseModel>, Collection<? extends BaseModel>> getMapaRepositorioMemoria() {
		return mapaRepositorioMemoria;
	}

	public void setMapaRepositorioMemoria(
			Map<Class<? extends BaseModel>, Collection<? extends BaseModel>> mapaRepositorioMemoria) {
		this.mapaRepositorioMemoria = mapaRepositorioMemoria;
	}

}