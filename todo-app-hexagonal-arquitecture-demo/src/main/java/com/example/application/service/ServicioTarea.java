/* Implementa los casos de uso */
package com.example.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.application.port.in.CasoUsoCrearTarea;
import com.example.application.port.in.CasoUsoObtenerTarea;
import com.example.application.port.in.CasoUsoListarTareas;
import com.example.application.port.out.PuertoRepositorioTarea;
import com.example.domain.exception.TareaNoEncontradaException;
import com.example.domain.model.Tarea;

import lombok.RequiredArgsConstructor;

/* ¿Es correcta una anotacion de Spring aqui? 
 * 
 * Los mas puristas dirian que NO, pero tiene un coste implementar esto
 * correctamente.
 * 
 * Con esta anotacion estamos introduciendo una dependencia del framework
 * en la capa de aplicacion, y el problema es que si mañana migramos a quarkus
 * o cualquier otro framework o si queremos testear el caso de uso en aislamiento total, esta clase
 * ya no seria agnostica del framework, es decir, estaria acoplada el Spring Framework
 * 
 * TODO: ¿Que deberia hacerse para que este acoplamiento no existiera?
 * 
 * Rta. Crear una clase de configuracion, anotada con @Configuration o @Component
 * en la capa de Infraestructura donde tengamos todos los Bean que 
 * hay que crear cuando se levanta el contexto de Spring
 * 
 * */

@RequiredArgsConstructor
@Service
public class ServicioTarea implements CasoUsoCrearTarea, CasoUsoObtenerTarea, CasoUsoListarTareas {

	private final PuertoRepositorioTarea puertoRepositorioTarea;
	
	@Override
	public Tarea create(Tarea tarea) {
		return puertoRepositorioTarea.save(tarea);
	}

	@Override
	public Tarea getById(long id) {
		return puertoRepositorioTarea.findById(id)
					.orElseThrow(() -> new TareaNoEncontradaException(id));
	}

	@Override
	public List<Tarea> listAll() {
		return puertoRepositorioTarea.findAll();
	}

}