package com.example.infrastructure.adapter.out.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.domain.model.Task;
import com.example.infrastructure.adapter.out.persistence.TaskJpaEntity;

@Mapper(componentModel = "spring")
public interface TaskPersistenceMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    TaskJpaEntity toJpaEntity(Task task);

    Task toDomain(TaskJpaEntity entity);
}
