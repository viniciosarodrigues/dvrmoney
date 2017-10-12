package com.nivsoft.dvrmoney.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nivsoft.dvrmoney.api.model.Lancamento;
import com.nivsoft.dvrmoney.api.repository.lancamento.LancamentoRepositoryQuery;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, LancamentoRepositoryQuery {

}
