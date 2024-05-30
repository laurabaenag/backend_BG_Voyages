package bg.tfg.voyages.service;

import java.util.List;
import java.util.Optional;

import bg.tfg.voyages.model.Destinos;

public interface DestinoService {
//	List<Destinos> findByNombreContainingIgnoreCase(String nombre);

	List<Destinos> findAll();

	List<Destinos> findByProvinciaContainingIgnoreCase(String provincia);

	List<Destinos> findByPaisContainingIgnoreCase(String pais);

	Destinos getById(Long id);

	void deleteById(Long id);

	void delete(Destinos entity);

	Destinos save(Destinos entity);

	Optional<Destinos> findById(Long id);
}
