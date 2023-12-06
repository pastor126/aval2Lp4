package com.eduardo.aval2Lp4.modelo;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Moto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = "A marca deve ser informada.")
	@Size(min = 3, message = "A marca deve ter no mínimo 3 caracteres.")
	private String marca;
	@NotBlank(message = "O modelo deve ser informado.")
	private String modelo;
	@NotBlank(message = "A marca deve ser informada.")
	@Size(min = 3, message = "A cor deve ter no mínimo 3 caracteres.")
	private String cor;
	
	@OneToOne(mappedBy = "moto", cascade = CascadeType.ALL)
	private Documento documento;
	
	@OneToMany(mappedBy = "moto")
	private List<Revisao> revisoes;
	
	
	public List<Revisao> getRevisoes() {
		return revisoes;
	}
	public void setRevisoes(List<Revisao> revisoes) {
		this.revisoes = revisoes;
	}
	public Documento getDocumento() {
		return documento;
	}
	public void setDocumento(Documento documento) {
		this.documento = documento;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	
}
