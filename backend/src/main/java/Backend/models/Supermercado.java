package Backend.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Supermercado {
	
	@Id
    @Column(name = "id_supermercado")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome", nullable = false)
	private String nome;
	
	@Column(name = "cnpj", nullable = false)
	private String cnpj;
	
	@Column(name = "telefone", nullable = false)
	private String telefone;
	
	@Column(name = "email", nullable = false)
	private String email;
	
	@ManyToOne
	@JoinColumn(name = "id_endereco")
	private Endereco endereco;

	@OneToOne
	@JoinColumn(name = "id_usuario")
	@JsonIgnore
	private Usuario usuario;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "supermercado")
	@JsonBackReference
	private List<Categoria> categorias = new ArrayList<>();

	public Supermercado() {
	}
	
	public Supermercado(String nome, String cnpj, String telefone, String email, Endereco endereco) {
		this.nome = nome;
		this.cnpj = cnpj;
		this.telefone = telefone;
		this.email = email;
		this.endereco = endereco;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getEmail() {
		return email;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void addCategoria(Categoria categoria) {
		this.categorias.add(categoria);
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
