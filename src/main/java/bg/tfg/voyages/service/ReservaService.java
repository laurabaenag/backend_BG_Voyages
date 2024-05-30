package bg.tfg.voyages.service;

import java.util.List;

import org.springframework.data.domain.Sort;

import bg.tfg.voyages.model.Reservas;

public interface ReservaService {
	Reservas saveReservas(Reservas reserva);

	void deleteByActividadId(Long actividadId);

	void deleteById(Long id);

	List<Reservas> findByUsuarioId(Long usuarioId);

	List<Reservas> findAll();
	
	void deleteByUsuarioId(Long usuarioId);
}
