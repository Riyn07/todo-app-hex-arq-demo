package com.example.application.port.out;

import java.util.List;
import java.util.Optional;

import com.example.domain.model.Tarea;

public interface PuertoRepositorioTarea {
	Tarea save(Tarea tarea);
	Optional<Tarea> findById(long id);
	List<Tarea> findAll();
}