package com.tirociniotriennale.sitoeventi.service;


import com.tirociniotriennale.sitoeventi.model.Utente;
import com.tirociniotriennale.sitoeventi.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private final UtenteRepository utenteRepository;

    @Autowired
    public UserServiceImpl(UtenteRepository utenteRepository){
        this.utenteRepository = utenteRepository;
    }

    public Utente createUtente(Utente utente){
        return utenteRepository.save(utente);
    }

    public Boolean salvaModificheUtente(Utente utente){

        Utente utenteOriginale = new Utente();
        Optional<Utente> trovaUtente = utenteRepository.findByUser(utente.getUser());
        if(trovaUtente.isPresent() ){
            utenteOriginale = trovaUtente.get();
            utenteOriginale.setPassword(utente.getPassword());
            utenteOriginale.setMail(utente.getMail());
            utenteRepository.save(utenteOriginale);//salvo l'utente oggetto passato per riferimeto quindi funzina
            return true;
        }

        return false;

    }

}
