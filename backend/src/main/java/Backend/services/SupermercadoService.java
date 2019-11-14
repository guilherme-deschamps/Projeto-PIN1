package Backend.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Backend.exceptions.ObjetoJaCadastradoException;
import Backend.exceptions.SupermercadoInexistenteException;
import Backend.models.Endereco;
import Backend.models.Supermercado;
import Backend.repositories.SupermercadoRepository;

@Service
public class SupermercadoService {

	@Autowired
	EnderecoService enderecoService;

	@Autowired
	SupermercadoRepository supermercadoRepository;

	public Supermercado cadastraSupermercado(String nome, String cnpj, String telefone, 
			String email, Endereco endereco) {
		
		if (supermercadoRepository.existsByCnpjOrEndereco(cnpj, endereco))
			throw new ObjetoJaCadastradoException("Supermercado ja cadastrado com esse cnpj ou endereço");
		Supermercado novoSupermercado = new Supermercado(nome, cnpj, telefone, email, endereco);
		supermercadoRepository.save(novoSupermercado);
		return novoSupermercado;
	}

	public Supermercado buscaSupermercadoPorId(Long id) throws SupermercadoInexistenteException {
		
		Optional opSupermercado = supermercadoRepository.findById(id);
		if (opSupermercado.isPresent())
			return (Supermercado) opSupermercado.get();
		throw new SupermercadoInexistenteException("Usuario n�o encontrado com o id especificado.");
	}

	public Iterable<Supermercado> buscaSupermercados() {

		return supermercadoRepository.findAll();
	}
}
