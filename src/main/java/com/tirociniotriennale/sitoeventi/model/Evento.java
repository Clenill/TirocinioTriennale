package com.tirociniotriennale.sitoeventi.model;


import jakarta.persistence.*;
// Le annotazioni servono per associale la tabella e le colonne del db all'oggetto.
// si usano al posto del descrittore in xml, obv veloci e pratiche.
// Per interrogare il database si utilizza JDBC e bisogna dirgli che driver usare (mariaDB) e indirizzo.
// Le operazioni CRUD vengono effettuate da Evento Repository che estende JpaRepository o CrudRepository.
// Hibernate automatizza le operazioni CRUD.
@Entity
@Table(name= "evento")
public class Evento {
    @Id
    @Column(name= "idevento")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name ="idtipologia")//si
    private int idtipologia;

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
    public Evento(int id, int idtipologia, String user, String nomeevento, String descbrv, String desclong, float prezzo,
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

    public int getId() {

        return id;
    }

    public void setId(int id){

        this.id = id;
    }

    public int getIdtipologia(){

        return idtipologia;
    }

    public void setIdtipologia(int idtipologia){

        this.idtipologia = idtipologia;
    }

    public String getUser() {

        return user;
    }

    public void setUser(String user){

        this.user = user;
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
    public float getPrezzo(){

        return prezzo;
    }

    public void setPrezzo(float prezzo){

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

}
