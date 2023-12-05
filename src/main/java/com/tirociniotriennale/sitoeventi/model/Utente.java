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

    @Column(name = "password")// PASSWORD----------------------------------------------------
    private String password;

    @Column(name = "indirizzo")
    private String indirizzo;

    @Column(name = "ruolo") // RUOLO --------------------------------------------------------
    private String ruolo;

    // Costruttori!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public Utente(){

    }

    public Utente(String user, String nome, String cognome, String password, String indirizzo, String ruolo){
        this.user=user;
        this.nome = nome;
        this.cognome = cognome;
        this.password = password;
        this.ruolo = ruolo;
        this.indirizzo = indirizzo;
    }

    // Getter e Setter !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

    public String getUser(){// 111111111111111111111111111111111
        return user;
    }

    public void setUser(){
        this.user = user;
    }

    public String getNome(){// 2222222222222222222222222222222222222
        return nome;
    }

    public void setNome(){
        this.nome = nome;
    }

    public String getCognome(){ // 3333333333333333333333333333333333
        return cognome;
    }

    public void setCognome(){
        this.cognome = cognome;
    }

    public String getIndirizzo(){ // 444444444444444444444444444444444
        return indirizzo;
    }

    public void setIndirizzo(){
        this.indirizzo = indirizzo;
    }

    public String getPassword(){ // 55555555555555555555555555555555555555
        return password;
    }

    public void setPassword(){
        this.password = password;
    }

    public String getRuolo(){ // 66666666666666666666666666666666666666666666
        return ruolo;
    }

    public void setRuolo(){ // 66666666666666666666666666666666666666666666
        this.ruolo = ruolo;
    }


}
