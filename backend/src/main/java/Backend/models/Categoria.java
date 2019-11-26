package Backend.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.File;
import java.util.List;

@Entity
public class Categoria {

    @Id
    @Column(name = "id_categoria")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoria")
    @JsonManagedReference
    private List<Produto> produtos;

    @ManyToOne
    @JoinColumn(name = "id_supermercado")
    @JsonBackReference
    private Supermercado supermercado;

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

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProduto(List<Produto> produto) {
        this.produtos = produto;
    }

    public void addProduto(Produto produto) {
        this.produtos.add(produto);
    }

    public Supermercado getSupermercado() {
        return supermercado;
    }

    public void setSupermercado(Supermercado supermercado) {
        this.supermercado = supermercado;
    }

    public String getCaminhoImagem() {
        return 	System.getProperty("user.dir") +
                File.separator + "uploads" +
                File.separator + "produtos" +
                File.separator + this.getId() +
                File.separator + "imagemProduto.png";
    }
}
