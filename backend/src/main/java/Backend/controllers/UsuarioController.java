package Backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Backend.exceptions.ObjetoJaCadastradoException;
import Backend.exceptions.PermissaoNegadaException;
import Backend.exceptions.UsuarioInexistenteException;
import Backend.models.Usuario;
import Backend.services.UsuarioService;

@RestController
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;
	
	@PostMapping(value = "/usuario/cadastro")
	public ResponseEntity<?> cadastraUsuario(
			@RequestParam(value = "nome", required = false) String nome,
			@RequestParam(value = "telefone", required = false) String telefone,
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "senha", required = false) String senha) {
		try {
			if (nome == null)
				throw new NullPointerException("O nome deve ser informado");
			if (telefone == null)
				throw new NullPointerException("O telefone deve ser informado");
			if (email == null)
				throw new NullPointerException("O email deve ser informado");
			if (senha == null)
				throw new NullPointerException("A senha deve ser informado");

			Usuario novoUsuario = usuarioService.cadastraUsuario(nome, telefone, email, senha);
			return new ResponseEntity<>(novoUsuario, HttpStatus.OK);
		} catch (ObjetoJaCadastradoException | NullPointerException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "/usuario/{id_usuario}")
	public ResponseEntity<?> getUsuarioById(
			@PathVariable(value = "id_usuario", required = false) Long id) {
		try {
			return new ResponseEntity<>(usuarioService.buscaUsuarioPorId(id), HttpStatus.OK);
		}catch (UsuarioInexistenteException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "/usuario/login")
	public ResponseEntity<?> verificaLogin(
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "senha", required = false) String senha) {
		try {
			if (email == null)
				throw new NullPointerException("O email deve ser informado");
			if (senha == null)
				throw new NullPointerException("A senha deve ser informado");

			Usuario usuario = usuarioService.verificaLogin(email, senha);
			return new ResponseEntity<>(usuario, HttpStatus.OK);
		} catch (PermissaoNegadaException | UsuarioInexistenteException | NullPointerException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}