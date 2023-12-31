/*package com.tirociniotriennale.sitoeventi.service;

import com.tirociniotriennale.sitoeventi.model.Utente;
import com.tirociniotriennale.sitoeventi.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class MockUserDatailsService extends JdbcUserDetailsManager {

    private final List<UserDetails> utenti;

    public MockUserDatailsService(List<UserDetails> utenti) {
        this.utenti = utenti;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return utenti.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
*/
/*
    @Override
    public UserDetails loadUserByUsername(String user) throws UsernameNotFoundException {
        return new UserDetails(){ //implementazione dei metodi


            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
                authorities.add(new SimpleGrantedAuthority("ROLE_USER"));//Spring richiede che il ruolo
                // deve essere preceduto da ROLE_ . Sarà vero?
                return authorities;

            }

            @Override
            public String getPassword() {
                return "{noop}password";
            }//{noop} dice a spring che la password non è stata criptata, usare solo in fase di develop.


            @Override
            public String getUsername() {
                return user;
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
        };
    }//prende lo username, restituisce un interfaccia UserDetails

*/



//}
