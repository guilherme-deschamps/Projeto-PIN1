package Backend.services;

import java.nio.file.AccessDeniedException;
import java.util.Optional;

import Backend.models.Supermercado;
import Backend.repositories.SupermercadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Backend.exceptions.ObjetoJaCadastradoException;
import Backend.exceptions.UsuarioInexistenteException;
import Backend.models.Usuario;
import Backend.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	SupermercadoRepository supermercadoRepository;
	
	public Usuario cadastraUsuario(String nome, String telefone, String email, String senha, String funcao)
			throws ObjetoJaCadastradoException {

		if (usuarioRepository.existsByEmail(email))
			throw new ObjetoJaCadastradoException("Usuario Ja cadastrado com este email.");
		Usuario novoUsuario = new Usuario(nome, telefone, email, senha);
		novoUsuario.setFuncao(funcao);
		usuarioRepository.save(novoUsuario);
		return novoUsuario;
	}

	public Usuario buscaUsuarioPorId(Long idUsuario) throws UsuarioInexistenteException {

		Optional opUsuario = usuarioRepository.findById(idUsuario);
		if (opUsuario.isPresent())
			return (Usuario) opUsuario.get();
		throw new UsuarioInexistenteException("Usuario não encontrado com o id especificado.");
	}

	public Usuario verificaLogin(String email, String senha)
			throws UsuarioInexistenteException, AccessDeniedException {

		Usuario usuario = usuarioRepository.findByEmail(email);
		if (usuario == null)
			throw new UsuarioInexistenteException("Usuario não encontrado com o id especificado.");
		if (usuario.getSenha().equals(senha))
			return usuario;
		else throw new AccessDeniedException("As informações de senha e email estão incorretas.");
	}

	public Usuario editaUsuario(Usuario usuario, String nome, String telefone, String email) {

		if (nome != null)
			usuario.setNome(nome);
		if (telefone != null)
			usuario.setSenha(telefone);
		if (email != null)
			usuario.setEmail(email);
		usuarioRepository.save(usuario);
		return usuario;
	}

	public Usuario alteraSenha(Usuario usuario, String senhaNova, String senhaAntiga) throws AccessDeniedException {

		if (senhaAntiga.equals(senhaNova))
			usuario.setSenha(senhaNova);
		else throw new AccessDeniedException("Acesso negado, senha nova deve ser igual à senha antiga.");
		usuarioRepository.save(usuario);
		return usuario;
	}

	public void adicionaNovoSupermercado(Usuario usuario, Supermercado supermercado) {
		usuario.setSupermercado(supermercado);
		supermercado.addUsuario(usuario);
		usuarioRepository.save(usuario);
		supermercadoRepository.save(supermercado);
	}

	public void adicionaNovaFuncao(Usuario usuario, String novaFuncao) {
		usuario.setFuncao(novaFuncao);
		usuarioRepository.save(usuario);
	}
}
