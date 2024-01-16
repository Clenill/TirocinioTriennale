package com.tirociniotriennale.sitoeventi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.tirociniotriennale.sitoeventi.model.*;
import com.tirociniotriennale.sitoeventi.repository.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
public class OrgController {
    @Autowired
    EventoRepository eventoRepository;
    @Autowired
    FaqRepository faqRepository;

    @GetMapping({"/org/index", "/org", "/org/" })
    public ModelAndView getOrgIndex(@RequestParam(name = "continue", required = false) String continueParam){
        //Ottengo l'oggetto Authentication corrente
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //Verifico che l'utente è autenticato
        if (authentication.isAuthenticated()){
            Object principal = authentication.getPrincipal();
            // casting del principal se è un'istanza di UserDetails
            if(principal instanceof UserDetails){
                UserDetails userDetails = (UserDetails) principal;
                String username = userDetails.getUsername();
            }
        }
        ModelAndView goi = new ModelAndView("org/index");
        goi.addObject("tuttieventi", eventoRepository.findAll());
        return goi;
    }


    @GetMapping({"/org/faq"})
    public ModelAndView getAllFaqAdmi() {
        ModelAndView gaffad = new ModelAndView("org/faq");
        gaffad.addObject("tuttelefaq", faqRepository.findAll());
        return gaffad;
    }
}
