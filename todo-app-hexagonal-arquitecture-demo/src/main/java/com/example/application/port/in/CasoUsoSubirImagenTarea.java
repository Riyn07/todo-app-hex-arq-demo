package com.example.application.port.in;

import com.example.domain.model.Tarea;

public interface CasoUsoSubirImagenTarea {
	Tarea uploadImage(long id, String imagePath);
}