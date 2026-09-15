package com.example.application.port.in;

import com.example.domain.model.Tarea;

public interface CasoUsoObtenerTarea {
	Tarea getById(long id);
}