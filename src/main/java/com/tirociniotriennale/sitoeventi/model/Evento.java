package com.tirociniotriennale.sitoeventi.model;


import jakarta.persistence.*;

@Entity
@Table(name= "evento")
public class Evento {
    @Id
    @Column(name= "idevento")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name ="idtipologia")
    private long idtipo;

    @Column(name="user")
    private String userevento;

    @Column(name="nome")
    private String nomeevento;

    @Column(name="descbreve")
    private String descbrv;

    @Column(name = "descestesa")
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



}
