package Backend.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Carrinho {

    public static final String CRIADO = "criado";
    public static final String LIDO = "lido";
    public static final String FAZENDO = "fazendo";
    public static final String FEITO = "feito";
    public static final String ENTREGANDO = "entregando";
    public static final String FINALIZADO = "finalizado";

    @Id
    @Column(name = "id_pedido")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "forma_de_pagamento")
    private String formaDePagamento;

    @Column(name = "valor_total")
    private double valorTotal;

    @Column(name = "situacao")
    private String situacao;

    @OneToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @OneToOne
    @JoinColumn(name = "id_supermercado")
    private Supermercado supermercado;

    @OneToOne
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;

    @ManyToMany
    @JoinTable(
            name = "carrinho_produto",
            joinColumns = @JoinColumn(name = "id_carrinho"),
            inverseJoinColumns = @JoinColumn(name = "id_produto"))
    private List<Produto> produtos;

    public Carrinho() {

    }

    public Carrinho(String formaDePagamento, double valorTotal, String situacao, Usuario usuario, Supermercado supermercado,
                    Endereco endereco, List<Produto> produtos) {
        this.formaDePagamento = formaDePagamento;
        this.valorTotal = valorTotal;
        this.situacao = situacao;
        this.usuario = usuario;
        this.supermercado = supermercado;
        this.endereco = endereco;
        this.produtos = produtos;
    }

    public Long getId() {
        return id;
    }

    public String getFormaDePagamento() {
        return formaDePagamento;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Supermercado getSupermercado() {
        return supermercado;
    }

    public Endereco getEndereco() {
        return endereco;
    }
}
