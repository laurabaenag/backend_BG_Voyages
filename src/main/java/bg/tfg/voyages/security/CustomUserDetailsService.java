package bg.tfg.voyages.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import bg.tfg.voyages.dao.UsuarioDao;
import bg.tfg.voyages.model.Usuarios;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UsuarioDao usuarioDAO;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuarios usuario = usuarioDAO.findByEmail(email)
        		.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con email: " + email));
        
        return buildUserDetails(usuario);
    }

    private UserDetails buildUserDetails(Usuarios usuario) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(usuario.isEsAdmin() ? "ADMIN" : "USER"));

        return new org.springframework.security.core.userdetails.User(
            usuario.getEmail(), 
            usuario.getPassword(), 
            authorities
        );
    }
}
