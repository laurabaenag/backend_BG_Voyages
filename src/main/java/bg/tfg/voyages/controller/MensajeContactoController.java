package bg.tfg.voyages.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bg.tfg.voyages.model.MensajesContacto;
import bg.tfg.voyages.model.Usuarios;
import bg.tfg.voyages.service.MensajeContactoService;
import bg.tfg.voyages.service.UsuarioService;
import bg.tfg.voyages.service.impl.UsuarioServiceImpl;

/**
 * Controlador REST para gestionar los mensajes enviados en la página de
 * contacto. Permite realizar operaciones CRUD sobre los estos mensajes.
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/mensajes")
public class MensajeContactoController {
	@Autowired
	private MensajeContactoService mensajesService;

	@PostMapping("/guardar")
	public ResponseEntity<MensajesContacto> guardarMensaje(@RequestBody MensajesContacto mensajes) {
		MensajesContacto savedMensaje = mensajesService.save(mensajes);
		return new ResponseEntity<>(savedMensaje, HttpStatus.CREATED);
	}

	@GetMapping("/todos")
	public ResponseEntity<List<MensajesContacto>> obtenerTodosLosMensajes() {
		List<MensajesContacto> mensajes = mensajesService.obtenerTodosLosMensajes();
		if (!mensajes.isEmpty()) {
			return new ResponseEntity<>(mensajes, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
}
