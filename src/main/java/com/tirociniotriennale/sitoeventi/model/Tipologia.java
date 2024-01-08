package com.tirociniotriennale.sitoeventi.model;


import java.util.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "tipologia")
public class Tipologia {
    @Id
    @Column(name = "idtipologia")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idtipologia;

    @Column(name = "tipologia", length= 20, nullable = false)
    @NotBlank
    private String tipologia;


    public Tipologia(){ }

    public Tipologia(int idtipologia, String tipologia){
        this.idtipologia = idtipologia;
        this.tipologia = tipologia;
    }

    public String getTipologia(){return tipologia;}
    public void setTipologia(String tipologia){this.tipologia = tipologia;}

    public int getIdtipologia(){return idtipologia;}

    public void setIdtipologia(int idtipologia){this.idtipologia = idtipologia;}
    
    @OneToMany(mappedBy = "tipologia", 
    		fetch = FetchType.EAGER)
    private Set<Evento> eventi;
    public Set<Evento> getEventi(){
    	return eventi;
    }
    
    


}