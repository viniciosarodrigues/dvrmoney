package com.nivsoft.dvrmoney.api.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Util {

	private Util() {
	}

	// Criptografa senhas
	public static String encodePassword(String senhaNaoCriptografada) {
		return new BCryptPasswordEncoder().encode(senhaNaoCriptografada);
	}
}
