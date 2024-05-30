package bg.tfg.voyages.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import bg.tfg.voyages.dao.ReservaDao;
import bg.tfg.voyages.model.Reservas;
import bg.tfg.voyages.service.ReservaService;

@Service
public class ReservaServiceImpl implements ReservaService {

	@Autowired
	private ReservaDao reservaDAO;

	@Override
	public Reservas saveReservas(Reservas reserva) {
		return reservaDAO.save(reserva);
	}

	@Override
	public void deleteByActividadId(Long actividadId) {
		reservaDAO.deleteByActividadId(actividadId);
	}

	@Override
	public void deleteById(Long id) {
		reservaDAO.deleteById(id);
		
	}

	@Override
	public List<Reservas> findByUsuarioId(Long usuarioId) {
		return reservaDAO.findByUsuarioId(usuarioId);
	}

	@Override
	public List<Reservas> findAll() {
		return reservaDAO.findAll();
	}

	@Override
	public void deleteByUsuarioId(Long usuarioId) {
		reservaDAO.deleteByUsuarioId(usuarioId);
		
	}

}
