package com.nivsoft.dvrmoney.api.repository.lancamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nivsoft.dvrmoney.api.model.Lancamento;
import com.nivsoft.dvrmoney.api.repository.filter.LancamentoFilter;
import com.nivsoft.dvrmoney.api.repository.projection.ResumoLancamento;

public interface LancamentoRepositoryQuery {
	public Page<Lancamento> listar(LancamentoFilter lancamentoFilter, Pageable pageable);

	public Page<ResumoLancamento> resumir(LancamentoFilter lancamentoFilter, Pageable pageable);
}
