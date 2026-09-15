package com.example.infrastructure.adapter.in.rest.dto;

import java.time.LocalDateTime;

import com.example.domain.model.EstadoTarea;

public record RespuestaTarea(
	long id,
	String title,
	String description,
	EstadoTarea status,
	LocalDateTime createdAt,
	LocalDateTime completedAt
) {
}