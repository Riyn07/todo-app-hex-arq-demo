package com.example.domain.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Tarea {

	@EqualsAndHashCode.Include
	private long id;
	
	private String title;
	private String description;
	private EstadoTarea status;
	private LocalDateTime createdAt;
	private LocalDateTime completedAt;
	
	public void complete() {
		
		if (this.status == EstadoTarea.COMPLETED) {
			throw new IllegalStateException("La tarea ya está completada");
		}
		
		this.status = EstadoTarea.COMPLETED;
		this.completedAt = LocalDateTime.now();
	}
	
	public void reopen() {
		
		if (this.status == EstadoTarea.PENDING) {
			throw new IllegalStateException("La tarea ya está pendiente");
		}
		
		this.status = EstadoTarea.PENDING;
		this.completedAt = null;
	}
	
	public void initDefaults() {
		if (this.status == null)
			this.status = EstadoTarea.PENDING;
		if (this.createdAt == null)
			this.createdAt = LocalDateTime.now();
	}
}
