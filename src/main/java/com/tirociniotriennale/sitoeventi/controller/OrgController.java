package com.tirociniotriennale.sitoeventi.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.tirociniotriennale.sitoeventi.model.*;
import com.tirociniotriennale.sitoeventi.repository.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Controller
public class OrgController {
    @Autowired
    EventoRepository eventoRepository;
    @Autowired
    FaqRepository faqRepository;
    @Autowired
    UtenteRepository utenteRepository;
    @Autowired
    TipologiaRepository tipologiaRepository;

    @GetMapping({"/org/index", "/org", "/org/" })
    public ModelAndView getOrgIndex(@RequestParam(name = "continue", required = false) String continueParam, Model model){
        //Ottengo l'oggetto Authentication corrente

        ModelAndView goi = new ModelAndView("org/index");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //Verifico che l'utente è autenticato
        if (authentication.isAuthenticated()){
            Object principal = authentication.getPrincipal();
            // casting del principal se è un'istanza di UserDetails
            if(principal instanceof UserDetails){
                UserDetails userDetails = (UserDetails) principal;
                String username = userDetails.getUsername();
                model.addAttribute("nomeutente", username);
            }
        }

        return goi;
    }

    @GetMapping({"/org/eventi"})
    public ModelAndView eventiOrganizzazione(Model model){
        boolean presente = false;
        ModelAndView eop = new ModelAndView("org/eventi");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()){
            Object principal = authentication.getPrincipal();
            // casting del principal se è un'istanza di UserDetails
            if(principal instanceof UserDetails){
                UserDetails userDetails = (UserDetails) principal;
                String username = userDetails.getUsername();
                model.addAttribute("nomeutente", username);
                Utente ut = new Utente();
                Optional<Utente> utenteOptional = utenteRepository.findByUser(username);
                ut = utenteOptional.get();
                Iterable<Evento> eventiutente = eventoRepository.findAllByUtenteevento(ut);
                Optional<Evento> eventoSingolo = eventoRepository.findFirstByUtenteevento(ut);

                if(eventoSingolo.isPresent()){
                    presente = true;
                    eop.addObject("tuttieventi", eventiutente);
                }
            }
        }

        if(presente){

        }else{
            model.addAttribute("messaggio", "Nessun evento presente per l'utente selezionato.");
        }
        //prendo gli eventi per l'utente selezionato


        return eop;
    }


    @GetMapping({"/org/faq"})
    public ModelAndView getAllFaqAdmi() {
        ModelAndView gaffad = new ModelAndView("org/faq");
        gaffad.addObject("tuttelefaq", faqRepository.findAll());
        return gaffad;
    }
    @GetMapping({"/org/addevento"})
    public ModelAndView aggiungiEvento(Model model){//Aggiunto Model
        try {
            ModelAndView nev = new ModelAndView("org/aggiungievento");
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
    @RequestMapping(value = "/org/salvaevento", method= RequestMethod.POST)
    public ModelAndView salvaEvento(@Valid @ModelAttribute("eventissimo") Evento eventissimo, BindingResult bindingResult,
                                    Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();



        int idTipo = eventissimo.getTipologia().getIdtipologia();

        if(bindingResult.hasErrors()) {//Nel caso di errore mi perde le tipologie quindi creo ModelAndView

            ModelAndView nan = new ModelAndView("org/aggiungievento");
            Iterable<Tipologia> tipoT = tipologiaRepository.findAll();
            nan.addObject("eventissimo", eventissimo);
            nan.addObject("tipologie", tipoT);
            return nan;

        }

        if (authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            // casting del principal se è un'istanza di UserDetails
            if (principal instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) principal;
                String username = userDetails.getUsername();
                Optional<Utente> utenteLoggato = utenteRepository.findByUser(username);//inserisco il nome dell'utente loggato
                eventissimo.setUtente(utenteLoggato.get());
            }
        }


        tipologiaRepository.findById(idTipo).ifPresent(tipoSelezionato -> {
            eventissimo.setTipologia(tipoSelezionato);
            eventissimo.setBiglietirimanenti(eventissimo.getBigliettimax());
            eventoRepository.save(eventissimo);
        });

        //Aggiunta di attributo "messaggio" al model
        model.addAttribute("messaggio", "Evento  salvato con successo!");
        //In caso di successo faccio una return con evento vuoto!
        ModelAndView nev = new ModelAndView("org/aggiungievento");
        Evento nuovoEvento = new Evento();
        Iterable<Tipologia> tipoT = tipologiaRepository.findAll();
        nev.addObject("eventissimo", nuovoEvento);
        nev.addObject("tipologie", tipoT);
        return nev;

    }

    @PostMapping("/org/cancellaevento")
    public String cancellaEvento(@RequestParam(name = "id") int id, RedirectAttributes redirectAttributes){
        Optional<Evento> eventoSelezionato = eventoRepository.findById(id);
        if(eventoSelezionato.isPresent()){//controllo che l'evento ci sia effettivamente
            eventoRepository.deleteById(id);//cancello l'evento
            redirectAttributes.addFlashAttribute("messaggiosucc","Evento Cancellato!");
        } else {// in caso negativo effettuo un redirect alla pagina org/eventi
            redirectAttributes.addFlashAttribute("messaggiored", "Id evento non trovato!");

        }
        return "redirect:/org/eventi";
    }

    @GetMapping({"/org/modificaevento"})
    public ModelAndView modificaEvento(@RequestParam(name = "id") int id, RedirectAttributes redirectAttributes){
        Evento eventodaModificare = new Evento();
        Optional<Evento> eventoOptional = eventoRepository.findById(id);
        if(eventoOptional.isPresent()){
            eventodaModificare = eventoOptional.get();
        }
        ModelAndView mee = new ModelAndView("org/modificaevento");
        Iterable<Tipologia> tipologie = tipologiaRepository.findAll();

        mee.addObject("eventoscelto", eventodaModificare);
        mee.addObject("tipologie", tipologie);
        redirectAttributes.addFlashAttribute("messaggio", "");
        return mee;
    }

    @PostMapping("/org/salvaeventomodificato")
        public String salvaEventoModificato(@Valid @ModelAttribute("eventoscelto") Evento eventomodificato, BindingResult bindingResult,
        RedirectAttributes redirectAttributes){


       Optional<Evento> eventoOptional = eventoRepository.findById(eventomodificato.getId());
       Evento eventoOriginale = eventoOptional.get();
       int scartobigliettievento = eventoOriginale.getBigliettimax()-eventoOriginale.getBiglietirimanenti();
        if (eventomodificato.getBigliettimax() > scartobigliettievento) {
            bindingResult.rejectValue("bigliettimax", "biglietti.invalidi", "I biglietti massimi non possono essere meno dei biglietti già venduti");
        }
        int bigliettiDaAssegnareRimanenti = eventomodificato.getBigliettimax()- scartobigliettievento;


        if(bindingResult.hasErrors()){
            return "redirect:/org/modificaevento";
        }

        eventoOriginale.setBiglietirimanenti(bigliettiDaAssegnareRimanenti);
        eventoOriginale.setBigliettimax(eventomodificato.getBigliettimax());
        eventoOriginale.setDescbrv(eventomodificato.getDescbrv());
        eventoOriginale.setDesclong(eventomodificato.getDesclong());
        eventoOriginale.setPrezzo(eventomodificato.getPrezzo());
        eventoOriginale.setNomeevento(eventomodificato.getNomeevento());
        eventoOriginale.setNomeimmagine(eventomodificato.getNomeimmagine());

        eventoRepository.save(eventoOriginale);
        redirectAttributes.addFlashAttribute("messaggio", "Evento Modificato con Successo!");
        return "redirect:/org/modificaevento";

    }
}
