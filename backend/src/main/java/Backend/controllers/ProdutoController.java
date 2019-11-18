package Backend.controllers;

import Backend.exceptions.CategoriaInexistenteException;
import Backend.models.Categoria;
import Backend.services.CategoriaService;
import Backend.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    @Autowired
    CategoriaService categoriaService;

    @PostMapping(value = "/produto")
    public ResponseEntity<?> cadastraProduto(@RequestParam(value = "id_categoria") Long idCategoria,
                                             @RequestParam(value = "nome") String nome,
                                             @RequestParam(value = "marca") String marca,
                                             @RequestParam(value = "preco") double preco,
                                             @RequestParam(value = "unidade_medida") String unidMedida) {

        try {
            Categoria categoria = categoriaService.buscaCategoriaPorId(idCategoria);
            return new ResponseEntity<>(produtoService.cadastraProduto(nome, marca, preco, unidMedida, categoria), HttpStatus.OK);
        } catch (Exception | CategoriaInexistenteException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

//    @GetMapping(value = "/produtos/categoria/{id_categoria}")
//    public ResponseEntity<?> getProdutosByCategoria
}
