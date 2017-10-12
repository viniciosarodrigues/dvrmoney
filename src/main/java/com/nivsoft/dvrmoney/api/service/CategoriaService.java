package com.nivsoft.dvrmoney.api.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.nivsoft.dvrmoney.api.model.Categoria;
import com.nivsoft.dvrmoney.api.repository.CategoriaRepository;

@Service
public class CategoriaService implements GenericService<Categoria, Long> {

	@Autowired
	CategoriaRepository categoriaRepository;

	@Override
	public Categoria criar(Categoria entity) {
		Categoria categoriaSalva = categoriaRepository.save(entity);
		return categoriaSalva;
	}

	@Override
	public List<Categoria> listar() {
		return categoriaRepository.findAll();
	}

	@Override
	public Categoria atualizar(Long id, Categoria entity) {
		Categoria categoriaSalva = buscarPorId(id);
		if (categoriaSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		BeanUtils.copyProperties(entity, categoriaSalva, "id");
		return categoriaRepository.save(categoriaSalva);
	}

	@Override
	public void deletarPorId(Long id) {
		categoriaRepository.delete(id);
	}

	@Override
	public Categoria buscarPorId(Long id) {
		Categoria categoriaSalva = categoriaRepository.findOne(id);
		if (categoriaSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return categoriaSalva;
	}

}
