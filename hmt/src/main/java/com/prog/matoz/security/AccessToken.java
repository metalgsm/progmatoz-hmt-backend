package com.prog.matoz.security;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.prog.matoz.config.TemplateHelper;
import com.prog.matoz.security.exception.InvalidTokenException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AccessToken {
	public static final String BEARER = "Bearer ";

	private final String value;

	public String getValueAsString() {
		return value;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		DecodedJWT decodedJWT = decodeToken(value);
		JsonObject payloadAsJson = decodeTokenPayloadToJsonObject(decodedJWT);

		return StreamSupport
				.stream(payloadAsJson.getAsJsonObject("realm_access").getAsJsonArray("roles").spliterator(), false)
				.map(JsonElement::getAsString).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	}

	private DecodedJWT decodeToken(String value) {
		if (TemplateHelper.isNull(value)) {
			throw new InvalidTokenException("Token has not been provided");
		}
		return JWT.decode(value);
	}

	private JsonObject decodeTokenPayloadToJsonObject(DecodedJWT decodedJWT) {
		try {
			String payloadAsString = decodedJWT.getPayload();
			return new Gson().fromJson(new String(Base64.getDecoder().decode(payloadAsString), StandardCharsets.UTF_8),
					JsonObject.class);
		} catch (RuntimeException exception) {
			throw new InvalidTokenException("Invalid JWT or JSON format of each of the jwt parts", exception);
		}
	}
}
