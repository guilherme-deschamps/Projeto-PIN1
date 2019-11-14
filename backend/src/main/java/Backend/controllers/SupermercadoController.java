package Backend.controllers;

import Backend.models.Usuario;
import Backend.services.UsuarioService;
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

import java.nio.file.AccessDeniedException;
import java.util.List;

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

	@Autowired
	UsuarioService usuarioService;

	@PostMapping(value = "/supermercado/cadastro/usuario/{id_usuario}")
	public ResponseEntity<?> cadastraSupermercado(
			@PathVariable(value = "id_usuario") Long idUsuario,

			@RequestParam(value = "nome") String nome,
			@RequestParam(value = "cnpj") String cnpj,
			@RequestParam(value = "telefone") String telefone,
			@RequestParam(value = "email") String email,

			@RequestParam(value = "endNumero") String endNumero,
			@RequestParam(value = "endCep") String endCep,
			@RequestParam(value = "endLogradouro") String endLogradouro,
			@RequestParam(value = "endBairro") String endBairro,
			@RequestParam(value = "endCidade") String endCidade) {
		
		try {
			Usuario usuario = usuarioService.buscaUsuarioPorId(idUsuario);
			if (usuario.getSupermercado() != null)
				throw new AccessDeniedException("Usuários podem pertencer apenas à um supermercado.");
			Endereco endereco;
			if (enderecoRepository.existsByCidadeAndBairroAndLogradouroAndNumero
					(endCidade, endBairro, endLogradouro, endNumero))
				endereco = enderecoRepository.findByCidadeAndBairroAndLogradouroAndNumero
						(endCidade, endBairro, endLogradouro, endNumero);
			else
				endereco = enderecoService.cadastraEndereco(endNumero, endCep, endLogradouro, endBairro, endCidade);
			
			Supermercado supermercado = supermercadoService.cadastraSupermercado(nome, cnpj, telefone, email, endereco);
			usuarioService.adicionaNovoSupermercado(usuario, supermercado);
			usuarioService.adicionaNovaFuncao(usuario, "gerente");
			return new ResponseEntity<>(supermercado, HttpStatus.OK);
		} catch (ObjetoJaCadastradoException | UsuarioInexistenteException | AccessDeniedException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/supermercado/{id_supermercado}/usuario")
	public ResponseEntity<?> addUsuario(
			@PathVariable(value = "id_supermercado") Long idSupermercado,

			@RequestParam(value = "nome") String nome,
			@RequestParam(value = "telefone") String telefone,
			@RequestParam(value = "email") String email,
			@RequestParam(value = "senha") String senha,
			@RequestParam(value = "funcao") String funcao) {

		try {
			Supermercado supermercado = supermercadoService.buscaSupermercadoPorId(idSupermercado);
			Usuario usuario = usuarioService.cadastraUsuario(nome, telefone, email, senha, funcao);
			usuarioService.adicionaNovoSupermercado(usuario, supermercado);
			return new ResponseEntity<>(usuario, HttpStatus.OK);
		} catch (SupermercadoInexistenteException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}
	
	@GetMapping(value = "/supermercado/{id_supermercado}")
	public ResponseEntity<?> getSupermercadoById(@PathVariable(value = "id_supermercado") Long id) {
		try {
			return new ResponseEntity<>(supermercadoService.buscaSupermercadoPorId(id), HttpStatus.OK);
		}catch (SupermercadoInexistenteException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/supermercados")
	public ResponseEntity<?> getAllSupermercados() {
		try {
			return new ResponseEntity<>(supermercadoService.buscaSupermercados(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
