package com.hotel.main.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
//import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

//import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="J001HOS")
public class Hospede implements Serializable {
		
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "codhos")
	@NotNull
	private Long idHospede;
		
	@Column(name = "nomhos")
	private String nomeHospede;
	
	@Column(name = "numdoc")
	private String nroDocumento;
	
	@Column(name = "numfon")
	private String nroFone;
	
	@JsonIgnore
	@OneToMany(mappedBy = "hospede")
	private List<CheckIn> checkins;
	
	@Transient
	private BigDecimal totalCheckin;
	
	@Transient
	private LocalDateTime ultimoCheckin;

	public LocalDateTime getUltimoCheckin() {
		return ultimoCheckin;
	}

	public void setUltimoCheckin(LocalDateTime ultimoCheckin) {
		this.ultimoCheckin = ultimoCheckin;
	}

	public BigDecimal getTotalCheckin() {
		return totalCheckin;
	}

	public void setTotalCheckin(BigDecimal totalCheckin) {
		this.totalCheckin = totalCheckin;
	}

	public List<CheckIn> getCheckins() {
		return checkins;
	}

	public void setCheckins(List<CheckIn> checkins) {
		this.checkins = checkins;
	}

	public Long getIdHospede() {
		return idHospede;
	}

	public void setIdHospede(Long idHospede) {
		this.idHospede = idHospede;
	}

	public String getNomeHospede() {
		return nomeHospede;
	}

	public void setNomeHospede(String nomeHospede) {
		this.nomeHospede = nomeHospede;
	}

	public String getNroDocumento() {
		return nroDocumento;
	}

	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

	public String getNroFone() {
		return nroFone;
	}

	public void setNroFone(String nroFone) {
		this.nroFone = nroFone;
	}
	
}
