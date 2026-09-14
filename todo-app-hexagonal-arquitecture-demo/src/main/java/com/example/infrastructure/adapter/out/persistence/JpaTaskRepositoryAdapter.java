package com.example.infrastructure.adapter.out.persistence;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.example.application.port.out.TaskRepositoryPort;
import com.example.domain.model.Task;
import com.example.infrastructure.adapter.out.persistence.mapper.TaskPersistenceMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class JpaTaskRepositoryAdapter implements TaskRepositoryPort {

	private final SpringDataTaskRepository springDataTaskRepository;
	private final TaskPersistenceMapper taskPersistenceMapper;

	@Override
	public Task save(Task task) {

		task.initDefaults();
		TaskJpaEntity entity = taskPersistenceMapper.toJpaEntity(task);
		TaskJpaEntity saved = springDataTaskRepository.save(entity);

		return taskPersistenceMapper.toDomain(saved);
	}

	@Override
	public Optional<Task> findById(long id) {
		return springDataTaskRepository.findById(id).map(taskPersistenceMapper::toDomain);
	}

	@Override
	public List<Task> findAll() {
		return springDataTaskRepository.findAll()
				.stream()
				.map(taskPersistenceMapper::toDomain)
				.collect(Collectors.toList());
	}
}
