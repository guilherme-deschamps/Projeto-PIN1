package Backend.repositories;

import Backend.models.Categoria;
import Backend.models.Produto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProdutoRepository extends CrudRepository<Produto, Long> {
    List<Produto> getAllByCategoria(Categoria categoria);
}
