package bg.tfg.voyages.controller;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bg.tfg.voyages.model.Actividades;
import bg.tfg.voyages.model.Destinos;
import bg.tfg.voyages.model.Usuarios;
import bg.tfg.voyages.service.ActividadService;
import bg.tfg.voyages.service.DestinoService;
import bg.tfg.voyages.service.ReservaService;

/**
 * Controlador REST para gestionar actividades turísticas. Permite realizar
 * operaciones CRUD sobre las actividades, así como gestionar las reservas
 * asociadas.
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/actividades")
public class ActividadController {
	@Autowired
	private ActividadService actividadService;

	@Autowired
	private DestinoService destinoService;

	@Autowired
	private ReservaService reservaService;

	@GetMapping("/{id}")
	public ResponseEntity<Actividades> obtenerActividadPorId(@PathVariable Long id) {
		Actividades actividades = actividadService.findById(id).orElse(null);
		if (actividades != null) {
			return new ResponseEntity<>(actividades, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/guardar")
	public ResponseEntity<Actividades> guardarActividad(@RequestBody Actividades actividad) {
		if (actividad.getDestino() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		// Obtengo el ID del destino desde la actividad
		Long idDestino = actividad.getDestino().getId();

		// Recupero el destino desde la base de datos utilizando el ID
		Destinos destino = destinoService.findById(idDestino)
				.orElseThrow(() -> new RuntimeException("Destino no encontrado con ID: " + idDestino));

		actividad.setDestino(destino);

		Actividades savedActividad = actividadService.save(actividad);

		return new ResponseEntity<>(savedActividad, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Actividades> actualizarActividad(@PathVariable Long id, @RequestBody Actividades actividad) {
		Actividades actividades = actividadService.findById(id).orElse(null);
		if (actividades != null) {
			// Obtengo el ID del destino desde la actividad
			Long idDestino = actividad.getDestino().getId();

			// Recupero el destino desde la base de datos utilizando el ID
			Destinos destino = destinoService.findById(idDestino)
					.orElseThrow(() -> new RuntimeException("Destino no encontrado con ID: " + idDestino));

			actividad.setDestino(destino);
			actividades.setDestino(actividad.getDestino());
			actividades.setNombreActividad(actividad.getNombreActividad());
			actividades.setDescripcion(actividad.getDescripcion());
			actividades.setFechaActividad(actividad.getFechaActividad());
			actividades.setHoraActividad(actividad.getHoraActividad());
			actividades.setUrlImagenActividad(actividad.getUrlImagenActividad());

			final Actividades updatedActividade = actividadService.save(actividades);
			return new ResponseEntity<>(updatedActividade, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> eliminarActividad(@PathVariable Long id) {

		// Elimino todas las reservas asociadas a cada actividad
		reservaService.deleteByActividadId(id);

		// Elimino la actividad por su id
		actividadService.deleteById(id);

		try {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/todas")
	public ResponseEntity<List<Actividades>> obtenerTodasLasActividades() {
		List<Actividades> actividades = actividadService.findAll();
		if (!actividades.isEmpty()) {
			return new ResponseEntity<>(actividades, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@PostMapping("/buscar")
	public List<Actividades> getActividadesByNombreDestinoAndFecha(@RequestBody Map<String, String> parametros) {
		String nombreDestino = parametros.get("nombre_destino");
		String fechaString = parametros.get("fecha");
		LocalDate fechaActividad = LocalDate.parse(fechaString);
		return actividadService.getActividadesByNombreDestinoAndFecha(nombreDestino, fechaActividad);
	}

	@PostMapping("/buscar/porDestino")
	public ResponseEntity<List<Actividades>> getActividadesByDestino(@RequestBody Map<String, String> payload) {
		String nombreDestino = payload.get("nombre_destino");
		if (nombreDestino == null || nombreDestino.isEmpty()) {
			return ResponseEntity.badRequest().build();
		}
		List<Actividades> actividades = actividadService.getActividadesByDestino(nombreDestino);
		return ResponseEntity.ok(actividades);
	}

	@PostMapping("/buscar/porFecha")
	public ResponseEntity<List<Actividades>> getActividadesByFecha(@RequestBody Map<String, String> payload) {
		String fecha = payload.get("fecha");
		if (fecha == null || fecha.isEmpty()) {
			return ResponseEntity.badRequest().build();
		}
		try {
			LocalDate fechaActividad = LocalDate.parse(fecha);
			List<Actividades> actividades = actividadService.findByFechaActividad(fechaActividad);
			return ResponseEntity.ok(actividades);
		} catch (DateTimeParseException e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping("/encontrar/{id}")
	public ResponseEntity<List<Actividades>> findActividadesByDestino(@PathVariable Long id) {
		List<Actividades> actividades = actividadService.getActividadesByIdDestino(id);
		if (actividades == null || actividades.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(actividades);
	}
}
