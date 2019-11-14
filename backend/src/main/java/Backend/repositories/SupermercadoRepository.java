package Backend.repositories;

import org.springframework.data.repository.CrudRepository;

import Backend.models.Endereco;
import Backend.models.Supermercado;

public interface SupermercadoRepository extends CrudRepository<Supermercado, Long>{

	boolean existsByCnpjOrEndereco(String cnpj, Endereco endereco);
}
