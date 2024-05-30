package bg.tfg.voyages.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import bg.tfg.voyages.model.Usuarios;

public interface UsuarioService {
	void flush();

	<S extends Usuarios> S saveAndFlush(S entity);

	<S extends Usuarios> List<S> saveAllAndFlush(Iterable<S> entities);

	void deleteAllInBatch(Iterable<Usuarios> entities);

	void deleteAllByIdInBatch(Iterable<Long> ids);

	void deleteAllInBatch();

	Usuarios getOne(Long id);

	Usuarios getById(Long id);

	Usuarios getReferenceById(Long id);

	<S extends Usuarios> List<S> findAll(Example<S> example);

	<S extends Usuarios> List<S> findAll(Example<S> example, Sort sort);

	<S extends Usuarios> List<S> saveAll(Iterable<S> entities);

	List<Usuarios> findAll();

	List<Usuarios> findAllById(Iterable<Long> ids);

	Usuarios save(Usuarios entity);

	Optional<Usuarios> findById(Long id);

	boolean existsById(Long id);

	long count();

	void deleteById(Long id);

	void delete(Usuarios entity);

	void deleteAllById(Iterable<? extends Long> ids);

	void deleteAll(Iterable<? extends Usuarios> entities);

	void deleteAll();

	List<Usuarios> findAll(Sort sort);

	Page<Usuarios> findAll(Pageable pageable);

	boolean validarCredenciales(String email, String password);

	Optional<String> getUserNameByEmail(String email);
	
	Optional<Usuarios> findByEmail(String email);
}
