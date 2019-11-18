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

import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;

    public Produto cadastraProduto(String nome, String marca, double preco, boolean ehEsgotado, String unidMedida,
                                   int percentualPromocao, Categoria categoria) {

        Produto novoProduto = new Produto(nome, marca, preco, ehEsgotado, unidMedida, categoria);
        return novoProduto;
    }

    public Produto buscaProdutoPorId(Long idProduto) throws ProdutoInexistenteException {

        Optional opProduto = produtoRepository.findById(idProduto);
        if (opProduto.isPresent())
            return (Produto) opProduto.get();
        throw new ProdutoInexistenteException("Produto não encontrado com o id especificado.");
    }
}
