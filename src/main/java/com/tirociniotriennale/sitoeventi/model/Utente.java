package com.tirociniotriennale.sitoeventi.model;


import jakarta.persistence.*;

@Entity
@Table(name = "utente")
public class Utente {
    @Id
    @Column(name = "user")// USER -----------------------------------------------------------
    private String user;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cognome")
    private String cognome;

    @Column(name= "nomeorg")
    private String nomeorg;

    @Column(name = "descrizione")
    private String descrizione;

    @Column(name = "mail")
    private String mail;

    @Column(name = "password")// PASSWORD----------------------------------------------------
    private String password;

    @Column(name = "indirizzo")
    private String indirizzo;

    @Column(name = "logo")
    private String logo;

    @Column(name = "missione")
    private String missione;

    @Column(name = "ruolo") // RUOLO --------------------------------------------------------
    private String ruolo;

    // Costruttori!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public Utente(){

    }

    public Utente(String user, String nome, String cognome, String nomeorg, String descrizione, String mail,
                  String password, String indirizzo, String logo, String missione, String ruolo){
        this.user = user;
        this.nome = nome;
        this.cognome = cognome;
        this.nomeorg = nomeorg;
        this.descrizione = descrizione;
        this.mail = mail;
        this.password = password;
        this.indirizzo = indirizzo;
        this.logo = logo;
        this.missione = missione;
        this.ruolo = ruolo;

    }

    // Getter e Setter Fatti //

    public String getUser(){// 111111111111111111111111111111111
        return user;
    }

    public void setUser(String user){
        this.user = user;
    }

    public String getNome(){// 2222222222222222222222222222222222222
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getCognome(){ // 3333333333333333333333333333333333
        return cognome;
    }

    public void setCognome(String cognome){
        this.cognome = cognome;
    }

    public String getNomeorg(){return nomeorg;}

    public void setNomeorg(String nomeorg){this.nomeorg = nomeorg;}

    public String getDescrizione(){return descrizione;}

    public void setDescrizione(String descrizione){this.descrizione = descrizione;}

    public String getMail(){return mail;}

    public void setMail(String mail){this.mail = mail;}

    public String getPassword(){ // 55555555555555555555555555555555555555
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getIndirizzo(){ // 444444444444444444444444444444444
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo){
        this.indirizzo = indirizzo;
    }

    public String getLogo(){return logo;}

    public void setLogo(String logo){this.logo = logo;}

    public String getMissione(){return missione;}

    public void setMissione(String missione){this.missione = missione;}


    public String getRuolo(){ // 66666666666666666666666666666666666666666666
        return ruolo;
    }

    public void setRuolo(String ruolo){
        this.ruolo = ruolo;
    }


}
