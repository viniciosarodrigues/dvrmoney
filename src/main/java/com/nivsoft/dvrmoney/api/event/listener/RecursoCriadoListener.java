package com.nivsoft.dvrmoney.api.event.listener;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nivsoft.dvrmoney.api.event.RecursoCriadoEvent;

@Component
public class RecursoCriadoListener implements ApplicationListener<RecursoCriadoEvent> {

	@Override
	public void onApplicationEvent(RecursoCriadoEvent recursoCriadoEvent) {
		HttpServletResponse response = recursoCriadoEvent.getResponse();
		Long codigoDeRecurso = recursoCriadoEvent.getCodigo();
		adicionaLocation(response, codigoDeRecurso);
	}

	private void adicionaLocation(HttpServletResponse response, Long codigoDeRecurso) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(codigoDeRecurso)
				.toUri();
		response.setHeader("Location", uri.toASCIIString());
	}

}
