package com.nivsoft.dvrmoney.api.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nivsoft.dvrmoney.api.model.Lancamento;
import com.nivsoft.dvrmoney.api.repository.LancamentoRepository;
import com.nivsoft.dvrmoney.api.repository.filter.LancamentoFilter;
import com.nivsoft.dvrmoney.api.repository.projection.ResumoLancamento;

@Service
public class LancamentoService implements GenericService<Lancamento, Long> {

	@Autowired
	LancamentoRepository lancamentoRepository;

	@Override
	public Lancamento criar(Lancamento entity) {
		return lancamentoRepository.save(entity);
	}

	@Override
	public List<Lancamento> listar() {
		return lancamentoRepository.findAll();
	}

	@Override
	public Lancamento atualizar(Long id, Lancamento entity) {
		Lancamento lancamentoSalvo = buscarPorId(id);
		if (lancamentoSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		BeanUtils.copyProperties(entity, lancamentoSalvo, "id");
		return lancamentoRepository.save(lancamentoSalvo);
	}

	@Override
	public void deletarPorId(Long id) {
		lancamentoRepository.delete(id);

	}

	@Override
	public Lancamento buscarPorId(Long id) {
		Lancamento lancamentoSalvo = lancamentoRepository.findOne(id);
		if (lancamentoSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return lancamentoSalvo;
	}

	public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable) {
		return lancamentoRepository.listar(lancamentoFilter, pageable);
	}

	public Page<ResumoLancamento> resumir(LancamentoFilter lancamentoFilter, Pageable pageable) {
		return lancamentoRepository.resumir(lancamentoFilter, pageable);
	}

}
