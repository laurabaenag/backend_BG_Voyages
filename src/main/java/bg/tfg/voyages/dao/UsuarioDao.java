package bg.tfg.voyages.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import bg.tfg.voyages.model.Usuarios;

public interface UsuarioDao extends JpaRepository<Usuarios, Long> {
	Optional<Usuarios> findByEmail(String email);
//	Optional<Usuarios> findByEmailAndPassword(String email, String password);
}
