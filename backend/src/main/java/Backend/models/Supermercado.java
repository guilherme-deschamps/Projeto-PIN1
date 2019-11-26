package Backend.models;

import Backend.FileStorage.FileStorageProperties;
import Backend.FileStorage.FileStorageService;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
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
	private Usuario usuario;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "supermercado")
	@JsonManagedReference
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

	@JsonIgnore
	public Usuario getUsuario() {
		return usuario;
	}

	public Long getIdUsuario() {
		return usuario.getId();
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getCaminhoImagem() {
		return 	System.getProperty("user.dir") +
				File.separator + "uploads" +
				File.separator + "supermercados" +
				File.separator + this.getId() +
				File.separator + "logoSupermercado.png";
	}
}
