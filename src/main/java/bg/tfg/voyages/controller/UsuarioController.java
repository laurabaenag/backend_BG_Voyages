package bg.tfg.voyages.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bg.tfg.voyages.model.Usuarios;
import bg.tfg.voyages.service.ReservaService;
import bg.tfg.voyages.service.UsuarioService;

/**
 * Controlador REST para gestionar usuarios. Permite realizar operaciones CRUD
 * sobre los usuarios, así como las creaciones de cookies y claves encriptadas
 * al iniciar sesión.
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/usuarios")
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private ReservaService reservaService;

	@GetMapping("/{id}")
	public ResponseEntity<Usuarios> obtenerUsuarioPorId(@PathVariable Long id) {
		Usuarios usuarios = usuarioService.findById(id).orElse(null);
		if (usuarios != null) {
			return new ResponseEntity<>(usuarios, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/inicioSesion")
	public ResponseEntity<Map<String, String>> login(@RequestBody Usuarios usuarios, HttpServletResponse response) {
		String email = usuarios.getEmail();
		String password = usuarios.getPassword();

		if (email != null && !email.isEmpty() && password != null && !password.isEmpty()) {
			if (usuarioService.validarCredenciales(email, password)) {
				String sessionId = UUID.randomUUID().toString();
				Cookie cookie = new Cookie("SESSION_ID", sessionId);
				cookie.setMaxAge(3600); // 1 hora
				cookie.setPath("/");
				response.addCookie(cookie);

				// Log para verificar la creación de la cookie
				System.out.println("Cookie creada: " + cookie.getName() + " = " + cookie.getValue());

				// Obtén el nombre del usuario por email y si es admin
				Optional<Usuarios> optionalUsuario = usuarioService.findByEmail(email);
				if (optionalUsuario.isPresent()) {
					Usuarios usuario = optionalUsuario.get();
					Map<String, String> responseBody = new HashMap<>();
					responseBody.put("nombre", usuario.getNombre());
					responseBody.put("idUsuario", usuario.getId().toString());
					responseBody.put("isAdmin", Boolean.toString(usuario.isEsAdmin()));
					responseBody.put("email", usuario.getEmail());
					responseBody.put("apellido", usuario.getApellido());

					// Log para verificar los datos del usuario
					System.out.println("Usuario encontrado: " + usuario.getNombre() + ", ID: " + usuario.getId()
							+ ", isAdmin: " + usuario.isEsAdmin());

					return new ResponseEntity<>(responseBody, HttpStatus.OK);
				} else {
					return new ResponseEntity<>(Map.of("message", "Usuario no encontrado"), HttpStatus.NOT_FOUND);
				}
			} else {
				return new ResponseEntity<>(Map.of("message", "Credenciales inválidas"), HttpStatus.UNAUTHORIZED);
			}
		} else {
			return new ResponseEntity<>(Map.of("message", "Email y password no pueden estar vacías"),
					HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/guardar")
	public ResponseEntity<Usuarios> guardarUsuario(@RequestBody Usuarios usuarios) {
		// Codifico la contraseña antes de guardar el usuario
		String encodedPassword = passwordEncoder.encode(usuarios.getPassword());
		usuarios.setPassword(encodedPassword);

		Usuarios savedUsuario = usuarioService.save(usuarios);
		return new ResponseEntity<>(savedUsuario, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Usuarios> actualizarUsuario(@PathVariable Long id, @RequestBody Usuarios usuarioDetails) {
		Usuarios usuarios = usuarioService.findById(id).orElse(null);
		if (usuarios != null) {
			usuarios.setNombre(usuarioDetails.getNombre());
			usuarios.setApellido(usuarioDetails.getApellido());
			usuarios.setEmail(usuarioDetails.getEmail());
			// Codifico la contraseña antes de actualizar el usuario
			String encodedPassword = passwordEncoder.encode(usuarioDetails.getPassword());
			usuarios.setPassword(encodedPassword);
			usuarios.setEsAdmin(usuarioDetails.isEsAdmin());

			final Usuarios updatedUsuario = usuarioService.save(usuarios);
			return new ResponseEntity<>(updatedUsuario, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> eliminarUsuario(@PathVariable Long id) {
		try {
			reservaService.deleteByUsuarioId(id);
			usuarioService.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/todos")
	public ResponseEntity<List<Usuarios>> obtenerTodosLosUsuarios() {
		List<Usuarios> usuarios = usuarioService.findAll();
		if (!usuarios.isEmpty()) {
			return new ResponseEntity<>(usuarios, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
}
