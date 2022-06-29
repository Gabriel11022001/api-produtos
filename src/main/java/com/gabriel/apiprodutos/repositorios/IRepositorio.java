package com.gabriel.apiprodutos.repositorios;

import java.util.List;

public interface IRepositorio<T, E> {

	T salvar(E obj) throws Exception;
	T atualizar(E obj) throws Exception;
	List<T> buscarTodos() throws Exception;
	T buscarPeloId(int id) throws Exception;
	void remover(int id) throws Exception;
}
