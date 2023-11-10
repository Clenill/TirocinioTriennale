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
    private long idtipo;

    @Column(name="user")//si
    private String userevento;

    @Column(name="nome")//si
    private String nomeevento;

    @Column(name="descbreve")//si
    private String descbrv;

    @Column(name = "descestesa")//si
    private String desclong;

    @Column(name = "prezzo")
    private float prezz;

    @Column(name = "luogo")
    private String luogoevento;

    @Column(name = "bigliettimax")
    private int bigliettimax;

    @Column(name = "bigliettirim")
    private int biglietirimanenti;

    @Column(name = "image")
    private String nomeimmagine;

    //--------------------------------------- Manca la data------------------------------------------------------
    public Evento() {
        //-------------------------------------Da rivedere se vuoto----------------------------------------------
    }

    public Evento(long id, long idtipo, String userevento, String nomeevento, String descbrv, String desclong, float prezz,
       String luogoevento, int bigliettimax, int biglietirimanenti, String nomeimmagine ){

        this.id = id;
        this.idtipo = idtipo;
        this.userevento = userevento;
        this.nomeevento = nomeevento;
        this.descbrv = descbrv;
        this.desclong = desclong;
        this.prezz = prezz;
        this.luogoevento = luogoevento;
        this.bigliettimax = bigliettimax;
        this.biglietirimanenti = biglietirimanenti;
        this.nomeimmagine = nomeimmagine;
        //-------------------------------Manca la data-----------------------------------------------------------
    }

    public long getId() {
        return id;
    }

    public long getIdtipo(){
        return idtipo;
    }

    public String getUserevento() {
        return userevento;
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
    public float getPrezz(){
        return prezz;
    }

    public int getBigliettimax(){
        return bigliettimax;
    }
    public int getBiglietirimanenti(){
        return biglietirimanenti;
    }


}
