package Backend.controllers;

import Backend.exceptions.CarrinhoInexistenteException;
import Backend.exceptions.MensagemErro;
import Backend.exceptions.SupermercadoInexistenteException;
import Backend.exceptions.UsuarioInexistenteException;
import Backend.models.*;
import Backend.repositories.EnderecoRepository;
import Backend.services.CarrinhoService;
import Backend.services.EnderecoService;
import Backend.services.SupermercadoService;
import Backend.services.UsuarioService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.rmi.AccessException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class CarrinhoController {

    @Autowired
    CarrinhoService carrinhoService;

    @Autowired
    SupermercadoService supermercadoService;

    @Autowired
    EnderecoRepository enderecoRepository;

    @Autowired
    EnderecoService enderecoService;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping(value = "/carrinho/cadastro")
    public ResponseEntity<?> cadastraCarrinho(
            @RequestParam(value = "forma_pagamento") String formaPagamento,
            @RequestParam(value = "valor_total") double valorTotal,

            @RequestParam(value = "id_usuario") Long idUsuario,
            @RequestParam(value = "id_supermercado") Long idSupermercado,
            @RequestParam(value = "produtos") String jsonProdutos,

            @RequestParam(value = "end_numero") String endNumero,
            @RequestParam(value = "end_cep") String endCep,
            @RequestParam(value = "end_logradouro") String endLogradouro,
            @RequestParam(value = "end_bairro") String endBairro,
            @RequestParam(value = "end_cidade") String endCidade) {

        try {
            Produto[] listaProdutos = objectMapper.readValue(jsonProdutos, Produto[].class);
            List<Produto> produtos = new ArrayList<>(Arrays.asList(listaProdutos));

            Usuario usuario = usuarioService.buscaUsuarioPorId(idUsuario);
            Supermercado supermercado = supermercadoService.buscaSupermercadoPorId(idSupermercado);
            Endereco endereco;
            if (enderecoRepository.existsByCidadeAndBairroAndLogradouroAndNumero
                    (endCidade, endBairro, endLogradouro, endNumero))
                endereco = enderecoRepository.findByCidadeAndBairroAndLogradouroAndNumero
                        (endCidade, endBairro, endLogradouro, endNumero);
            else
                endereco = enderecoService.cadastraEndereco(endNumero, endCep, endLogradouro, endBairro, endCidade);

            return new ResponseEntity<>(carrinhoService.cadastraCarrinho
                    (formaPagamento, valorTotal, Carrinho.CRIADO, usuario, supermercado, endereco, produtos), HttpStatus.OK);
        } catch (SupermercadoInexistenteException | UsuarioInexistenteException | IOException e) {
            return new ResponseEntity<>(new MensagemErro(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/carrinho/{id_carrinho}/situacao")
    public ResponseEntity<?> avancaCarrinho(
            @PathVariable(value = "id_carrinho") Long idCarrinho) {

        try {
            Carrinho carrinho = carrinhoService.buscaCarrinhoPorId(idCarrinho);
            return new ResponseEntity<>(carrinhoService.avancaCarrinho(carrinho), HttpStatus.OK);
        } catch (CarrinhoInexistenteException | AccessException e) {
            return new ResponseEntity<>(new MensagemErro(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/carrinho/supermercado/{id_supermercado}")
    public ResponseEntity<?> getCarrinhosBySupermercado(@PathVariable(value = "id_supermercado") Long idSupermercado) {
        try {
            return new ResponseEntity<>(carrinhoService.buscaCarrinhoPorSupermercado(idSupermercado), HttpStatus.OK);
        } catch (SupermercadoInexistenteException e) {
            return new ResponseEntity<>(new MensagemErro(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
