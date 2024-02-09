package com.tirociniotriennale.sitoeventi.service;
import com.tirociniotriennale.sitoeventi.model.Utente;

public interface UserService {

    Utente createUtente(Utente utente);

   public Boolean salvaModificheUtente(Utente utente);

   public Boolean salvaNuovoUtene(Utente utente);

}
