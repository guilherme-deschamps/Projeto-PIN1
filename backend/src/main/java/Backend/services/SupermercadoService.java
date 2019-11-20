package Backend.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

import Backend.FileStorage.FileStorageException;
import Backend.FileStorage.FileStorageProperties;
import Backend.FileStorage.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Backend.exceptions.ObjetoJaCadastradoException;
import Backend.exceptions.SupermercadoInexistenteException;
import Backend.models.Endereco;
import Backend.models.Supermercado;
import Backend.repositories.SupermercadoRepository;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class SupermercadoService {

    @Autowired
    EnderecoService enderecoService;

    @Autowired
    SupermercadoRepository supermercadoRepository;

    private final Path fileStorageLocation;
    @Autowired
    FileStorageService fileStorageService;

    @Autowired
    public SupermercadoService(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getlocalizacaoUploads())
                .toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public Supermercado cadastraSupermercado(String nome, String cnpj, String telefone, String email, Endereco endereco,
                                             MultipartFile imgSupermercado) throws IOException {

        if (supermercadoRepository.existsByCnpjOrEndereco(cnpj, endereco))
            throw new ObjetoJaCadastradoException("Supermercado ja cadastrado com esse cnpj ou endereço");
        Supermercado novoSupermercado = new Supermercado(nome, cnpj, telefone, email, endereco);
        novoSupermercado = supermercadoRepository.save(novoSupermercado);

        String caminho = this.fileStorageLocation + File.separator + "supermercado" + File.separator + novoSupermercado.getId();
        File diretorio = new File(this.fileStorageLocation.resolve(caminho).toString());
        if (!diretorio.exists())
            diretorio.mkdirs();

        fileStorageService.storeFile(imgSupermercado, caminho, "logoSupermercado");
        return novoSupermercado;
    }

    public Supermercado buscaSupermercadoPorId(Long id) throws SupermercadoInexistenteException {

        Optional opSupermercado = supermercadoRepository.findById(id);
        if (opSupermercado.isPresent())
            return (Supermercado) opSupermercado.get();
        throw new SupermercadoInexistenteException("Usuario n�o encontrado com o id especificado.");
    }

    public Iterable<Supermercado> buscaSupermercados() {

        return supermercadoRepository.findAll();
    }
}
