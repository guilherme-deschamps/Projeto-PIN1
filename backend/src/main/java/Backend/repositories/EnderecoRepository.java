package Backend.repositories;

import org.springframework.data.repository.CrudRepository;

import Backend.models.Endereco;

public interface EnderecoRepository extends CrudRepository<Endereco, Long>{

	boolean existsByCidadeAndBairroAndLogradouroAndNumero(String cidade, 
			String bairro, String logradouro, String numero);
	Endereco findByCidadeAndBairroAndLogradouroAndNumero(String cidade, 
			String bairro, String logradouro, String numero);
}
