
package com.tirociniotriennale.sitoeventi.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.sql.DataSource;

import static org.springframework.security.config.annotation.web.builders.HttpSecurity.*;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

@Autowired
private DataSource dataSource;

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService) throws Exception {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        return new ProviderManager(authenticationProvider);
    }

    @Bean
    public UserDetailsService userDetailsService() {
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager();
        userDetailsManager.setUsersByUsernameQuery("select user, password, enabled from spring_prova_jpa.utente where user = ?");
        userDetailsManager.setAuthoritiesByUsernameQuery("select user, ruolo from spring_prova_jpa.autorizzazioni where user = ?");
        userDetailsManager.setDataSource(dataSource);
        return userDetailsManager;
    }




    @Bean
    SecurityFilterChain web(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests((authorize)-> authorize
                .requestMatchers("/").permitAll()
                .requestMatchers("/error").permitAll()
                .requestMatchers("/403").permitAll()
                .requestMatchers("/logout").permitAll()
                .requestMatchers("/public/**").permitAll()
                .requestMatchers("/org/**").hasAuthority("org")
                .requestMatchers("/admin/**").hasAuthority("admin")
                .requestMatchers("/user/**").hasAuthority("user")
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                ).formLogin(form -> form
                .loginPage("/login").permitAll().successHandler(myAuthenticationSuccessHandler())).logout((logout)-> logout.logoutUrl("/logout")
                .logoutSuccessUrl("/public/index"))
                .csrf(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler(){
        return new MySimpleUrlAuthenticationSuccessHandler();
    }



   @Bean
   public WebSecurityCustomizer webSecurityCustomizer() {
       return (web) -> web.ignoring().requestMatchers( "/images/**", "/css/**", "/static/**");
    }



}