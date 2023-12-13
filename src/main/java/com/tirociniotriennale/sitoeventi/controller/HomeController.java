package com.tirociniotriennale.sitoeventi.controller;

import com.tirociniotriennale.sitoeventi.model.Evento;
import com.tirociniotriennale.sitoeventi.repository.EventoRepository;
import com.tirociniotriennale.sitoeventi.repository.FaqRepository;
import com.tirociniotriennale.sitoeventi.repository.UtenteRepository;
import com.tirociniotriennale.sitoeventi.service.EventoService;
import com.tirociniotriennale.sitoeventi.service.EventoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;
/*---------------------------------spring.jpa.hibernate.ddl-auto=auto-------------------------------------*/
@Controller
// Introducendo un @RequestMapping("/ticketlove") si crea un percorso path a cascata con le richieste
// di questo HomeController qundi si avrà ticketlove/index? credo.
// @RequestMapping("/ticketlove") ma non c'è differenza
public class HomeController {
    // AGGIUNTO PER PROVARE-------------------
    @Autowired
    private EventoRepository eventorepository;
    @Autowired
    private UtenteRepository utenteRepository;
    @Autowired
    private FaqRepository faqRepository;
    @Autowired
    private final EventoService eventoService;
    //--------------------------------------------
    public HomeController(EventoService eventoService){// aggiunto costruttore
        this.eventoService = eventoService;
    }
    @RequestMapping(value = "/index")
    public String index(){

    return "index";

    }

    @GetMapping({"/", "/index"})
    public  ModelAndView getAllEventPerData(){ // al momento è una semplice getAll
        ModelAndView gau = new ModelAndView("index");
        gau.addObject("tutteventi", eventorepository.findAll());
        return gau;
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
        try {
            ModelAndView nev = new ModelAndView("aggiungievento");
            Evento nuovoEvento = new Evento();
            nev.addObject("eventissimo", nuovoEvento);
            return nev;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    // metodo per salvare un evento
    @RequestMapping(value = "/salvaevento", method=RequestMethod.POST)
    public String salvaEvento(@ModelAttribute Evento evento){

        System.out.println("INIZIO METODO SALVAEVENTO");
        eventorepository.save(evento);
        return "index";
    }

    @GetMapping({"/faq"})
    public ModelAndView getAllFaq() {
        ModelAndView gaf = new ModelAndView("faq");
        gaf.addObject("tuttelefaq", faqRepository.findAll());
        return gaf;
    }


    @RequestMapping(value = "/eventi")
    public String eventi(){

        return "eventi";

    }

    @RequestMapping(value = "/addevento")
    public String addevento(){

        return "aggiungievento";

    }


    @RequestMapping(value = "/partner")
    public String listapartner(){

        return "partner";

    }

    @RequestMapping(value = "/faq")
    public String faq(){

        return "faq";

    }


    @RequestMapping({"/evento/{id}"}) // @RequestMapping?
    public ModelAndView getEventoById(@PathVariable int id) {
        try {
            ModelAndView geid = new ModelAndView("evento");
            Optional<Evento> optionalEvento = eventoService.findById(id);
            optionalEvento.ifPresent(evento -> geid.addObject("eventoselezionato", evento));
            return geid;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }





}
