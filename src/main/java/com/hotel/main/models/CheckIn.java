package com.hotel.main.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sun.istack.NotNull;
//import javax.validation.constraints.NotNull;

@Entity
@Table(name="J002MVI")
public class CheckIn implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "codcin")
	@NotNull
	private Long idCheckIn;
		
	@ManyToOne
	@JoinColumn(name = "codhos")
	private Hospede hospede;
	
	@Column(name = "datent")
	private LocalDateTime dataEntrada;
	
	@Column(name = "datsai")
	private LocalDateTime dataSaida;
	
	@Column(name = "adcvei")
	private Boolean adicionalVeiculo;
	
	@Column(name = "valhos")
	private BigDecimal valorCheckin;

	public Long getIdCheckIn() {
		return idCheckIn;
	}

	public void setIdCheckIn(Long idCheckIn) {
		this.idCheckIn = idCheckIn;
	}

	public Hospede getHospede() {
		return hospede;
	}

	public void setHospede(Hospede hospede) {
		this.hospede = hospede;
	}

	public LocalDateTime getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(LocalDateTime dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public LocalDateTime getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(LocalDateTime dataSaida) {
		this.dataSaida = dataSaida;
	}

	public Boolean getAdicionalVeiculo() {
		return adicionalVeiculo;
	}

	public void setAdicionalVeiculo(Boolean adicionalVeiculo) {
		this.adicionalVeiculo = adicionalVeiculo;
	}

	public BigDecimal getValorCheckin() {
		return valorCheckin;
	}

	public void setValorCheckin(BigDecimal valorHospedagem) {
		this.valorCheckin = valorHospedagem;
	} 
	
}
