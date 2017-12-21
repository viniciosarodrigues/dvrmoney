package com.nivsoft.dvrmoney.api.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("dvrmoney")
public class DvrMoneyAPIProperty {

	private String origemPermitida = "http://localhost:8000";
	private final Seguranca seguranca = new Seguranca();

	public String getOrigemPermitida() {
		return origemPermitida;
	}

	public void setOrigemPermitida(String origemPermitida) {
		this.origemPermitida = origemPermitida;
	}

	public Seguranca getSeguranca() {
		return seguranca;
	}

	public static class Seguranca {
		private boolean enableHTTPS;

		public boolean isEnableHTTPS() {
			return enableHTTPS;
		}

		public void setEnableHTTPS(boolean enableHTTPS) {
			this.enableHTTPS = enableHTTPS;
		}
	}
}
