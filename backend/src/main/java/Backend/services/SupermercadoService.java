package Backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Backend.exceptions.ObjetoJaCadastradoException;
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
		
		if (supermercadoRepository.existsByCnpjAndEndereco(cnpj, endereco))
			throw new ObjetoJaCadastradoException("Supermercado ja cadastrado com esse cnpj"
					+ "ou endereço");
		Supermercado novoSupermercado = new Supermercado(nome, cnpj, telefone, email, endereco);
		supermercadoRepository.save(novoSupermercado);
		return novoSupermercado;
	}
}
