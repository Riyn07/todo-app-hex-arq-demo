package com.example.infrastructure.adapter.in.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.application.port.in.CreateTaskUseCase;
import com.example.application.port.in.GetTaskUseCase;
import com.example.application.port.in.ListTaskUseCase;
import com.example.domain.model.Task;
import com.example.infrastructure.adapter.in.rest.dto.CreateTaskRequest;
import com.example.infrastructure.adapter.in.rest.dto.TaskResponse;
import com.example.infrastructure.adapter.in.rest.mapper.TaskRestMapper;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {

	private final CreateTaskUseCase createTaskUseCase;
	private final GetTaskUseCase getTaskUseCase;
	private final ListTaskUseCase listTaskUseCase;
	private final TaskRestMapper taskRestMapper;

	@PostMapping
	public ResponseEntity<TaskResponse> create(@Valid @RequestBody CreateTaskRequest request) {

		Task task = taskRestMapper.toDomain(request);
		Task saved = createTaskUseCase.create(task);

		return ResponseEntity.status(HttpStatus.CREATED).body(taskRestMapper.toResponse(saved));
	}

	@GetMapping("/{id}")
	public ResponseEntity<TaskResponse> getById(@PathVariable long id) {

		Task task = getTaskUseCase.getById(id);

		return ResponseEntity.ok(taskRestMapper.toResponse(task));
	}

	@GetMapping
	public ResponseEntity<List<TaskResponse>> listAll() {

		List<TaskResponse> response = listTaskUseCase.listAll()
				.stream()
				.map(taskRestMapper::toResponse)
				.collect(Collectors.toList());

		return ResponseEntity.ok(response);
	}
}
