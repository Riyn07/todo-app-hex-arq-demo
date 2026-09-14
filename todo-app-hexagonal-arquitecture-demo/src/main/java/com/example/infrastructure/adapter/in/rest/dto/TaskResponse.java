package com.example.infrastructure.adapter.in.rest.dto;

import java.time.LocalDateTime;

import com.example.domain.model.TaskStatus;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TaskResponse {

	private long id;
	private String title;
	private String description;
	private TaskStatus status;
	private LocalDateTime createdAt;
	private LocalDateTime completedAt;
}
