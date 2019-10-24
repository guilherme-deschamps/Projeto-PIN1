package Backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Backend.models.Endereco;
import Backend.models.Supermercado;

@Service
public class SupermercadoService {

	@Autowired
	EnderecoService enderecoService;
	
	public Supermercado cadastraSupermercado(String nome, String cnpj, String telefone, 
			String email, Endereco endereco) {
		
		
		return null;
	}
}
