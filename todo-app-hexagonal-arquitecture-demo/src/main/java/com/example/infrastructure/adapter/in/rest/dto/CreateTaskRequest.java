package com.example.infrastructure.adapter.in.rest.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateTaskRequest(
        @NotBlank(message = "El titulo es obligatorio") String title,
        @NotBlank(message = "La descripcion es obligatoria") String description
) {}