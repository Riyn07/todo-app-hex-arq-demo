package com.example.infrastructure.adapter.in.rest.dto;

import java.time.LocalDateTime;

import com.example.domain.model.TaskStatus;

public record TaskResponse(
        long id,
        String title,
        String description,
        TaskStatus status,
        LocalDateTime createdAt,
        LocalDateTime completedAt,
        String imagePath
) {}