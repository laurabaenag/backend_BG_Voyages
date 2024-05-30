package bg.tfg.voyages.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import bg.tfg.voyages.model.Actividades;

public interface ActividadDao extends JpaRepository<Actividades, Long> {
	@Query("SELECT a FROM Actividades a WHERE a.fechaActividad = :fecha")
	List<Actividades> findByFechaActividad(LocalDate fecha);

	@Query("SELECT a FROM Actividades a WHERE a.destino.id_destino = :destinoId AND a.fechaActividad = :fecha")
	List<Actividades> findByDestinoIdAndFecha(@Param("destinoId") Long destinoId, @Param("fecha") LocalDate fecha);

	@Query("SELECT a FROM Actividades a WHERE a.destino.id_destino = :destinoId")
	List<Actividades> findByDestino(@Param("destinoId") Long destinoId);

	@Modifying
    @Transactional
	@Query("DELETE FROM Actividades a WHERE a.destino.id_destino = :destinoId")
	void deleteByDestinoId(@Param("destinoId") Long destinoId);
}
