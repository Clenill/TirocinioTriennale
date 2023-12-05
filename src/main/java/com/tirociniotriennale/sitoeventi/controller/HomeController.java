package com.tirociniotriennale.sitoeventi.controller;

import com.tirociniotriennale.sitoeventi.model.Evento;
import com.tirociniotriennale.sitoeventi.repository.EventoRepository;
import com.tirociniotriennale.sitoeventi.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
// Introducendo un @RequestMapping("/ticketlove") si crea un percorso path a cascata con le richieste
// di questo HomeController qundi si avrà ticketlove/index? credo.
// @RequestMapping("/ticketlove") ma non c'è differenza
public class HomeController {
    // AGGIUNTO PER PROVARE-------------------
    @Autowired
    private EventoRepository eventorepository;
    private UtenteRepository utenteRepository;
    //--------------------------------------------
    @RequestMapping(value = "/index")
    public String index(){

    return "index";

    }

    @GetMapping({"/eventi"})
    public ModelAndView getAllEventi() {
        ModelAndView mav = new ModelAndView("eventi");
        mav.addObject("tuttiglieventi", eventorepository.findAll());
        return mav;
    }
// aggiungere un evento

    @GetMapping({"/addevento"}) //Cattura l'url può venire anche da un bottone! con th:href=@{/addevento}
    // al suo interno tipo <a th:href.. class=..></a> io l'ho fatto con link per ora
    public ModelAndView aggiungiEvento(){
        ModelAndView nev = new ModelAndView(("aggiungi_evento"));
        Evento nuovoEvento = new Evento();
        nev.addObject("evento", nuovoEvento);
        return nev;
    }

    // metodo per salvare un evento
    @PostMapping({"/salvaEvento"})
    public String salvaEvento(@ModelAttribute Evento evento){
        eventorepository.save(evento);
        return "index";
    }

    @RequestMapping(value = "/eventi")
    public String eventi(){

        return "eventi";

    }

    @RequestMapping(value = "/partner")
    public String partner(){

        return "partner";

    }
    @RequestMapping(value = "/chisiamo")
    public String chisiamo(){

        return "chisiamo";

    }
    @RequestMapping(value = "/faq")
    public String faq(){

        return "faq";

    }

/*
@GetMapping("/index") // FUNZIONA
    public String indexDue(){

        return "index";

    }
*/

}
