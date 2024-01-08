package com.tirociniotriennale.sitoeventi.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "autorizzazioni")
public class Autorizzazioni {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "ruolo")
	@NotBlank
	private String ruolo;
	
	public Autorizzazioni() {}
	
	public Autorizzazioni(Integer id, String ruolo) {
		this.id = id;
		
		this.ruolo= ruolo;
	} 
	
	//getter e setter
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id=id;
	}
	
	public String getRuolo() {
		return ruolo;
	}
	
	public void setRuolo(String ruolo) {
		this.ruolo=ruolo;
	}
	
	@OneToOne
	@JoinColumn(name = "user", referencedColumnName = "user")
	private Utente utenteAut;
	
	public Utente getUtenteAut() {
		return utenteAut;
	}
	
	public void setUtenteAut(Utente utenteAut) {
		this.utenteAut = utenteAut;
	}

}
