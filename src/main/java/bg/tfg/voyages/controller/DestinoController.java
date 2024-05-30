package bg.tfg.voyages.controller;

import java.util.List;

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
import bg.tfg.voyages.service.ActividadService;
import bg.tfg.voyages.service.DestinoService;
import bg.tfg.voyages.service.ReservaService;

/**
 * Controlador REST para gestionar destinos turísticos. Permite realizar
 * operaciones CRUD sobre los destinos, así como gestionar las actividades y
 * reservas asociadas.
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/destinos")
public class DestinoController {
	@Autowired
	private DestinoService destinoService;

	@Autowired
	private ActividadService actividadService;

	@Autowired
	private ReservaService reservaService;

	@GetMapping("/{id}")
	public ResponseEntity<Destinos> obtenerDestinoPorId(@PathVariable Long id) {
		Destinos destinos = destinoService.findById(id).orElse(null);
		if (destinos != null) {
			return new ResponseEntity<>(destinos, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/guardar")
	public ResponseEntity<Destinos> guardarDestino(@RequestBody Destinos destinos) {
		Destinos savedDestino = destinoService.save(destinos);
		return new ResponseEntity<>(savedDestino, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Destinos> actualizarDestino(@PathVariable Long id, @RequestBody Destinos destinoDetails) {
		Destinos destinos = destinoService.findById(id).orElse(null);
		if (destinos != null) {
			destinos.setNombre_destino(destinoDetails.getNombre_destino());
			destinos.setProvincia(destinoDetails.getProvincia());
			destinos.setPais(destinoDetails.getPais());

			final Destinos updatedDestino = destinoService.save(destinos);
			return new ResponseEntity<>(updatedDestino, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> eliminarDestino(@PathVariable Long id) {
		try {
			// Verifico si el destino existe
			if (!destinoService.findById(id).isPresent()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

			// Obtengo todas las actividades asociadas al destino
			List<Actividades> actividades = actividadService.getActividadesByIdDestino(id);

			// Elimino todas las reservas asociadas a cada actividad
			for (Actividades actividad : actividades) {
				reservaService.deleteByActividadId(actividad.getIdActividad());
			}

			// Elimino todas las actividades asociadas al destino
			actividadService.deleteByDestinoId(id);

			destinoService.deleteById(id);

			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			// Excepción para más detalles
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/todos")
	public ResponseEntity<List<Destinos>> obtenerTodosLosDestinos() {
		List<Destinos> destinos = destinoService.findAll();
		if (!destinos.isEmpty()) {
			return new ResponseEntity<>(destinos, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
}
