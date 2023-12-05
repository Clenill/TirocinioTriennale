package com.tirociniotriennale.sitoeventi.model;


import jakarta.persistence.*;

@Entity
@Table(name= "evento")
public class Evento {
    @Id
    @Column(name= "idevento")//si
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name ="idtipologia")//si
    private long idtipologia;

    @Column(name="user")//si
    private String user;

    @Column(name="nome")//si
    private String nomeevento;

    @Column(name="descbreve")//si
    private String descbrv;

    @Column(name = "descestesa")//si
    private String desclong;

    @Column(name = "prezzo")
    private float prezzo;

    @Column(name = "luogo")
    private String luogoevento;

    @Column(name = "bigliettimax")
    private int bigliettimax;

    @Column(name = "bigliettirim")
    private int biglietirimanenti;

    @Column(name = "image")
    private String nomeimmagine;

    //--------------------------------------- Manca la data------------------------------------------------------
    public Evento() { //Costruttore vuoto
        //-------------------------------------Da rivedere se vuoto----------------------------------------------
    }

    // Costruttore con dati
    public Evento(long id, long idtipologia, String user, String nomeevento, String descbrv, String desclong, float prezzo,
       String luogoevento, int bigliettimax, int biglietirimanenti, String nomeimmagine ){

        this.id = id;
        this.idtipologia = idtipologia;
        this.user = user;
        this.nomeevento = nomeevento;
        this.descbrv = descbrv;
        this.desclong = desclong;
        this.prezzo = prezzo;
        this.luogoevento = luogoevento;
        this.bigliettimax = bigliettimax;
        this.biglietirimanenti = biglietirimanenti;
        this.nomeimmagine = nomeimmagine;
        //-------------------------------Manca la data-----------------------------------------------------------
    }

    public long getId() {

        return id;
    }

    public long getIdtipologia(){

        return idtipologia;
    }

    public String getUser() {

        return user;
    }
    public String getNomeevento() {

        return nomeevento;
    }
    public String getDescbrv() {

        return descbrv;
    }
    public String getDesclong() {

        return desclong;
    }
    public String getLuogoevento() {

        return luogoevento;
    }
    public String getNomeimmagine() {

        return nomeimmagine;
    }
    public float getPrezzo(){

        return prezzo;
    }

    public int getBigliettimax(){

        return bigliettimax;
    }
    public int getBiglietirimanenti(){

        return biglietirimanenti;
    }


}
