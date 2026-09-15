package com.example.application.port.in;

import com.example.domain.model.Tarea;

public interface CasoUsoActualizarTarea {
	Tarea update(long id, Tarea tarea);
}