package com.example.application.port.in;

import java.util.List;

import com.example.domain.model.Task;

public interface ListTaskUseCase {
	List<Task> listAll();
}
