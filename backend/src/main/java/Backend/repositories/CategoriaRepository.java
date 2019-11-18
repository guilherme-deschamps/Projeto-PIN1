package Backend.repositories;

import Backend.models.Categoria;
import org.springframework.data.repository.CrudRepository;

public interface CategoriaRepository extends CrudRepository<Categoria, Long> {

    boolean existsByNome(String nome);
}
