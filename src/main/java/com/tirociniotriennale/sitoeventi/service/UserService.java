package com.tirociniotriennale.sitoeventi.service;

import com.tirociniotriennale.sitoeventi.model.Utente;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {

    Utente createUtente(Utente utente);

}
