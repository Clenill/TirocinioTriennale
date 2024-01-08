/*
package com.tirociniotriennale.sitoeventi.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.tirociniotriennale.sitoeventi.model.Utente;
import java.util.Collection;
import java.util.Collections;

//Utilizzo questa classe per implementare UserDetails in modo da collegare il mio
//Utente all'architettura di Spring Security. Questo perché ho la mia Classe Utente e il DB associato ad essa.

public class SecurityUser implements UserDetails {
    private final Utente utente;
    public SecurityUser(Utente utente) {
        this.utente = utente;
    }

    @Override
    public String getUsername() {
        return utente.getUser();//Metodo getter della classe User
    }

    @Override
    public String getPassword() {
        return utente.getPassword();//Metodo getter della classe user
    }

    //Il metodo getRuolo() restituisce il ruolo, è una stringa e non può essere null;

    public String getRole(){
        if (utente.getRuolo() == null){
            return "none";//Da configurazione è impossibile che sia null, ma non si sa mai.
        }

        return utente.getRuolo();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.<GrantedAuthority>singletonList(new SimpleGrantedAuthority());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Utente getUtente() {
        return utente;
    }

}
*/