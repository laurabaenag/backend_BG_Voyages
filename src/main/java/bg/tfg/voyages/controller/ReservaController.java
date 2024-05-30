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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bg.tfg.voyages.model.Actividades;
import bg.tfg.voyages.model.Reservas;
import bg.tfg.voyages.model.Usuarios;
import bg.tfg.voyages.service.ReservaService;
import bg.tfg.voyages.service.UsuarioService;

/**
 * Controlador REST para gestionar las reservas realizadas. Permite realizar
 * operaciones CRUD sobre estas.
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/reservas")
public class ReservaController {
	@Autowired
	private ReservaService reservaService;

	@PostMapping("/crear")
	public ResponseEntity<Reservas> createReserva(@RequestBody Reservas reserva) {
		if (reserva.getUsuarios() == null || reserva.getUsuarios().getId() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		if (reserva.getActividades() == null || reserva.getActividades().getIdActividad() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Reservas nuevaReserva = reservaService.saveReservas(reserva);
		return new ResponseEntity<>(nuevaReserva, HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> eliminarReserva(@PathVariable Long id) {
		try {
			reservaService.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/todas")
	public ResponseEntity<List<Reservas>> obtenerTodasLasReservas() {
		List<Reservas> reservas = reservaService.findAll();
		if (!reservas.isEmpty()) {
			return new ResponseEntity<>(reservas, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/mostrar/{id}")
	public ResponseEntity<List<Reservas>> findReservasByUsuario(@PathVariable Long id) {
		List<Reservas> reservas = reservaService.findByUsuarioId(id);
		if (reservas == null || reservas.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(reservas);
	}

}
