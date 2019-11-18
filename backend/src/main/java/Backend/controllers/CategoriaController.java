package Backend.controllers;

import Backend.models.Categoria;
import Backend.models.Produto;
import Backend.services.CategoriaService;
import Backend.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;

    @Autowired
    ProdutoService produtoService;

    @PostMapping(value = "/categoria")
    public ResponseEntity<?> cadastraCategoria(@RequestParam(value = "nome") String nome) {

        try {
            return new ResponseEntity<>(categoriaService.cadastraCategoria(nome), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/categoria/produto/{id_produto}")
    public ResponseEntity<?> adicionaProduto(@PathVariable(value = "id_produto") Long idProduto) {

        try {
            Produto produto = produtoService.buscaProdutoPorId(idProduto);
            categoriaService.adicionaProduto(produto);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
