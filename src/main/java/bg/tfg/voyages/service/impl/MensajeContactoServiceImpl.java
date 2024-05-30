package bg.tfg.voyages.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bg.tfg.voyages.dao.MensajeContactoDao;
import bg.tfg.voyages.model.MensajesContacto;
import bg.tfg.voyages.service.MensajeContactoService;

@Service
public class MensajeContactoServiceImpl implements MensajeContactoService {

	@Autowired
	private MensajeContactoDao mensajeContactoDAO;

	@Override
	public List<MensajesContacto> obtenerTodosLosMensajes() {
		return mensajeContactoDAO.findAll();
	}

	@Override
	public MensajesContacto save(MensajesContacto entity) {
		return mensajeContactoDAO.save(entity);
	}

}
