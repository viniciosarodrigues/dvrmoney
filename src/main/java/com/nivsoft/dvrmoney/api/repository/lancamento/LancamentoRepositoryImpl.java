package com.nivsoft.dvrmoney.api.repository.lancamento;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.nivsoft.dvrmoney.api.model.Categoria_;
import com.nivsoft.dvrmoney.api.model.Lancamento;
import com.nivsoft.dvrmoney.api.model.Lancamento_;
import com.nivsoft.dvrmoney.api.model.Pessoa_;
import com.nivsoft.dvrmoney.api.repository.filter.LancamentoFilter;
import com.nivsoft.dvrmoney.api.repository.projection.ResumoLancamento;

public class LancamentoRepositoryImpl implements LancamentoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	/**
	 * Implementação de paginação
	 */
	@Override
	public Page<Lancamento> listar(LancamentoFilter lancamentoFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Lancamento> criteria = builder.createQuery(Lancamento.class);
		Root<Lancamento> root = criteria.from(Lancamento.class);

		Predicate[] predicates = criarRestricoes(lancamentoFilter, builder, root);

		criteria.where(predicates);

		TypedQuery<Lancamento> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(lancamentoFilter));
	}

	/**
	 * Cria restrições de filtro
	 * 
	 * @param lancamentoFilter
	 * @param builder
	 * @param root
	 * @return
	 */
	private Predicate[] criarRestricoes(LancamentoFilter lancamentoFilter, CriteriaBuilder builder,
			Root<Lancamento> root) {

		List<Predicate> predicates = new ArrayList<>();

		if (!StringUtils.isEmpty(lancamentoFilter.getDescricao())) {
			predicates.add(builder.like(builder.lower(root.get(Lancamento_.descricao)),
					"%" + lancamentoFilter.getDescricao() + "%"));
		}

		if (!StringUtils.isEmpty(lancamentoFilter.getDataVencimentoDe())) {
			predicates.add(builder.greaterThanOrEqualTo(root.get(Lancamento_.dataVencimento),
					lancamentoFilter.getDataVencimentoDe()));
		}

		if (!StringUtils.isEmpty(lancamentoFilter.getDataVencimentoAte())) {
			predicates.add(builder.lessThanOrEqualTo(root.get(Lancamento_.dataVencimento),
					lancamentoFilter.getDataVencimentoAte()));
		}
		return predicates.toArray(new Predicate[predicates.size()]);
	}

	/**
	 * Adiciona as restrições de paginação
	 * 
	 * @param query
	 * @param pageable
	 */
	private void adicionarRestricoesDePaginacao(TypedQuery<?> query, Pageable pageable) {
		Integer paginaAtula = pageable.getPageNumber();
		Integer totalRegistrosPorPagina = pageable.getPageSize();
		Integer primeiroRegistroDaPagina = paginaAtula * totalRegistrosPorPagina;

		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
	}

	/**
	 * Retorna o total por páginas
	 * 
	 * @param lancamentoFilter
	 * @return
	 */
	private Long total(LancamentoFilter lancamentoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
		Root<Lancamento> root = criteriaQuery.from(Lancamento.class);

		Predicate[] predicates = criarRestricoes(lancamentoFilter, builder, root);
		criteriaQuery.where(predicates);
		criteriaQuery.select(builder.count(root));

		return manager.createQuery(criteriaQuery).getSingleResult();
	}

	@Override
	public Page<ResumoLancamento> resumir(LancamentoFilter lancamentoFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ResumoLancamento> criteria = builder.createQuery(ResumoLancamento.class);
		Root<Lancamento> root = criteria.from(Lancamento.class);
		criteria.select(builder.construct(ResumoLancamento.class, root.get(Lancamento_.id),
				root.get(Lancamento_.descricao), root.get(Lancamento_.dataVencimento),
				root.get(Lancamento_.dataPagamento), root.get(Lancamento_.valor), root.get(Lancamento_.tipo),
				root.get(Lancamento_.categoria).get(Categoria_.nome), root.get(Lancamento_.pessoa).get(Pessoa_.nome)));

		Predicate[] predicates = criarRestricoes(lancamentoFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<ResumoLancamento> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(lancamentoFilter));
	}

}
