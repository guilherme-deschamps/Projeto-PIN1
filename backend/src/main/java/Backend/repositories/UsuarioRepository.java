package Backend.repositories;

import org.springframework.data.repository.CrudRepository;

import Backend.models.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
	
	boolean existsByEmail(String email);
	Usuario findByEmail(String email);
}
