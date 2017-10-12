package com.nivsoft.dvrmoney.api.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.nivsoft.dvrmoney.api.model.Pessoa;
import com.nivsoft.dvrmoney.api.repository.PessoaRepository;

@Service
public class PessoaService implements GenericService<Pessoa, Long> {

	@Autowired
	PessoaRepository pessoaRepository;

	public Pessoa atualizaPropriedadeAtivo(Long id, Boolean ativo) {
		Pessoa pessoaSalva = buscarPorId(id);
		pessoaSalva.setAtivo(ativo);

		return pessoaRepository.save(pessoaSalva);
	}

	@Override
	public Pessoa criar(Pessoa entity) {
		return pessoaRepository.save(entity);
	}

	@Override
	public Pessoa atualizar(Long id, Pessoa entity) {
		Pessoa pessoaSalva = pessoaRepository.getOne(id);
		if (pessoaSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		BeanUtils.copyProperties(entity, pessoaSalva, "id");
		return pessoaRepository.save(pessoaSalva);
	}

	@Override
	public void deletarPorId(Long id) {
		pessoaRepository.delete(id);
	}

	@Override
	public Pessoa buscarPorId(Long id) {
		Pessoa pessoaSalva = pessoaRepository.findOne(id);
		if (pessoaSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return pessoaSalva;
	}

	@Override
	public List<Pessoa> listar() {
		return pessoaRepository.findAll();
	}
}
