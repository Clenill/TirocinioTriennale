package com.tirociniotriennale.sitoeventi.model;

import java.util.*;
import jakarta.persistence.*;

@Entity
@Table(name = "tipologia")
public class Tipologia {
    @Id
    @Column(name = "idtipologia")
    private int idtipologia;

    @Column(name = "tipologia")
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


}
