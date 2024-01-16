
package com.tirociniotriennale.sitoeventi.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.tirociniotriennale.sitoeventi.model.Utente;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

//Utilizzo questa classe per implementare UserDetails in modo da collegare il mio
//Utente all'architettura di Spring Security. Questo perché ho la mia Classe Utente e il DB associato ad essa.

public class SecurityUser implements UserDetails {
    private final Utente utente;
    public SecurityUser(Utente utente) {
        this.utente = utente;
    }

    @Override
    public String getUsername() {//get username e get password vengono usati nel processo di autenticazione
        return utente.getUser();//Metodo getter della classe User
    }

    @Override
    public String getPassword() {
        return utente.getPassword();//Metodo getter della classe user
    }

    //Il metodo getRuolo() restituisce il ruolo, è una stringa e non può essere null;
    // comunquesia nel caso impossibile che fosse null gli assegno none come ruolo.
    public String getRole(){//adattato al nuovo db
        if (utente.getAutorizzazioni().getRuolo() == null){
            return "none";
        }

        return utente.getAutorizzazioni().getRuolo();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {//ogni utente deve avere almeno un'autorizzazione
        return Collections.<GrantedAuthority>singletonList(new SimpleGrantedAuthority("ROLE_"+getRole()));
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
