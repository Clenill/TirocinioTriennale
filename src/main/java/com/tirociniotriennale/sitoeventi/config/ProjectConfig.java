
package com.tirociniotriennale.sitoeventi.config;

import com.tirociniotriennale.sitoeventi.security.SecurityUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl.*;

@Configuration
public class ProjectConfig {

    /*
    JdbcUserDetailsManager è come dice il nome un userdatailmanager.
    l'autentication provider chiama JdbcUserDetailsManager per ottenere
    l'utente tramite l'username. L'istanza di JdbcUserDetailsManager
    cerca l'utente del db e ritorna i dettagli. Se l'utente è trovato
    verifica che la password fornita coincide con quella nel db.
    Nel mio caso sovrascrivo le query perche di default questo cerca
    in tabelle autorities e users. Inoltre non utilizzo la colonna
    enebled e quindi non la scrivo della query.

    The JdbcUserDetailsManager needs the
    DataSource to connect to the database. The data source can be autowired
    through a parameter of the method (as presented in the next listing) or
    through an attribute of the class.
    */
    /*
    @Bean
    public CustomJdbcUserDetailsManager jdbcUserDetailsManager (DataSource dataSource){


        UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter = new UsernamePasswordAuthenticationFilter();
        usernamePasswordAuthenticationFilter.setUsernameParameter("user");


        String usersByUsernameQuery = "select user, password, enabled from spring_prova_jpa.utente where username = ?";
        String authsByUserQuery = "select ruolo from spring_prova_jpa.autorizzazioni where username = ?";

        CustomJdbcUserDetailsManager userDetailsManager = new CustomJdbcUserDetailsManager(dataSource);

        userDetailsManager.setUsersByUsernameQuery(usersByUsernameQuery);
        userDetailsManager.setAuthoritiesByUsernameQuery(authsByUserQuery);

        return userDetailsManager;

    }
    */
/*
    @Bean
    public JdbcDaoImpl jdbcDao(DataSource dataSource){
        String usersByUsernameQuery = "select user, password, enabled from spring_prova_jpa.utente where username = ?";
        String authsByUserQuery = "select ruolo from spring_prova_jpa.autorizzazioni where username = ?";
        JdbcDaoImpl querypersonalizzate = new JdbcDaoImpl();
        querypersonalizzate.setDataSource(dataSource);

        querypersonalizzate.setUsersByUsernameQuery(usersByUsernameQuery);
        querypersonalizzate.setAuthoritiesByUsernameQuery(authsByUserQuery);
        return querypersonalizzate;
    }
*/
/*
    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService) {
        return new ProviderManager(Collections.singletonList(new DaoAuthenticationProvider()));
    }
    */


    /*
    private static class DaoAuthenticationProviderConfig extends DaoAuthenticationProvider {
        DaoAuthenticationProviderConfig(UserDetailsService userDetailsService) {
            setUserDetailsService(userDetailsService);
            setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        }
    }
*/
    /*
    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService) {
        List<AuthenticationProvider> providers = new ArrayList<>();
        providers.add(new DaoAuthenticationProviderConfig(userDetailsService));
        return new ProviderManager(providers);
    }
    */



    /*
    @Bean
    public AuthenticationManager authenticationManager(
            UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);


        return new ProviderManager(authenticationProvider);
    }
*/
}
