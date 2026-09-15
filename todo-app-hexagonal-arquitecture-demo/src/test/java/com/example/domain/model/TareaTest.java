package com.example.domain.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class TareaTest {

	@Test
	void initDefaults_define_estado_PENDING_y_fecha_creacion_cuando_son_null() {

		// GIVEN: una tarea recien creada sin estado ni fecha
		Tarea tarea = new Tarea();
		tarea.setTitle("Comprar pan");
		tarea.setDescription("Pan integral");

		// WHEN: se inicializan los valores por defecto
		tarea.initDefaults();

		// THEN: el estado es PENDING y la fecha de creacion no es null
		assertThat(tarea.getStatus()).isEqualTo(EstadoTarea.PENDING);
		assertThat(tarea.getCreatedAt()).isNotNull();
	}

	@Test
	void complete_marca_tarea_como_COMPLETED_y_guarda_fecha_de_completado() {

		// GIVEN: una tarea pendiente
		Tarea tarea = Tarea.builder()
				.title("Estudiar")
				.description("Arquitectura hexagonal")
				.status(EstadoTarea.PENDING)
				.build();

		// WHEN: se completa la tarea
		tarea.complete();

		// THEN: el estado es COMPLETED y la fecha de completado no es null
		assertThat(tarea.getStatus()).isEqualTo(EstadoTarea.COMPLETED);
		assertThat(tarea.getCompletedAt()).isNotNull();
	}

	@Test
	void complete_lanza_excepcion_cuando_tarea_ya_esta_completada() {

		// GIVEN: una tarea ya completada (status COMPLETED)
		Tarea tarea = Tarea.builder()
				.title("Estudiar")
				.status(EstadoTarea.COMPLETED)
				.build();

		// WHEN / THEN: al completarla se lanza IllegalStateException con ese mensaje
		assertThatThrownBy(() -> tarea.complete())
				.isInstanceOf(IllegalStateException.class)
				.hasMessage("La tarea ya está completada");
	}

	@Test
	void reopen_cambia_a_PENDING_y_borra_la_fecha_de_completado() {

		// GIVEN: una tarea completada con fecha de completado
		Tarea tarea = Tarea.builder()
				.title("Estudiar")
				.status(EstadoTarea.COMPLETED)
				.completedAt(LocalDateTime.now())
				.build();

		// WHEN: se reabre la tarea
		tarea.reopen();

		// THEN: el estado es PENDING y la fecha de completado queda null
		assertThat(tarea.getStatus()).isEqualTo(EstadoTarea.PENDING);
		assertThat(tarea.getCompletedAt()).isNull();
	}

	@Test
	void reopen_lanza_excepcion_cuando_tarea_ya_esta_pendiente() {

		// GIVEN: una tarea pendiente (status PENDING)
		Tarea tarea = Tarea.builder()
				.title("Estudiar")
				.status(EstadoTarea.PENDING)
				.build();

		// WHEN / THEN: al reabrirla se lanza IllegalStateException con ese mensaje
		assertThatThrownBy(() -> tarea.reopen())
				.isInstanceOf(IllegalStateException.class)
				.hasMessage("La tarea ya está pendiente");
	}
}