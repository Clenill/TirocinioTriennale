package com.tirociniotriennale.sitoeventi.controller;

import com.tirociniotriennale.sitoeventi.repository.EventoRepository;
import com.tirociniotriennale.sitoeventi.repository.FaqRepository;
import com.tirociniotriennale.sitoeventi.repository.TipologiaRepository;
import com.tirociniotriennale.sitoeventi.repository.UtenteRepository;
import com.tirociniotriennale.sitoeventi.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.tirociniotriennale.sitoeventi.model.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Controller
public class PublicController {

    @Autowired
    private EventoRepository eventoRepo;
    @Autowired
    private UtenteRepository utenteRepo;
    @Autowired
    private FaqRepository faqRepo;
    @Autowired
    private final EventoService eventoServ;
    @Autowired
    private TipologiaRepository tipologiaRepository;
    //--------------------------------------------
    public PublicController(EventoService eventoServ){// aggiunto costruttore
        this.eventoServ = eventoServ;
    }

    @GetMapping({"/public", "/public/index", "/public/", "/", "/index"})
    public  ModelAndView getAllEventPublic(Model model, RedirectAttributes redirectAttributes){

        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();

        if (authentication.isAuthenticated()){
            Object principal = authentication.getPrincipal();
            if(principal instanceof UserDetails){
                UserDetails userDetails = (UserDetails) principal;
                String username = userDetails.getUsername();
                Collection<? extends GrantedAuthority> collectionautorita = userDetails.getAuthorities();

                String primaAut = collectionautorita.iterator().next().getAuthority();
                model.addAttribute("autorita", primaAut);

                model.addAttribute("nomeutente", username);

            }
        }

        ModelAndView gae = new ModelAndView("public/index");

        Iterable<Evento> eventiperdata = eventoRepo.findAllByOrderByLocalDateAsc();
        gae.addObject("eventidata", eventiperdata);
        gae.addObject("tuttieventi", eventoRepo.findAll());
        return gae;
    }

    @GetMapping({"/public/eventi"})
    public ModelAndView getAllEventiPublic(Model model) {
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
        ModelAndView mava = new ModelAndView("public/eventi");
        mava.addObject("tipologie", tipologiaRepository.findAll());
        mava.addObject("tuttiglieventi", eventoRepo.findAll());
        return mava;
    }


    @RequestMapping({"/public/evento/{id}"}) // @RequestMapping?
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
            ModelAndView gei = new ModelAndView("public/evento");
            Optional<Evento> optionalEvento = eventoServ.findById(id);
            optionalEvento.ifPresent(evento -> gei.addObject("eventoselezionato", evento));
            return gei;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }


    //Implementazione tasto cerca
    @GetMapping({"/public/cerca"})
    public ModelAndView cercaEventi(@RequestParam("search")String search, Model model) {
        ModelAndView mava = new ModelAndView("public/cerca");
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


        Iterable<Evento> ricercaParzialeNome = eventoServ.cercaPerNome(search);

        if (ricercaParzialeNome == null) {
            // L'iterable è vuoto, aggiungo un messaggio al model
            model.addAttribute("messaggio", "Nessun evento trovato per la ricerca: " + search);
            ricercaParzialeNome = Collections.emptyList();// se la ricerca è null aggiungo una lista vuota
        }

        mava.addObject("eventicercati", ricercaParzialeNome);
        return mava;
    }



    @GetMapping({"/public/faq"})
    public ModelAndView getAllFaqPublic(Model model) {
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
        ModelAndView gaffa = new ModelAndView("public/faq");
        gaffa.addObject("tuttelefaq", faqRepo.findAll());
        return gaffa;
    }

    //tasto filtra in eventi
    @GetMapping("/public/filtra")
    public ModelAndView filtraPublic(@RequestParam(name = "tipologia", required = false) String tipologia
           , Model model){

        ModelAndView fep = new ModelAndView("public/ricerca");
        int idtipologia = 0;

        if(tipologia != null && !tipologia.isEmpty()){
            idtipologia = Integer.parseInt(tipologia);
        }

        //se tipologia = 0 allora non si applica nessun filtro e voglio tutti gli eventi

        if(idtipologia != 0){
            Iterable<Evento> eventipertipologiaricerca = eventoServ.getEventiPerIdTipologia(idtipologia);
            fep.addObject("tuttiglieventi", eventipertipologiaricerca);

        }else{
            Iterable<Evento> tutti = eventoRepo.findAll();
            fep.addObject("tuttiglieventi", tutti);
        }


        fep.addObject("tipologie", tipologiaRepository.findAll());
        return fep;
    }

}
