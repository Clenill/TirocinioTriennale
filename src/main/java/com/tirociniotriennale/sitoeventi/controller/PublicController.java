package com.tirociniotriennale.sitoeventi.controller;

import com.tirociniotriennale.sitoeventi.repository.EventoRepository;
import com.tirociniotriennale.sitoeventi.repository.FaqRepository;
import com.tirociniotriennale.sitoeventi.repository.UtenteRepository;
import com.tirociniotriennale.sitoeventi.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.tirociniotriennale.sitoeventi.model.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collection;
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
    //--------------------------------------------
    public PublicController(EventoService eventoServ){// aggiunto costruttore
        this.eventoServ = eventoServ;
    }

    @GetMapping({"/public", "/public/index", "/public/", "/", "/index"})
    public  ModelAndView getAllEventPublic(Model model, RedirectAttributes redirectAttributes){
        //Verifica dettagli utente se loggato,
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

        ModelAndView gae = new ModelAndView("public/index");
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
        Iterable<Evento> eventiPerNome = eventoRepo.findByNomeevento(search);

        if (!eventiPerNome.iterator().hasNext()) {
            // L'iterable è vuoto, aggiungo un messaggio al model
            model.addAttribute("messaggio", "Nessun evento trovato per la ricerca: " + search);
        }

        mava.addObject("eventicercati", eventiPerNome);
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

}
