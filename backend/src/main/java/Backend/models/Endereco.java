package Backend.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Endereco {

	@Id
	@Column(name = "id_endereco")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "numero")
	private String numero;
	
	@Column(name = "cep")
	private String cep;
	
	@Column(name = "logradouro")
	private String logradouro;
	
	@Column(name = "bairro")
	private String bairro;
	
	@Column(name = "cidade")
	private String cidade;
	
	public Endereco() {
	}
	
	public Endereco(String numero, String cep, String logradouro, 
			String bairro, String cidade) {
		
		this.numero = numero;
		this.cep = cep;
		this.logradouro = logradouro;
		this.bairro = bairro;
		this.cidade = cidade;
	}
}
