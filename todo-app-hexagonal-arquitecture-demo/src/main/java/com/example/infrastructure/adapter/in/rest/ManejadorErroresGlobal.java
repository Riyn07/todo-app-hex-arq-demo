package com.example.infrastructure.adapter.in.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.domain.exception.TareaNoEncontradaException;

@RestControllerAdvice
public class ManejadorErroresGlobal {
	
	@ExceptionHandler(TareaNoEncontradaException.class)
	public ProblemDetail handleTareaNoEncontrada(TareaNoEncontradaException ex) {
		
		@SuppressWarnings("null")
		ProblemDetail problem = ProblemDetail.forStatusAndDetail(
				HttpStatus.NOT_FOUND, ex.getMessage()
		);
		
		problem.setTitle("Tarea no encontrada");
		
		return problem;
	}

	@SuppressWarnings("null")
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ProblemDetail handleValidationErrors(MethodArgumentNotValidException ex) {
		
		List<String> errors = ex.getBindingResult()
				.getFieldErrors()
				.stream()
				.map(error -> error.getField() + ": " + error.getDefaultMessage())
				.toList();
		
		ProblemDetail problem = ProblemDetail.forStatusAndDetail(
				HttpStatus.BAD_REQUEST, "Error de Validación"
		);
		
		problem.setTitle("Datos invalidos recibidos");
		problem.setProperty("errors", errors);
		
		return problem;
		
	}
}