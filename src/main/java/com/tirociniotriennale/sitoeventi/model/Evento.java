package com.tirociniotriennale.sitoeventi.model;



import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name= "evento")
public class Evento {
    @Id
    @Column(name= "idevento")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nome", length = 50, nullable = false)
    @NotNull
    private String nomeevento;

    @Column(name = "prezzo", nullable = false)
    @NotNull @DecimalMin(value = "0.50")
    private BigDecimal prezzo;
    
    @Column(name = "descestesa")//si
    private String desclong;
    
    @Column(name="descbreve")//si
    private String descbrv;
    
    @Column(name = "bigliettimax", nullable = false)
    @NotNull @Min(2)
    private int bigliettimax;
    
    @Column(name = "bigliettirim")
    private int biglietirimanenti;
    
    @Column(name = "luogo")
    private String luogoevento;
    
    @Column(name = "image")
    private String nomeimmagine;
    
    @Column(name = "datajpa")
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate localDate;


    //--------------------------------------- Manca la data------------------------------------------------------
    public Evento() {
    }

    // Costruttore con dati
    public Evento(int id, String user, String nomeevento, String descbrv, String desclong, BigDecimal prezzo,
    	       String luogoevento, int bigliettimax, int biglietirimanenti, String nomeimmagine, LocalDate localDate ){

    	        this.id = id;
    	        this.nomeevento = nomeevento;
    	        this.descbrv = descbrv;
    	        this.desclong = desclong;
    	        this.prezzo = prezzo;
    	        this.luogoevento = luogoevento;
    	        this.bigliettimax = bigliettimax;
    	        this.biglietirimanenti = biglietirimanenti;
    	        this.nomeimmagine = nomeimmagine;
    	        this.localDate = localDate;
    	        //-------------------------------Manca la data-----------------------------------------------------------
    	    }

    public int getId() {

        return id;
    }

    public void setId(int id){

        this.id = id;
    }

    public String getNomeevento() {

        return nomeevento;
    }

    public void setNomeevento(String nomeevento){

        this.nomeevento = nomeevento;
    }
    public String getDescbrv() {

        return descbrv;
    }

    public void setDescbrv(String descbrv){

        this.descbrv = descbrv;
    }
    public String getDesclong() {

        return desclong;
    }

    public void setDesclong(String desclong){

        this.desclong = desclong;
    }
    public String getLuogoevento() {

        return luogoevento;
    }

    public void setLuogoevento(String luogoevento){

        this.luogoevento = luogoevento;
    }
    public String getNomeimmagine() {

        return nomeimmagine;
    }

    public void setNomeimmagine(String nomeimmagine){

        this.nomeimmagine = nomeimmagine;
    }
    public BigDecimal getPrezzo(){

        return prezzo;
    }

    public void setPrezzo(BigDecimal prezzo){

        this.prezzo = prezzo;
    }

    public int getBigliettimax(){

        return bigliettimax;
    }

    public void setBigliettimax(int bigliettimax){

        this.bigliettimax = bigliettimax;
    }
    public int getBiglietirimanenti(){

        return biglietirimanenti;
    }

    public void setBiglietirimanenti(int biglietirimanenti){

        this.biglietirimanenti = biglietirimanenti;
    }
    public LocalDate getLocalDate() {
    	return localDate;
    }
    
    public void setLocalDate(LocalDate localDate) {
    	this.localDate = localDate;
    }

    @ManyToOne()
    @JoinColumn(name="idtipologia")
    private Tipologia tipologia;
    
    public Tipologia getTipologia() {
    	return tipologia;
    }
    
    public void setTipologia(Tipologia tipologia) {
    	this.tipologia = tipologia;
    }
    
    @OneToMany(mappedBy = "ordineevento", 
    		fetch = FetchType.EAGER)// ad un evento ci sono associati più ordini
    private Set<Ordine> ordini;
    public Set<Ordine> getOrdini(){
    	return ordini;
    }
    
    @ManyToOne()
    @JoinColumn(name = "user")
    private Utente utenteevento;
    
    public Utente getUtente() {
    	return utenteevento;
    }
    
    public void setUtente(Utente utenteevento) {
    	this.utenteevento = utenteevento;
    }

}
