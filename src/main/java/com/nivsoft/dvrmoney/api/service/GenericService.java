package com.nivsoft.dvrmoney.api.service;

import java.util.List;

public interface GenericService<E, T> {
	public E criar(E entity);

	public List<E> listar();

	public E atualizar(T id, E entity);

	public void deletarPorId(T id);

	public E buscarPorId(T id);

}
