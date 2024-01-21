package com.tirociniotriennale.sitoeventi.controller;

import com.tirociniotriennale.sitoeventi.repository.EventoRepository;
import com.tirociniotriennale.sitoeventi.repository.FaqRepository;
import com.tirociniotriennale.sitoeventi.repository.OrdineRepository;
import com.tirociniotriennale.sitoeventi.repository.UtenteRepository;
import com.tirociniotriennale.sitoeventi.service.EventoService;
import com.tirociniotriennale.sitoeventi.service.UserService;
import jakarta.validation.Valid;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.tirociniotriennale.sitoeventi.model.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collection;
import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    private EventoRepository eventoRepository;
    @Autowired
    private UtenteRepository utenteRepository;
    @Autowired
    private FaqRepository faqRepository;
    @Autowired
    private final EventoService eventoService;
    @Autowired
    private OrdineRepository ordineRepository;
    @Autowired
    private UserService userService;
    //--------------------------------------------
    public UserController(EventoService eventoService){// aggiunto costruttore
        this.eventoService = eventoService;
    }

    @GetMapping({"/user", "/user/index", "/user/"})
    public ModelAndView getEventiUser(Model model){
        ModelAndView gae = new ModelAndView("user/index");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.isAuthenticated()){
            Object principal = authentication.getPrincipal();
            if(principal instanceof UserDetails){
                UserDetails userDetails= (UserDetails) principal;
                String username = userDetails.getUsername();
                model.addAttribute("nomeutente", username);
            }
        }

        gae.addObject("tuttieventi", eventoRepository.findAll());
        return gae;
    }

    @GetMapping({"/user/eventi"})
    public ModelAndView getAllEventiUser(Model model) {
        ModelAndView mava = new ModelAndView("user/eventi");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.isAuthenticated()){
            Object principal = authentication.getPrincipal();
            if(principal instanceof UserDetails){
                UserDetails userDetails= (UserDetails) principal;
                String username = userDetails.getUsername();
                model.addAttribute("nomeutente", username);
            }
        }
        mava.addObject("tuttiglieventi", eventoRepository.findAll());
        return mava;
    }

    @RequestMapping({"/user/evento/{id}"}) // @RequestMapping?
    public ModelAndView getEventoByIdUser(@PathVariable int id, Model model) {
        try {
            ModelAndView geif = new ModelAndView("user/evento");
            Ordine ordine = new Ordine();
            geif.addObject("ordineevento", ordine);
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if(authentication.isAuthenticated()){
                Object principal = authentication.getPrincipal();
                if(principal instanceof UserDetails){
                    UserDetails userDetails= (UserDetails) principal;
                    String username = userDetails.getUsername();
                    model.addAttribute("nomeutente", username);
                    Collection<? extends GrantedAuthority> collectionautorita = userDetails.getAuthorities();

                    String primaAut = collectionautorita.iterator().next().getAuthority();
                    model.addAttribute("autorita", primaAut);
                }
            }
            Optional<Evento> optionalEvento = eventoService.findById(id);
            optionalEvento.ifPresent(evento -> geif.addObject("eventoselezionato", evento));
            return geif;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }


    @GetMapping({"/user/faq"})
    public ModelAndView getAllFaqUser(Model model) {
        ModelAndView gaffa = new ModelAndView("user/faq");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.isAuthenticated()){
            Object principal = authentication.getPrincipal();
            if(principal instanceof UserDetails){
                UserDetails userDetails= (UserDetails) principal;
                String username = userDetails.getUsername();
                model.addAttribute("nomeutente", username);
            }
        }
        gaffa.addObject("tuttelefaq", faqRepository.findAll());
        return gaffa;
    }

    @GetMapping({"/user/profilo"})
    public ModelAndView getProfilo(Model model) {
        ModelAndView gaffa = new ModelAndView("user/profilo");
        Utente utenteloggato = new Utente();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.isAuthenticated()){
            Object principal = authentication.getPrincipal();
            if(principal instanceof UserDetails){
                UserDetails userDetails= (UserDetails) principal;
                String username = userDetails.getUsername();
                model.addAttribute("nomeutente", username);
                Optional<Utente> cercautente = utenteRepository.findById(username);

                if(cercautente.isPresent()){
                    utenteloggato=cercautente.get();
                    gaffa.addObject("utenteloggato", utenteloggato);
                }

            }
        }


        gaffa.addObject("tuttelefaq", faqRepository.findAll());
        return gaffa;
    }
    //view form modifica utente
    @GetMapping("/user/modificadatiutente")// se funziona posso provare un post perché più sicuro
    public ModelAndView paginaModificaUser(RedirectAttributes redirectAttributes,Model model){
        Utente utenteloggato = new Utente();
        ModelAndView mdu = new ModelAndView("user/modificadatiutente");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.isAuthenticated()){
            Object principal = authentication.getPrincipal();
            if(principal instanceof UserDetails){
                UserDetails userDetails= (UserDetails) principal;
                String username = userDetails.getUsername();
                model.addAttribute("nomeutente", username);
                Optional<Utente> cercautente = utenteRepository.findById(username);

                if(cercautente.isPresent()){
                    utenteloggato=cercautente.get();

                }
            }
        }
        mdu.addObject("utentedamod", utenteloggato);
        return mdu;
    }

    //salva le modifiche all'utente
    @PostMapping("/user/salvamodificheutente")
    public String modificaUserForm(@Valid @ModelAttribute("utentedamod") Utente utente, BindingResult bindingResult,
                                     RedirectAttributes redirectAttributes){
        boolean modifica = false;

        if(bindingResult.hasErrors()) {//Nel caso di errore faccio un redirect e stampo gli errori
            redirectAttributes.addFlashAttribute("messaggiored", "Impossibile effettuare Modificare");
            return "redirect:/user/modificadatiutente";
        }
        // La modifica devo gestirla nel servizio
        modifica = userService.salvaModificheUtente(utente);
        if(modifica){
            redirectAttributes.addFlashAttribute("messaggio", "Modifiche effettuate!");
            return "redirect:/user/profilo";
        }
        redirectAttributes.addFlashAttribute("messaggiored", "Impossibile effettuare Modificare");
        return "redirect:/user/modificadatiutente";

    }
    //tasto cerca

    @GetMapping({"/user/cerca"})
    public ModelAndView cercaUserEventi(@RequestParam("search")String search, Model model) {
        ModelAndView mava = new ModelAndView("user/cerca");
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
        Iterable<Evento> eventiPerNome = eventoRepository.findByNomeevento(search);

        if (!eventiPerNome.iterator().hasNext()) {
            // L'iterable è vuoto, aggiungo un messaggio al model
            model.addAttribute("messaggio", "Nessun evento trovato per la ricerca: " + search);
        }

        mava.addObject("eventicercati", eventiPerNome);
        return mava;
    }
    // acquisto biglietti

    @PostMapping("/user/acquistoevento")
    public String acquistoBigliettiEvento(@ModelAttribute("ordineevento") Ordine ordine,@RequestParam("eventId") int eventId,
                                          RedirectAttributes redirectAttributes){
        //prelevo l'evento
        Evento eventoacquisto = new Evento();
        Optional<Evento> prelevaEvento = eventoRepository.findById(eventId);
        if(prelevaEvento.isPresent()){
            eventoacquisto=prelevaEvento.get();
        }
        // controllo che l'ordine sia positivo e che non ecceda i biglietti disponibili
        if(ordine.getBiglietti() <= 0 || ordine.getBiglietti() > eventoacquisto.getBiglietirimanenti()){
            redirectAttributes.addFlashAttribute("messaggiored", "Numero biglietti non permesso");
            return "redirect:/user/evento/"+eventId;
        }

        Utente utenteOrdine = new Utente();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Object principal = authentication.getPrincipal();

        UserDetails userDetails = (UserDetails) principal;
        String username = userDetails.getUsername();
        Optional<Utente> utenteLoggato = utenteRepository.findById(username);
        if(utenteLoggato.isPresent()){
            //assegno l'utente se presente per legarlo all'ordine
            utenteOrdine =utenteLoggato.get();
        }
        eventoacquisto.setBiglietirimanenti(eventoacquisto.getBiglietirimanenti() - ordine.getBiglietti());
        eventoRepository.save(eventoacquisto);
        ordine.setEvento(eventoacquisto);
        ordine.setUtente(utenteOrdine);
        ordineRepository.save(ordine);
        redirectAttributes.addFlashAttribute("messaggio", "Ordine effettuato con Successo!");

        return "redirect:/user/evento/"+eventId;
    }

    @RequestMapping(value = "/user/partner")
    public String listapartnerUser(){

        return "public/partner";

    }
}
