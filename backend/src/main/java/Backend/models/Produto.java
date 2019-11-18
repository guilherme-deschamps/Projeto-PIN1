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

    public Produto(String nome, String marca, double preco, boolean ehEsgotado, String undMedida, Categoria categoria) {
        this.nome = nome;
        this.marca = marca;
        this.preco = preco;
        this.ehEsgotado = ehEsgotado;
        this.undMedida = undMedida;
        this.categoria = categoria;
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

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public boolean isEhEsgotado() {
        return ehEsgotado;
    }

    public void setEhEsgotado(boolean ehEsgotado) {
        this.ehEsgotado = ehEsgotado;
    }

    public String getUndMedida() {
        return undMedida;
    }

    public void setUndMedida(String undMedida) {
        this.undMedida = undMedida;
    }

    public int getPercentualPromocao() {
        return percentualPromocao;
    }

    public void setPercentualPromocao(int percentualPromocao) {
        this.percentualPromocao = percentualPromocao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
