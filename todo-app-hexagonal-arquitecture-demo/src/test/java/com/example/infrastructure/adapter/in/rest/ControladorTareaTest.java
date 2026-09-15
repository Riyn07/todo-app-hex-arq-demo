package com.example.infrastructure.adapter.in.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.application.port.out.PuertoRepositorioTarea;
import com.example.domain.model.Tarea;

@SpringBootTest
@AutoConfigureMockMvc
class ControladorTareaTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private PuertoRepositorioTarea puertoRepositorioTarea;

	@Test
	void crearTarea_devuelve_201_con_la_tarea_guardada() throws Exception {

		// GIVEN: una peticion POST con JSON valido
		String json = """
				{
					"title": "Estudiar Java",
					"description": "Practicar tests"
				}
				""";

		// WHEN / THEN: se envia y se espera 201 + los campos del JSON
		mockMvc.perform(post("/api/v1/tasks")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").exists())
				.andExpect(jsonPath("$.title").value("Estudiar Java"))
				.andExpect(jsonPath("$.status").value("PENDING"));
	}

	@Test
	void crearTareaConTituloVacio_devuelve_400_con_errores() throws Exception {

		// GIVEN: una peticion POST con titulo vacio (viola @NotBlank)
		String json = """
				{
					"title": "",
					"description": "Algo"
				}
				""";

		// WHEN / THEN: se envia y se espera 400 + el array de errores
		mockMvc.perform(post("/api/v1/tasks")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.errors").isArray())
				.andExpect(jsonPath("$.errors[0]").value("title: El titulo es obligatorio"));
	}

	@Test
	void obtenerTareaPorId_devuelve_200() throws Exception {

		// GIVEN: guardamos una tarea real en la base de datos H2
		Tarea tarea = puertoRepositorioTarea.save(Tarea.builder()
				.title("Guardada")
				.description("Para el test")
				.build());

		// WHEN / THEN: pedimos esa tarea por su ID y se espera 200
		mockMvc.perform(get("/api/v1/tasks/" + tarea.getId()))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.title").value("Guardada"));
	}

	@Test
	void obtenerTareaInexistente_devuelve_404() throws Exception {

		// GIVEN: un ID que no existe en la base de datos

		// WHEN / THEN: se espera 404 con el titulo "Tarea no encontrada"
		mockMvc.perform(get("/api/v1/tasks/99999"))
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.title").value("Tarea no encontrada"));
	}

	@Test
	void listarTareas_devuelve_200_con_un_array() throws Exception {

		// WHEN / THEN: se listan las tareas y se espera 200 con un array
		mockMvc.perform(get("/api/v1/tasks"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").isArray());
	}
}