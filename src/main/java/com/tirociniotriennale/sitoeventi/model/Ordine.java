package com.tirociniotriennale.sitoeventi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ordine")
public class Ordine {
	
	@Id
	@Column(name = "idordine")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idordine;
	
	@Column(name = "biglietti", nullable = false)
	private int biglietti;
	
	public Ordine() {
	}
	
	public Ordine(int idordine, int biglietti) {
		this.idordine = idordine;
		this.biglietti = biglietti;
	}
	
	//Getters e Setters
	
	public int getIdordine() {

        return idordine;
    }

    public void setIdordine(int idordine){

        this.idordine = idordine;
    }
    
    public int getBiglietti() {
    	return biglietti;
    }
    public void setBiglietti(int biglietti) {
    	this.biglietti = biglietti;
    }
    
    
    @ManyToOne()//Molti ordini possono riferirsi allo stesso idevento
    @JoinColumn(name="idevento")
    private Evento ordineevento;
    
    public Evento getEvento() {
    	return ordineevento;
    }
    
    public void setEvento(Evento ordineevento) {
    	this.ordineevento = ordineevento;
    }
    
    @ManyToOne()//Molti ordini possono riferirsi allo stesso utente
    @JoinColumn(name="user")
    private Utente utente;
    
    public Utente getUtente() {
    	return utente;
    }
    
    public void setUtente(Utente utente) {
    	this.utente = utente;
    }
    
    

    
    
}
