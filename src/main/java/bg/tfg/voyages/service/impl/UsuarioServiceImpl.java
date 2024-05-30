package bg.tfg.voyages.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import bg.tfg.voyages.dao.UsuarioDao;
import bg.tfg.voyages.model.Usuarios;
import bg.tfg.voyages.security.CustomUserDetailsService;
import bg.tfg.voyages.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioDao usuarioDAO;

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void flush() {
		usuarioDAO.flush();
	}

	@Override
	public <S extends Usuarios> S saveAndFlush(S entity) {
		return usuarioDAO.saveAndFlush(entity);
	}

	@Override
	public <S extends Usuarios> List<S> saveAllAndFlush(Iterable<S> entities) {
		return usuarioDAO.saveAllAndFlush(entities);
	}

	@Override
	public void deleteAllInBatch(Iterable<Usuarios> entities) {
		usuarioDAO.deleteAllInBatch(entities);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		usuarioDAO.deleteAllByIdInBatch(ids);
	}

	@Override
	public void deleteAllInBatch() {
		usuarioDAO.deleteAllInBatch();
	}

	@Override
	public Usuarios getOne(Long id) {
		return usuarioDAO.getOne(id);
	}

	@Override
	public Usuarios getById(Long id) {
		return usuarioDAO.getById(id);
	}

	@Override
	public Usuarios getReferenceById(Long id) {
		return usuarioDAO.getReferenceById(id);
	}

	@Override
	public <S extends Usuarios> List<S> findAll(Example<S> example) {
		return usuarioDAO.findAll(example);
	}

	@Override
	public <S extends Usuarios> List<S> findAll(Example<S> example, Sort sort) {
		return usuarioDAO.findAll(example, sort);
	}

	@Override
	public <S extends Usuarios> List<S> saveAll(Iterable<S> entities) {
		return usuarioDAO.saveAll(entities);
	}

	@Override
	public List<Usuarios> findAll() {
		return usuarioDAO.findAll();
	}

	@Override
	public List<Usuarios> findAllById(Iterable<Long> ids) {
		return usuarioDAO.findAllById(ids);
	}

	public Usuarios save(Usuarios entity) {
		return usuarioDAO.save(entity);
	}

	@Override
	public Optional<Usuarios> findById(Long id) {
		return usuarioDAO.findById(id);
	}

	@Override
	public boolean existsById(Long id) {
		return usuarioDAO.existsById(id);
	}

	@Override
	public long count() {
		return usuarioDAO.count();
	}

	@Override
	public void deleteById(Long id) {
		usuarioDAO.deleteById(id);
	}

	@Override
	public void delete(Usuarios entity) {
		usuarioDAO.delete(entity);
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		usuarioDAO.deleteAllById(ids);
	}

	@Override
	public void deleteAll(Iterable<? extends Usuarios> entities) {
		usuarioDAO.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		usuarioDAO.deleteAll();
	}

	@Override
	public List<Usuarios> findAll(Sort sort) {
		return usuarioDAO.findAll(sort);
	}

	@Override
	public Page<Usuarios> findAll(Pageable pageable) {
		return usuarioDAO.findAll(pageable);
	}

	@Override
	public boolean validarCredenciales(String email, String password) {
//		try {
//			UserDetails userDetails = userDetailsService.loadUserByUsername(email);
////			passwordEncoder.encode("1313");
////			passwordEncoder.matches("1313", "$2a$10$XiPtgVLtvc9H7lBc9EZ/hOcp7WoHQ4GCy/F5oe7bLVqjD1Z6616Zi");
//			boolean matches = passwordEncoder.matches(password, userDetails.getPassword());
//			if (matches) {
//				System.out.println("La contraseña coincide para el usuario: " + email);
//			} else {
//				System.out.println("La contraseña no coincide para el usuario: " + email);
//			}
//			return matches;
//		} catch (UsernameNotFoundException e) {
//			System.out.println("Usuario no encontrado: " + email);
//			return false;
//		}
		try {
			UserDetails userDetails = userDetailsService.loadUserByUsername(email);
			boolean matches = passwordEncoder.matches(password, userDetails.getPassword());
			if (matches) {
				System.out.println("La contraseña coincide para el usuario: " + email);
			} else {
				System.out.println("La contraseña no coincide para el usuario: " + email);
			}
			return matches;
		} catch (UsernameNotFoundException e) {
			System.out.println("Usuario no encontrado: " + email);
			return false;
		}

	}

	@Override
	public Optional<String> getUserNameByEmail(String email) {
		return usuarioDAO.findByEmail(email).map(Usuarios::getNombre);
	}

	@Override
	public Optional<Usuarios> findByEmail(String email) {
		return usuarioDAO.findByEmail(email);
	}


}
