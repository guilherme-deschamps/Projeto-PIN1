package Backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Backend.exceptions.ObjetoJaCadastradoException;
import Backend.models.Endereco;
import Backend.repositories.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	EnderecoRepository enderecoRepository;
	
	public Endereco cadastraEndereco(String numero, String cep,
			String logradouro, String bairro, String cidade) throws ObjetoJaCadastradoException {
	
		if (enderecoRepository.existsByCidadeAndBairroAndLogradouroAndNumero
				(cidade, bairro, logradouro, numero)) 
			throw new ObjetoJaCadastradoException("Endere�o ja cadastrado com estas"
					+ "informa��es");
		Endereco novoEndereco = new Endereco(numero, cep, logradouro, bairro, cidade);
		enderecoRepository.save(novoEndereco);
		return novoEndereco;
	}
	
	
}
