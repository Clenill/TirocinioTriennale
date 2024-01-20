package com.tirociniotriennale.sitoeventi.controller;

import com.tirociniotriennale.sitoeventi.model.Utente;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {

        return "login";
    }


    @GetMapping("/logout")
    public String logout(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //Verifico che l'utente è autenticato
        if (authentication.isAuthenticated()){
            Object principal = authentication.getPrincipal();
            // casting del principal se è un'istanza di UserDetails
            if(principal instanceof UserDetails){
                UserDetails userDetails = (UserDetails) principal;
                String username = userDetails.getUsername();
                Collection<? extends GrantedAuthority> collectionautorita = userDetails.getAuthorities();

                String primaAut = collectionautorita.iterator().next().getAuthority();
                model.addAttribute("autorita", primaAut);

                model.addAttribute("nomeutente", username);

            }
        }


        return "logout";
    }


}