package Backend.models;

import javax.persistence.*;

@Entity
public class Produto {

    @Id
    @Column(name = "id_produto")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "marca")
    private String marca;

    @Column(name = "preco")
    private double preco;

    @Column(name = "ehEsgotado")
    private boolean ehEsgotado;

    @Column(name = "und_medida")
    private String undMedida;

    @Column(name = "percentual_promocao")
    private int percentualPromocao;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    public Produto() {
    }

    public Produto(String nome, String marca, double preco, boolean ehEsgotado, String undMedida, int percentualPromocao,
                   Categoria categoria) {
        this.nome = nome;
        this.marca = marca;
        this.preco = preco;
        this.ehEsgotado = ehEsgotado;
        this.undMedida = undMedida;
        this.percentualPromocao = percentualPromocao;
        this.categoria = categoria;
    }
}
