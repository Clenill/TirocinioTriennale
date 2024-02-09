package com.tirociniotriennale.sitoeventi.model;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.persistence.CascadeType;

@Entity
@Table(name = "utente")
public class Utente {
    
	@Id
	@Column(name = "user", length = 25)
	private String user;
	
	@Column(name = "password", length = 20, nullable= false)
	@NotBlank
	@Size(min=3, max = 20)
	private String password;
	
	@Column(name = "enabled", nullable= false)
	private boolean enabled;
	
	@Column(name= "nomeorg", length = 25)
    private String nomeorg;
	
	@Column(name = "mail")
    private String mail;
	
	public Utente() {}
	
	public Utente(String user, String password, boolean enabled, String nomeorg, String mail) {
		this.user=user;
		this.password=password;
		this.enabled=enabled;
		this.nomeorg=nomeorg;
		this.mail=mail;
	}
	
	//Getter e Setter
	
	public String getUser() {
		return user;
	}
	
	public void setUser(String user) {
		this.user=user;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password=password;
	}
	
	public boolean getEnabled() {
		return enabled;
	}
	
	public String getMail(){return mail;}

    public void setMail(String mail){this.mail = mail;}
	
	public void setEnebled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public String getNomeorg(){return nomeorg;}

    public void setNomeorg(String nomeorg){this.nomeorg = nomeorg;}
	
	@OneToMany(mappedBy = "utente", 
    		fetch = FetchType.EAGER)
    private Set<Ordine> ordini;
    public Set<Ordine> getOrdini(){
    	return ordini;
    }
    
    @OneToOne(mappedBy = "utenteAut", cascade = CascadeType.ALL)
    private Autorizzazioni autorizzazioni;
	
    public Autorizzazioni getAutorizzazioni() {
    	return autorizzazioni;
    }
    
    @OneToMany(mappedBy = "utenteevento",
    		fetch = FetchType.EAGER)
    private Set<Evento> eventi;
    public Set<Evento> getEventi(){
    	return eventi;
    }
    
}
	