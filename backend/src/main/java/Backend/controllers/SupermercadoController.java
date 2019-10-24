package Backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Backend.exceptions.ObjetoJaCadastradoException;
import Backend.exceptions.SupermercadoInexistenteException;
import Backend.exceptions.UsuarioInexistenteException;
import Backend.models.Endereco;
import Backend.models.Supermercado;
import Backend.repositories.EnderecoRepository;
import Backend.services.EnderecoService;
import Backend.services.SupermercadoService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class SupermercadoController {
	
	@Autowired
	SupermercadoService supermercadoService;
	
	@Autowired
	EnderecoRepository enderecoRepository;
	
	@Autowired
	EnderecoService enderecoService;

	@PostMapping(value = "/supermercado/cadastro")
	public ResponseEntity<?> cadastraSupermercado(
			@RequestParam(value = "nome", required = false) String nome,
			@RequestParam(value = "cnoj", required = false) String cnpj,
			@RequestParam(value = "telefone", required = false) String telefone,
			@RequestParam(value = "email", required = false) String email,

			@RequestParam(value = "endNumero", required = false) String endNumero,
			@RequestParam(value = "endCep", required = false) String endCep,
			@RequestParam(value = "endLogradouro", required = false) String endLogradouro,
			@RequestParam(value = "endBairro", required = false) String endBairro,
			@RequestParam(value = "endCidade", required = false) String endCidade) {
		
		try {
			Endereco endereco = null;
			if (enderecoRepository.existsByCidadeAndBairroAndLogradouroAndNumero
					(endCidade, endBairro, endLogradouro, endNumero))
				endereco = enderecoRepository.findByCidadeAndBairroAndLogradouroAndNumero
						(endCidade, endBairro, endLogradouro, endNumero);
			else
				endereco = enderecoService.cadastraEndereco(endNumero, 
						endCep, endLogradouro, endBairro, endCidade);
			
			Supermercado supermercado = supermercadoService.cadastraSupermercado
					(nome, cnpj, telefone, email, endereco);
			return new ResponseEntity<>(supermercado, HttpStatus.OK);
		} catch (ObjetoJaCadastradoException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "/supermercado/{id_supermercado}")
	public ResponseEntity<?> getUsuarioById(
			@PathVariable(value = "id_usuario", required = false) Long id) {
		try {
			return new ResponseEntity<>(supermercadoService.buscaSupermercadoPorId(id), HttpStatus.OK);
		}catch (SupermercadoInexistenteException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
