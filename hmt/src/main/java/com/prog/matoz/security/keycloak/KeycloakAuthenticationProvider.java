package com.prog.matoz.security.keycloak;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;

import com.prog.matoz.security.jwt.JwtAuthentication;

public class KeycloakAuthenticationProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication authentication) {
		return authentication;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return JwtAuthentication.class.isAssignableFrom(authentication);
	}
}