package com.tirociniotriennale.sitoeventi.controller;

import com.tirociniotriennale.sitoeventi.service.EventoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
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
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

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
    @Autowired
    EventoService eventoServ;
    @Autowired
    OrdineRepository ordineRepository;

    @GetMapping({"/org/index", "/org", "/org/" })
    public ModelAndView getOrgIndex(@RequestParam(name = "continue", required = false) String continueParam, Model model, RedirectAttributes redirectAttributes){
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

    @RequestMapping({"/org/evento/{id}"}) // @RequestMapping?
    public ModelAndView getEventoByIdPublic(@PathVariable int id, Model model) {
        try {
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
            ModelAndView gei = new ModelAndView("org/evento");
            Optional<Evento> optionalEvento = eventoServ.findById(id);
            optionalEvento.ifPresent(evento -> gei.addObject("eventoselezionato", evento));
            return gei;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
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
    public String salvaEvento(@Valid @ModelAttribute("eventissimo") Evento eventissimo, BindingResult bindingResult,
                                    Model model, RedirectAttributes redirectAttributes){
        LocalDate dataodierna = LocalDate.now();
        Iterable<Tipologia> tipoT = tipologiaRepository.findAll();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int idTipo = eventissimo.getTipologia().getIdtipologia();

        if(eventissimo.getLocalDate().isBefore(dataodierna)){
            redirectAttributes.addFlashAttribute("messaggiodata", "La data deve essere successiva a oggi!");
            eventissimo.setLocalDate(null);
            model.addAttribute("eventissimo", eventissimo);
            model.addAttribute("tipologie", tipoT);
            return "redirect:/org/addevento";
        }



        if(bindingResult.hasErrors()) {//Nel caso di errore mi perde le tipologie quindi creo ModelAndView



            eventissimo.setLocalDate(null);
            redirectAttributes.addFlashAttribute("eventissimo", eventissimo);
            redirectAttributes.addFlashAttribute("tipologie", tipoT);
            return "redirect:/org/addevento";

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

        if(eventissimo.getNomeimmagine() == null || eventissimo.getNomeimmagine().isEmpty()){
            eventissimo.setNomeimmagine("default.jpg");
        }

        tipologiaRepository.findById(idTipo).ifPresent(tipoSelezionato -> {
            eventissimo.setTipologia(tipoSelezionato);
            eventissimo.setBiglietirimanenti(eventissimo.getBigliettimax());
            eventoRepository.save(eventissimo);
        });

        //Aggiunta di attributo "messaggio" al model
        redirectAttributes.addFlashAttribute("messaggiogreen", "Evento salvato con successo!");
        //In caso di successo faccio una return con evento vuoto!

        return "redirect:/org/index";

    }

    @PostMapping("/org/cancellaevento")
    public String cancellaEvento(@RequestParam(name = "id") int id, RedirectAttributes redirectAttributes){
        Optional<Evento> eventoSelezionato = eventoRepository.findById(id);
        if(eventoSelezionato.isPresent()){//controllo che l'evento ci sia effettivamente
            Evento evento = eventoSelezionato.get();
            Set<Ordine> ordini = evento.getOrdini();
            for (Ordine ordine : ordini) {
                ordineRepository.delete(ordine);
            }
            eventoRepository.deleteById(id);//cancello l'evento
            redirectAttributes.addFlashAttribute("messaggiosucc","Evento Cancellato!");
        } else {// in caso negativo effettuo un redirect alla pagina org/eventi
            redirectAttributes.addFlashAttribute("messaggiored", "Id evento non trovato!");

        }
        return "redirect:/org/eventi";
    }

    @GetMapping({"/org/modificaevento"})
    public ModelAndView modificaEvento(@RequestParam(name = "id") int id, RedirectAttributes redirectAttributes,
        Model model){
        Evento eventodaModificare = new Evento();
        Optional<Evento> eventoOptional = eventoRepository.findById(id);
        if(eventoOptional.isPresent()){
            eventodaModificare = eventoOptional.get();
        }
        String dataevento = eventodaModificare.getLocalDate().toString();


        ModelAndView mee = new ModelAndView("org/modificaevento");
        Iterable<Tipologia> tipologie = tipologiaRepository.findAll();
        model.addAttribute("dataevento", dataevento);
        mee.addObject("eventoscelto", eventodaModificare);
        mee.addObject("tipologie", tipologie);
        redirectAttributes.addFlashAttribute("messaggio", "");
        return mee;
    }

    @PostMapping("/org/salvaeventomodificato")
        public String salvaEventoModificato(@Valid @ModelAttribute("eventoscelto") Evento eventomodificato, BindingResult bindingResult,
        RedirectAttributes redirectAttributes){
        //recurero l'utente loggato
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            // casting del principal se è un'istanza di UserDetails
            if (principal instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) principal;
                String username = userDetails.getUsername();
                Optional<Utente> utenteLoggato = utenteRepository.findByUser(username);//inserisco il nome dell'utente loggato
                eventomodificato.setUtente(utenteLoggato.get());
            }
        }
        //prendo idtipologia per assegnazione Tipologia
        int tipoid = eventomodificato.getTipologia().getIdtipologia();

        //recupero l'evento non ancora modificato per avere biglietti massimi e rimanenti prima della modificia
       Optional<Evento> eventoOptional = eventoRepository.findById(eventomodificato.getId());
       // vado a creare la variabile bigliettiassegnati perché i nuovi biglietti max non possono essere inferiori ai biglietti già assegnati
       int bigliettiassegnati = eventoOptional.get().getBigliettimax() - eventoOptional.get().getBiglietirimanenti();
        if (eventomodificato.getBigliettimax() < bigliettiassegnati) {
            bindingResult.rejectValue("bigliettimax", "biglietti.invalidi", "I biglietti massimi non possono essere meno dei biglietti già venduti");
        }

        LocalDate dataodierna = LocalDate.now();
        if(eventomodificato.getLocalDate().isBefore(dataodierna)){
            redirectAttributes.addFlashAttribute("messaggiored","La data deve essere successiva a oggi!");
            return "redirect:/org/modificaevento?id="+eventomodificato.getId();
        }

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("messaggiored","Modifiche non permesse!");
            return "redirect:/org/modificaevento?id="+eventomodificato.getId();
        }

        int nuovobigliettirim = eventomodificato.getBigliettimax() - bigliettiassegnati;
        eventomodificato.setBiglietirimanenti(nuovobigliettirim);
        eventomodificato.setOrdini(eventoOptional.get().getOrdini());
        //iterazione sugli ordini
        for(Ordine ordine : eventomodificato.getOrdini()){
            ordine.setEvento(eventomodificato);
        }
        //assegno tipologia scelta
        tipologiaRepository.findById(tipoid).ifPresent( tiposelezionao ->
                eventomodificato.setTipologia(tiposelezionao));

        eventoRepository.save(eventomodificato);
        redirectAttributes.addFlashAttribute("messaggiosucc", "Evento Modificato con Successo!");
        return "redirect:/org/eventi";

    }
}
