package com.example.domain.exception;

@SuppressWarnings("serial")
public class TareaNoEncontradaException extends RuntimeException {

	public TareaNoEncontradaException(long id) {
		super("No ha sido encontrada la tarea con ID: " + id);
	}
	
}
