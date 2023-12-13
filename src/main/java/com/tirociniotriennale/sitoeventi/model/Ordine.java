package com.tirociniotriennale.sitoeventi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ordine")
public class Ordine {
    @Id
    @Column(name = "idordine")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idordine;

    @Column(name = "idevento")
    private int ideventoordine;

    @Column(name = "user")
    private String userordine;

    @Column(name = "biglietti")
    private int biglietti;

    @Column(name = "pagamento")
    private String pagamento;

    public Ordine(){}

    public Ordine(int idordine, int ideventoordine, String userordine, int biglietti, String pagamento){
        this.idordine = idordine;
        this.ideventoordine = ideventoordine;
        this.userordine = userordine;
        this.biglietti = biglietti;
        this.pagamento = pagamento;
    }

    public int getIdordine(){return idordine;}
    public void setIdordine(int idordine){this.idordine = idordine;}

    public int getIdeventoordine(){return ideventoordine;}
    public void setIdeventoordine(int ideventoordine){this.ideventoordine = ideventoordine;}

    public String getUserordine(){return userordine;}
    public void setUserordine(String userordine){this.userordine = userordine;}

    public int getBiglietti(){return biglietti;}
    public void setBiglietti(int biglietti){this.biglietti = biglietti;}

    public String getPagamento(){return pagamento;}

    public void setPagamento(String pagamento){this.pagamento = pagamento;}

}
