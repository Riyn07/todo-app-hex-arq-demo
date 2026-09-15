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

import com.example.application.port.in.CasoUsoCrearTarea;
import com.example.application.port.in.CasoUsoObtenerTarea;
import com.example.application.port.in.CasoUsoListarTareas;
import com.example.domain.model.Tarea;
import com.example.infrastructure.adapter.in.rest.dto.SolicitudCrearTarea;
import com.example.infrastructure.adapter.in.rest.dto.RespuestaTarea;
import com.example.infrastructure.adapter.in.rest.mapper.MapeadorRestTarea;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class ControladorTarea {

	private final CasoUsoCrearTarea casoUsoCrearTarea;
	private final CasoUsoObtenerTarea casoUsoObtenerTarea;
	private final CasoUsoListarTareas casoUsoListarTareas;
	private final MapeadorRestTarea mapeadorRestTarea;

	@PostMapping
	public ResponseEntity<RespuestaTarea> create(@Valid @RequestBody SolicitudCrearTarea solicitud) {

		Tarea tarea = mapeadorRestTarea.toDomain(solicitud);
		Tarea guardada = casoUsoCrearTarea.create(tarea);

		return ResponseEntity.status(HttpStatus.CREATED).body(mapeadorRestTarea.toResponse(guardada));
	}

	@GetMapping("/{id}")
	public ResponseEntity<RespuestaTarea> getById(@PathVariable long id) {

		Tarea tarea = casoUsoObtenerTarea.getById(id);

		return ResponseEntity.ok(mapeadorRestTarea.toResponse(tarea));
	}

	@GetMapping
	public ResponseEntity<List<RespuestaTarea>> listAll() {

		List<RespuestaTarea> response = casoUsoListarTareas.listAll()
				.stream()
				.map(mapeadorRestTarea::toResponse)
				.collect(Collectors.toList());

		return ResponseEntity.ok(response);
	}
}