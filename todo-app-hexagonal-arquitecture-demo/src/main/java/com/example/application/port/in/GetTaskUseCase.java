package com.example.application.port.in;

import com.example.domain.model.Task;

public interface GetTaskUseCase {
	Task getById(long id);
}
