package Backend.services;

import Backend.exceptions.CategoriaInexistenteException;
import Backend.exceptions.ObjetoJaCadastradoException;
import Backend.exceptions.ProdutoInexistenteException;
import Backend.models.Categoria;
import Backend.models.Produto;
import Backend.models.Usuario;
import Backend.repositories.CategoriaRepository;
import Backend.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    public Categoria cadastraCategoria(String nome) {
        Categoria categoria = new Categoria(nome);
        categoriaRepository.save(categoria);
        return categoria;
    }

    public Categoria buscaCategoriaPorId(Long idCategoria) throws CategoriaInexistenteException {

        Optional opCategoria = categoriaRepository.findById(idCategoria);
        if (opCategoria.isPresent())
            return (Categoria) opCategoria.get();
        throw new CategoriaInexistenteException("Categoria não encontrado com o id especificado.");
    }

    public void adicionaProduto(Categoria categoria, Produto produto) {
        categoria.addProduto(produto);
        produto.setCategoria(categoria);
        categoriaRepository.save(categoria);
        produtoRepository.save(produto);
    }
}
