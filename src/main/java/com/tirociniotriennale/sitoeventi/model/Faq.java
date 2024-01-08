package com.tirociniotriennale.sitoeventi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "faq")
public class Faq {
	
	@Id
	@Column(name = "idfaq")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idfaq;
	
	@Column(name = "domanda", nullable = false, length = 200)
	@NotBlank
	private String domanda;
	
	@Column(name = "risposta", nullable = false, length = 1000)
	@NotBlank
	private String risposta;
	
	public Faq() {
		
	}
	
	public Faq(int idfaq, String domanda, String risposta) {
		this.idfaq = idfaq;
		this.domanda = domanda;
		this.risposta = risposta;
	}
	
	//Getter e Setter
	
	public int getIdfaq() {

        return idfaq;
    }

    public void setId(int idfaq){

        this.idfaq = idfaq;
    }
    public String getDomanda() {

        return domanda;
    }

    public void setDomanda(String domanda){

        this.domanda = domanda;
    }
    public String getRisposta() {

        return risposta;
    }

    public void setRisposta(String risposta){

        this.risposta = risposta;
    }

}
