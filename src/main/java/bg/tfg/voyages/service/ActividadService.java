package bg.tfg.voyages.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import bg.tfg.voyages.model.Actividades;

public interface ActividadService {
	List<Actividades> findByFechaActividad(LocalDate fecha);

	List<Actividades> findAll();

	Actividades getById(Long id);

	void deleteById(Long id);

	void delete(Actividades entity);

	Actividades save(Actividades entity);

	Optional<Actividades> findById(Long id);

	List<Actividades> getActividadesByNombreDestinoAndFecha(String nombre_destino, LocalDate fecha);

	List<Actividades> getActividadesByDestino(String nombre_destino);

	List<Actividades> getActividadesByIdDestino(Long idDestino);

	void deleteByDestinoId(Long destinoId);
}
