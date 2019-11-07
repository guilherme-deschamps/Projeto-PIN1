package Backend.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Categoria {

    @Id
    @Column(name = "id_categoria")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @OneToMany
    @JoinColumn(name = "id_produto")
    private List<Produto> produto;

    public Categoria() {
    }

    public Categoria(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
