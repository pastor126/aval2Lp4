package com.eduardo.aval2Lp4.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Documento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = "A placa deve ser informada.")
	@Size(min = 7, message = "A placa de conter 7 caracteres.")
	@Size(max = 7, message = "A placa de conter 7 caracteres.")
	private String placa;
	@NotBlank(message = "O Renavan deve ser informado.")
	@Size(min = 11, message = "O Renavan deve conter 11 números.")
	@Size(max = 11, message = "O Renavan deve conter 11 números.")
	private String renavan;
	
	@OneToOne
	private Moto moto;
	
	
	
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getRenavan() {
		return renavan;
	}
	public void setRenavan(String renavan) {
		this.renavan = renavan;
	}
	public Moto getMoto() {
		return moto;
	}
	public void setMoto(Moto moto) {
		this.moto = moto;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	
	
}
