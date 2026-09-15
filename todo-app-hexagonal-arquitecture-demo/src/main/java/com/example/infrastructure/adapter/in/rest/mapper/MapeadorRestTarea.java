package com.example.infrastructure.adapter.in.rest.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.domain.model.Tarea;
import com.example.infrastructure.adapter.in.rest.dto.SolicitudCrearTarea;
import com.example.infrastructure.adapter.in.rest.dto.RespuestaTarea;

@Mapper(componentModel = "spring")
public interface MapeadorRestTarea {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "completedAt", ignore = true)
    Tarea toDomain(SolicitudCrearTarea solicitud);

    RespuestaTarea toResponse(Tarea tarea);
}