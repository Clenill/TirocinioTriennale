package com.tirociniotriennale.sitoeventi.controller;

import com.tirociniotriennale.sitoeventi.repository.*;
import com.tirociniotriennale.sitoeventi.service.EventoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.tirociniotriennale.sitoeventi.model.*;

import java.util.Optional;
/*---------------------------------spring.jpa.hibernate.ddl-auto=auto-------------------------------------*/
@Controller
// Introducendo un @RequestMapping("/ticketlove") si crea un percorso path a cascata con le richieste
// di questo HomeController qundi si avrà ticketlove/index?, da testare.
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
    @Autowired
    private TipologiaRepository tipologiaRepository;
    //--------------------------------------------
    public HomeController(EventoService eventoService){// aggiunto costruttore
        this.eventoService = eventoService;
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
    public ModelAndView aggiungiEvento(Model model){//Aggiunto Model
        try {
            ModelAndView nev = new ModelAndView("aggiungievento");
            Evento nuovoEvento = new Evento();
            Iterable<Tipologia> tipoT = tipologiaRepository.findAll();
            model.addAttribute("validationErrors", ""); // Inizializza con stringa vuota
            nev.addObject("eventissimo", nuovoEvento);
            nev.addObject("tipologie", tipoT);
            return nev;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    // metodo per salvare un evento
    @RequestMapping(value = "/salvaevento", method=RequestMethod.POST)
    public ModelAndView salvaEvento(@Valid @ModelAttribute("eventissimo") Evento eventissimo, BindingResult bindingResult,
                              Model model){
        int idTipo = eventissimo.getTipologia().getIdtipologia();

        if(bindingResult.hasErrors()) {//Nel caso di errore mi perde le tipologie quindi creo ModelAndView

            ModelAndView nan = new ModelAndView("aggiungievento");
            Iterable<Tipologia> tipoT = tipologiaRepository.findAll();
            nan.addObject("eventissimo", eventissimo);
            nan.addObject("tipologie", tipoT);
            return nan;
            //return"aggiungievento";
        }


        tipologiaRepository.findById(idTipo).ifPresent(tipoSelezionato -> {
            eventissimo.setTipologia(tipoSelezionato);
            eventissimo.setBiglietirimanenti(eventissimo.getBigliettimax());
            eventorepository.save(eventissimo);
        });

        //Aggiunta di attributo "messaggio" al model
        model.addAttribute("messaggio", "Evento  salvato con successo!");
        //In caso di successo faccio una return con evento vuoto!
        ModelAndView nev = new ModelAndView("aggiungievento");
        Evento nuovoEvento = new Evento();
        Iterable<Tipologia> tipoT = tipologiaRepository.findAll();
        nev.addObject("eventissimo", nuovoEvento);
        nev.addObject("tipologie", tipoT);
        return nev;
        //return "aggiungievento";
    }

    @GetMapping({"/faq"})
    public ModelAndView getAllFaq() {
        ModelAndView gaf = new ModelAndView("faq");
        gaf.addObject("tuttelefaq", faqRepository.findAll());
        return gaf;
    }



    @RequestMapping(value = "/partner")
    public String listapartner(){

        return "partner";

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

    @RequestMapping("/403")
    public String accessDenied() {
        return "403";
    }


}
