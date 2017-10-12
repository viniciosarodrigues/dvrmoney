package com.nivsoft.dvrmoney.api.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nivsoft.dvrmoney.api.event.RecursoCriadoEvent;
import com.nivsoft.dvrmoney.api.model.Lancamento;
import com.nivsoft.dvrmoney.api.repository.filter.LancamentoFilter;
import com.nivsoft.dvrmoney.api.repository.projection.ResumoLancamento;
import com.nivsoft.dvrmoney.api.service.LancamentoService;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {

	@Autowired
	LancamentoService lancamentoService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oauth2.hasScope('read')")
	public Page<Lancamento> pesquisar(LancamentoFilter lancamentoFilter, Pageable pageable) {
		return lancamentoService.filtrar(lancamentoFilter, pageable);
	}

	@GetMapping(params = "resumo")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oauth2.hasScope('read')")
	public Page<ResumoLancamento> resumir(LancamentoFilter lancamentoFilter, Pageable pageable) {
		return lancamentoService.resumir(lancamentoFilter, pageable);
	}

	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRA_LANCAMENTO') and #oauth2.hasScope('read')")
	public ResponseEntity<Lancamento> criarLancamento(@Valid @RequestBody Lancamento lancamento,
			HttpServletResponse response) {
		Lancamento lancamentoSalvo = lancamentoService.criar(lancamento);

		publisher.publishEvent(new RecursoCriadoEvent(lancamentoSalvo, response, lancamentoSalvo.getId()));

		return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoSalvo);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oauth2.hasScope('read')")
	public Lancamento buscarLancamentoPeloId(@PathVariable Long id) {
		return lancamentoService.buscarPorId(id);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oauth2.hasScope('delete')")
	public void removeLancamento(@PathVariable Long id) {
		lancamentoService.deletarPorId(id);
	}

}
