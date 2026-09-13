package com.example.domain.exception;

@SuppressWarnings("serial")
public class TaskNotFoundException extends RuntimeException {

	/* TaskNotFoundException es una regla de negocio y como RuntimeException
	 * no necesita nada del framework Spring se puede utilizar en este contexto,
	 * es decir, en el dominio */
	public TaskNotFoundException(long id) {
		super("No ha sido encontrada la tarea con ID: " + id);
	}
	
}
