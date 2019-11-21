package Backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import Backend.exceptions.ObjetoJaCadastradoException;
import Backend.exceptions.UsuarioInexistenteException;
import Backend.models.Usuario;
import Backend.services.UsuarioService;

import java.nio.file.AccessDeniedException;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping(value = "/usuario/cadastro")
    public ResponseEntity<?> cadastraUsuario(
            @RequestParam(value = "nome") String nome,
            @RequestParam(value = "telefone") String telefone,
            @RequestParam(value = "email") String email,
            @RequestParam(value = "senha") String senha) {
        try {
            Usuario novoUsuario = usuarioService.cadastraUsuario(nome, telefone, email, senha, "usuario");
            return new ResponseEntity<>(novoUsuario, HttpStatus.OK);
        } catch (ObjetoJaCadastradoException | NullPointerException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/usuario/login")
    public ResponseEntity<?> verificaLogin(
            @RequestParam(value = "email") String email,
            @RequestParam(value = "senha") String senha) {
        try {

            Usuario usuario = usuarioService.verificaLogin(email, senha);
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        } catch (AccessDeniedException | UsuarioInexistenteException | NullPointerException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/usuario/{id_usuario}")
    public ResponseEntity<?> editaUsuario(
            @PathVariable(value = "id_usuario") Long idUsuario,
            @RequestParam(value = "nome") String nome,
            @RequestParam(value = "telefone") String telefone,
            @RequestParam(value = "email") String email) {

        try {
            Usuario usuario = usuarioService.buscaUsuarioPorId(idUsuario);
            usuario = usuarioService.editaUsuario(usuario, nome, telefone, email);
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        }catch (UsuarioInexistenteException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/usuario/novasenha/{id_usuario}")
    public ResponseEntity<?> alterarSenha(
            @PathVariable(value = "id_usuario") Long idUsuario,
            @RequestParam(value = "senha_nova") String senhaNova,
            @RequestParam(value = "senha_antiga") String senhaAntiga) {

        try {
            if (idUsuario == null)
                throw new NullPointerException("O id do usuário deve ser informado");
            if (senhaNova == null)
                throw new NullPointerException("A senha nova deve ser informada");
            if (senhaAntiga == null)
                throw new NullPointerException("A senha antiga deve ser informada");

            Usuario usuario = usuarioService.buscaUsuarioPorId(idUsuario);
            usuario = usuarioService.alteraSenha(usuario, senhaNova, senhaAntiga);
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        } catch (NullPointerException | UsuarioInexistenteException | AccessDeniedException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

//    @PutMapping(value = "/usuario/{id_usuario/endereco")
//    public ResponseEntity<?> adicionaEndereco() {
//
//    }

    @GetMapping(value = "/usuario/{id_usuario}")
    public ResponseEntity<?> getUsuarioById(
            @PathVariable(value = "id_usuario") Long id) {
        try {
            return new ResponseEntity<>(usuarioService.buscaUsuarioPorId(id), HttpStatus.OK);
        } catch (UsuarioInexistenteException e) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}