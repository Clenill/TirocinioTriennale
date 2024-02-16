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

    @Column(name = "totpagamento")
    private double totpagamento;

    @Column(name = "pagato", nullable = false)
    private boolean pagato;
	
	public Ordine() {
	}
	
	public Ordine(int idordine, int biglietti, double totpagamento, boolean pagato) {
		this.idordine = idordine;
		this.biglietti = biglietti;
        this.totpagamento = totpagamento;
        this.pagato = pagato;
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

    public double getTotpagamento() {

        return totpagamento;
    }
    public void setTotpagamento(double totpagamento) {

        this.totpagamento = totpagamento;
    }

    public boolean getPagato(){
        return pagato;
    }

    public void setPagato(boolean pagato){
        this.pagato = pagato;
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
