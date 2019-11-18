package Backend.models;

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
	
	@OneToMany
	@JoinColumn(name = "id_usuario")
	private List<Usuario> usuarios;

	@OneToMany
	@JoinColumn(name = "id_categoria")
	private List<Categoria> categorias;

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

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void addCategoria(Categoria categoria) {
		this.categorias.add(categoria);
	}
}
