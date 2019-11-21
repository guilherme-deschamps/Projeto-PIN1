package Backend.repositories;

import Backend.models.Carrinho;
import Backend.models.Supermercado;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CarrinhoRepository extends CrudRepository<Carrinho, Long> {
    List<Carrinho> getAllBySupermercado(Supermercado supermercado);
}
