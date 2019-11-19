package Backend.services;

import Backend.exceptions.ObjetoJaCadastradoException;
import Backend.exceptions.ProdutoInexistenteException;
import Backend.exceptions.UsuarioInexistenteException;
import Backend.models.Categoria;
import Backend.models.Produto;
import Backend.models.Usuario;
import Backend.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    CategoriaService categoriaService;

    public Produto cadastraProduto(String nome, String marca, double preco, String unidMedida, Categoria categoria) {

        Produto produto = produtoRepository.save(new Produto(nome, marca, preco, unidMedida, categoria));
        categoriaService.adicionaProduto(categoria, produto);
        return produto;
    }

    public Produto buscaProdutoPorId(Long idProduto) throws ProdutoInexistenteException {

        Optional opProduto = produtoRepository.findById(idProduto);
        if (opProduto.isPresent())
            return (Produto) opProduto.get();
        throw new ProdutoInexistenteException("Produto não encontrado com o id especificado.");
    }

    public List<Produto> buscaProdutosPorCategoria(Categoria categoria) {

        return produtoRepository.getAllByCategoria(categoria);
    }
}
