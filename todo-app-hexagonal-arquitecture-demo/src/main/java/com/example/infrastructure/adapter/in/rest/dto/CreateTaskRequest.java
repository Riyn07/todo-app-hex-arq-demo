package com.example.infrastructure.adapter.in.rest.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTaskRequest {
	
	@NotBlank(message = "El titulo es obligatorio")
	private String title;
	
	@NotBlank(message = "La descripcion es obligatoria")
	private String description;
}
