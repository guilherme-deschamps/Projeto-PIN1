package Backend.services;

import Backend.exceptions.ObjetoJaCadastradoException;
import Backend.models.Categoria;
import Backend.models.Usuario;
import Backend.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;

    public Categoria cadastraCategoria(String nome) {
        Categoria categoria = new Categoria(nome);
        categoriaRepository.save(categoria);
        return categoria;
    }
}
