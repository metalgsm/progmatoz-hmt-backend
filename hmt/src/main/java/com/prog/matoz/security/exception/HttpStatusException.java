package com.prog.matoz.security.exception;

public abstract class HttpStatusException extends RuntimeException {
	private static final long serialVersionUID = 5813263241011589772L;

	public HttpStatusException() {
	}

	public HttpStatusException(String message) {
		super(message);
	}

	public HttpStatusException(Throwable cause) {
		super(cause);
	}

	public HttpStatusException(String message, Throwable cause) {
		super(message, cause);
	}

	public abstract int getStatusCode();
}
