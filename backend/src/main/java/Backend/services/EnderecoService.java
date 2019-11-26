package Backend.services;


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
			throw new ObjetoJaCadastradoException("Endereço ja cadastrado com estas informações");
		Endereco novoEndereco = new Endereco(numero, cep, logradouro, bairro, cidade);
		enderecoRepository.save(novoEndereco);
		return novoEndereco;
	}
	
	
}
