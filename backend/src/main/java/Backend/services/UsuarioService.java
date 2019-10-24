package Backend.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Backend.exceptions.ObjetoJaCadastradoException;
import Backend.exceptions.PermissaoNegadaException;
import Backend.exceptions.UsuarioInexistenteException;
import Backend.models.Usuario;
import Backend.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	public Usuario cadastraUsuario(String nome, String telefone, String email, String senha)
			throws ObjetoJaCadastradoException {

		if (usuarioRepository.existsByEmail(email))
			throw new ObjetoJaCadastradoException("Usuario Ja cadastrado com este email.");
		Usuario novoUsuario = new Usuario(nome, telefone, email, senha);
		usuarioRepository.save(novoUsuario);
		return novoUsuario;
	}

	public Usuario buscaUsuarioPorId(Long id) throws UsuarioInexistenteException {

		Optional opUsuario = usuarioRepository.findById(id);
		if (opUsuario.isPresent())
			return (Usuario) opUsuario.get();
		throw new UsuarioInexistenteException("Usuario não encontrado com o id especificado.");
	}

	public Usuario verificaLogin(String email, String senha)
			throws UsuarioInexistenteException, PermissaoNegadaException {

		Usuario usuario = usuarioRepository.findByEmail(email);
		if (usuario == null)
			throw new UsuarioInexistenteException("Usuario não encontrado com o id especificado.");
		if (usuario.getSenha().equals(senha))
			return usuario;
		else
			throw new PermissaoNegadaException("As informações de senha e email estão incorretas.");
	}
}
