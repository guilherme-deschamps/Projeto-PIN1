package Backend.controllers;

import Backend.exceptions.MensagemErro;
import Backend.models.Usuario;
import Backend.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import Backend.exceptions.ObjetoJaCadastradoException;
import Backend.exceptions.SupermercadoInexistenteException;
import Backend.exceptions.UsuarioInexistenteException;
import Backend.models.Endereco;
import Backend.models.Supermercado;
import Backend.repositories.EnderecoRepository;
import Backend.services.EnderecoService;
import Backend.services.SupermercadoService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.AccessDeniedException;

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
			@RequestParam(value = "img_supermercado") MultipartFile imgSupermercado,

			@RequestParam(value = "end_numero") String endNumero,
			@RequestParam(value = "end_cep") String endCep,
			@RequestParam(value = "end_logradouro") String endLogradouro,
			@RequestParam(value = "end_bairro") String endBairro,
			@RequestParam(value = "end_cidade") String endCidade) {
		
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
			
			Supermercado supermercado = supermercadoService.cadastraSupermercado(nome, cnpj, telefone, email, endereco,
					imgSupermercado);
			usuarioService.adicionaNovoSupermercado(usuario, supermercado);
			usuarioService.adicionaNovaFuncao(usuario, "gerente");
			return new ResponseEntity<>(supermercado, HttpStatus.OK);
		} catch (ObjetoJaCadastradoException | UsuarioInexistenteException | IOException e) {
			return new ResponseEntity<>(new MensagemErro(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping(value = "/supermercado/{id_supermercado}/usuario")
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
			return new ResponseEntity<>(new MensagemErro(e.getMessage()), HttpStatus.BAD_REQUEST);
		}

	}
	
	@GetMapping(value = "/supermercado/{id_supermercado}")
	public ResponseEntity<?> getSupermercadoById(@PathVariable(value = "id_supermercado") Long id) {
		try {
			return new ResponseEntity<>(supermercadoService.buscaSupermercadoPorId(id), HttpStatus.OK);
		}catch (SupermercadoInexistenteException e) {
			return new ResponseEntity<>(new MensagemErro(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/supermercados")
	public ResponseEntity<?> getAllSupermercados() {
		try {
			return new ResponseEntity<>(supermercadoService.buscaSupermercados(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new MensagemErro(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
}
