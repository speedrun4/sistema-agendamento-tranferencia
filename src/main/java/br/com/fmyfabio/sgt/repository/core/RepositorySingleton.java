package br.com.fmyfabio.sgt.repository.core;

import java.util.Collection;

import br.com.fmyfabio.sgt.model.BaseModel;

/**
 * Interface singleton para gerenciar o repositorio em memoria.
 * @author fabio.santos
 */
public interface RepositorySingleton {

	public <T extends BaseModel> Collection<T> obterListaDoRepositorio(Class<T> classe);

	public <T extends BaseModel> void salvarNoRepositorio(T baseModel);

}
