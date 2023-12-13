package com.tirociniotriennale.sitoeventi.model;


import jakarta.persistence.*;


@Entity
@Table(name= "faq")
public class Faq {
    @Id
    @Column(name= "idfaq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name= "domanda")
    private String domanda;

    @Column(name = "risposta")
    private String risposta;

    public Faq(){

    }

    public Faq(String domanda, String risposta, int id){
        this.id = id;
        this.domanda = domanda;
        this.risposta = risposta;
    }


    public String getDomanda(){
        return this.domanda;
    }

    public void setDomanda(String dom){
        this.domanda = dom;
    }

    public String getRisposta(){
        return this.risposta;
    }

    public void setRisposta(String ris){
        this.risposta = ris;
    }

    public long getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }


}
