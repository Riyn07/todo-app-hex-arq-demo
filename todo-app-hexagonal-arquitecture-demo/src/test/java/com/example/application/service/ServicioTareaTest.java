package com.example.application.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.application.port.out.PuertoRepositorioTarea;
import com.example.domain.exception.TareaNoEncontradaException;
import com.example.domain.model.Tarea;

@ExtendWith(MockitoExtension.class)
class ServicioTareaTest {

	@Mock
	private PuertoRepositorioTarea puertoRepositorioTarea;

	@InjectMocks
	private ServicioTarea servicioTarea;

	@Test
	void create_guarda_la_tarea_en_el_puerto_y_la_devuelve() {

		// GIVEN: una tarea sin id
		Tarea tarea = Tarea.builder()
				.title("Estudiar")
				.description("Hexagonal")
				.build();

		// GIVEN: el puerto simulado devuelve la misma tarea al guardar
		when(puertoRepositorioTarea.save(tarea)).thenReturn(tarea);

		// WHEN: el servicio crea la tarea
		Tarea resultado = servicioTarea.create(tarea);

		// THEN: devuelve la tarea y el puerto fue llamado con save(tarea)
		assertThat(resultado).isSameAs(tarea);
		verify(puertoRepositorioTarea).save(tarea);
	}

	@Test
	void getById_devuelve_la_tarea_cuando_existe() {

		// GIVEN: existe una tarea con id 1 en el puerto simulado
		Tarea tarea = Tarea.builder()
				.id(1L)
				.title("Estudiar")
				.build();
		when(puertoRepositorioTarea.findById(1L)).thenReturn(Optional.of(tarea));

		// WHEN: el servicio la busca
		Tarea resultado = servicioTarea.getById(1L);

		// THEN: devuelve esa tarea
		assertThat(resultado).isEqualTo(tarea);
	}

	@Test
	void getById_lanza_TareaNoEncontradaException_cuando_no_existe() {

		// GIVEN: el puerto simulado devuelve Optional vacio
		when(puertoRepositorioTarea.findById(999L)).thenReturn(Optional.empty());

		// WHEN / THEN: se lanza la excepcion de dominio con ese mensaje
		assertThatThrownBy(() -> servicioTarea.getById(999L))
				.isInstanceOf(TareaNoEncontradaException.class)
				.hasMessage("No ha sido encontrada la tarea con ID: 999");
	}

	@Test
	void listAll_devuelve_todas_las_tareas() {

		// GIVEN: el puerto simulado devuelve dos tareas
		when(puertoRepositorioTarea.findAll()).thenReturn(List.of(
				Tarea.builder().id(1L).title("Primera").build(),
				Tarea.builder().id(2L).title("Segunda").build()));

		// WHEN: el servicio lista
		List<Tarea> resultado = servicioTarea.listAll();

		// THEN: devuelve 2 tareas y el puerto fue llamado con findAll()
		assertThat(resultado).hasSize(2);
		verify(puertoRepositorioTarea).findAll();
	}
}