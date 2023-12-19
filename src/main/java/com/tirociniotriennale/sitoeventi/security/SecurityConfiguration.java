package com.tirociniotriennale.sitoeventi.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.DefaultSecurityFilterChain;

import static org.springframework.security.config.annotation.web.builders.HttpSecurity.*;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/login").permitAll()
                        .anyRequest().authenticated() //.anyRequest().authenticated()
                ).formLogin(formLogin -> formLogin
                      /*  .loginPage("/login")*/
                        .permitAll());

        return http.build();
    }



    /*
        @Bean
        public SecurityFilterChain securityFilterChainAdmin(HttpSecurity http) throws Exception {
            http.authorizeRequests().requestMatchers("/admin").hasRole("admin").anyRequest().authenticated()
                    .and().formLogin(formLogin -> formLogin
                            .loginPage("/login")
                            .permitAll());
            return http.build();
        }

        @Bean
        public SecurityFilterChain securityFilterChainUser(HttpSecurity http) throws Exception {
            http.authorizeRequests().requestMatchers("/user").hasRole("user").anyRequest().authenticated()
                    .and().formLogin(formLogin -> formLogin
                            .loginPage("/login")
                            .permitAll());
            return http.build();
        }

        @Bean
        public SecurityFilterChain securityFilterChainOrg(HttpSecurity http) throws Exception {
            http.authorizeRequests().requestMatchers("/org").hasRole("org").anyRequest().authenticated()
                    .and().formLogin(formLogin -> formLogin
                            .loginPage("/login")
                            .permitAll());
            return http.build();
        }

        @Bean
        public SecurityFilterChain securityFilterChainPublic(HttpSecurity http) throws Exception {
            http.authorizeRequests().requestMatchers("/public").permitAll()
                    .anyRequest().permitAll();

            return http.build();
        }
    */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/webjars/**", "/images/**", "/css/**", "/static/**");
    }


    protected void configure (AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);//l'utente che fa login usa questo service
        //implementatao in service
    }





}