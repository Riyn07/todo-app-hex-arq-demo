package com.example.application.port.in;

import com.example.domain.model.Task;

public interface CreateTaskUseCase {
	Task create(Task task);
}
