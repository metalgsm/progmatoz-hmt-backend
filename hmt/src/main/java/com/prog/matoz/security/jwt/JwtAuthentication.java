package com.prog.matoz.security.jwt;

import org.springframework.security.authentication.AbstractAuthenticationToken;

import com.prog.matoz.security.AccessToken;

public class JwtAuthentication extends AbstractAuthenticationToken {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7307318010111081996L;
	
	private final AccessToken accessToken;

	public JwtAuthentication(AccessToken accessToken) {
		super(accessToken.getAuthorities());
		this.accessToken = accessToken;
	}

	@Override
	public Object getCredentials() {
		return null;
	}

	@Override
	public Object getPrincipal() {
		return accessToken.getValueAsString();
	}
}
