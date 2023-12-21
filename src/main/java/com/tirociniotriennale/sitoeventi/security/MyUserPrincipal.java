/*package com.tirociniotriennale.sitoeventi.security;

import com.tirociniotriennale.sitoeventi.model.Utente;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class MyUserPrincipal implements UserDetails {
    private Utente utente;
    public MyUserPrincipal(Utente utente) {
        this.utente = utente;
    }

    @Override
    public String getUsername() {
        return utente.getUser();
    }

    @Override
    public String getPassword() {
        return utente.getPassword();
    }


    public String getRole(){
        return utente.getRuolo();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.<GrantedAuthority>singletonList(new SimpleGrantedAuthority(getRole()));
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
