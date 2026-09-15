	package com.example.infrastructure.adapter.out.persistence;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.example.application.port.out.PuertoRepositorioTarea;
import com.example.domain.model.Tarea;
import com.example.infrastructure.adapter.out.persistence.mapper.MapeadorPersistenciaTarea;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AdaptadorRepositorioTareaJpa implements PuertoRepositorioTarea {

	private final RepositorioTareaSpringData repositorioTareaSpringData;
	private final MapeadorPersistenciaTarea mapeadorPersistenciaTarea;

	@Override
	public Tarea save(Tarea tarea) {

		tarea.initDefaults();
		EntidadTareaJpa entidad = mapeadorPersistenciaTarea.toEntidad(tarea);
		EntidadTareaJpa guardada = repositorioTareaSpringData.save(entidad);

		return mapeadorPersistenciaTarea.toDomain(guardada);
	}

	@Override
	public Optional<Tarea> findById(long id) {
		return repositorioTareaSpringData.findById(id).map(mapeadorPersistenciaTarea::toDomain);
	}

	@Override
	public List<Tarea> findAll() {
		return repositorioTareaSpringData.findAll()
				.stream()
				.map(mapeadorPersistenciaTarea::toDomain)
				.collect(Collectors.toList());
	}
}