package com.example.infrastructure.adapter.out.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.domain.model.Tarea;
import com.example.infrastructure.adapter.out.persistence.EntidadTareaJpa;

@Mapper(componentModel = "spring")
public interface MapeadorPersistenciaTarea {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    EntidadTareaJpa toEntidad(Tarea tarea);

    Tarea toDomain(EntidadTareaJpa entidad);
}