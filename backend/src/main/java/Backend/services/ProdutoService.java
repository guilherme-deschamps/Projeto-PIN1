package Backend.services;

import Backend.FileStorage.FileStorageException;
import Backend.FileStorage.FileStorageProperties;
import Backend.FileStorage.FileStorageService;
import Backend.exceptions.ObjetoJaCadastradoException;
import Backend.exceptions.ProdutoInexistenteException;
import Backend.exceptions.UsuarioInexistenteException;
import Backend.models.Categoria;
import Backend.models.Produto;
import Backend.models.Usuario;
import Backend.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    CategoriaService categoriaService;

    private final Path fileStorageLocation;
    @Autowired
    FileStorageService fileStorageService;

    @Autowired
    public ProdutoService(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getlocalizacaoUploads())
                .toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public Produto cadastraProduto(String nome, String marca, double preco, String unidMedida, Categoria categoria,
                                   MultipartFile imgProduto) throws IOException {

        Produto novoProduto = produtoRepository.save(new Produto(nome, marca, preco, unidMedida, categoria));
        categoriaService.adicionaProduto(categoria, novoProduto);

        String caminho = this.fileStorageLocation + File.separator + "produtos" + File.separator + novoProduto.getId();

        fileStorageService.storeFile(imgProduto, caminho, "imagemProduto");
        return novoProduto;
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
