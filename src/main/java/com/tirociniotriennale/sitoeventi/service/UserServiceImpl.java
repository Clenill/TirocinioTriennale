package com.tirociniotriennale.sitoeventi.service;


import com.tirociniotriennale.sitoeventi.model.Utente;
import com.tirociniotriennale.sitoeventi.repository.UtenteRepository;
import com.tirociniotriennale.sitoeventi.security.MyUserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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

}
