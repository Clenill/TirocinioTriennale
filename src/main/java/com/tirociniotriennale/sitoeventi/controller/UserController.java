package com.tirociniotriennale.sitoeventi.controller;

import com.tirociniotriennale.sitoeventi.repository.*;
import com.tirociniotriennale.sitoeventi.service.EventoService;
import com.tirociniotriennale.sitoeventi.service.UserService;
import com.tirociniotriennale.sitoeventi.util.HtmlToPdfConverter;
import jakarta.servlet.http.HttpServletResponse;
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
import java.io.IOException;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Optional;

import static java.util.Spliterators.iterator;

@Controller
public class UserController {

    private final EventoRepository eventoRepository;

    private final UtenteRepository utenteRepository;
    private final FaqRepository faqRepository;

    private final EventoService eventoService;

    private final OrdineRepository ordineRepository;

    private final UserService userService;

    private final TipologiaRepository tipologiaRepository;

    //--------------------------------------------
    @Autowired
    public UserController(EventoService eventoService, UserService userService, FaqRepository faqRepository,
                          EventoRepository eventoRepository, UtenteRepository utenteRepository, OrdineRepository ordineRepository,
                          TipologiaRepository tipologiaRepository){
        // iniezione tramite costruttore

        this.eventoRepository = eventoRepository;
        this.utenteRepository = utenteRepository;
        this.faqRepository = faqRepository;
        this.userService = userService;
        this.eventoService = eventoService;
        this.ordineRepository = ordineRepository;
        this.tipologiaRepository = tipologiaRepository;

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
        Iterable<Evento> eventiperdata = eventoRepository.findAllByOrderByLocalDateAsc();
        gae.addObject("eventidata", eventiperdata);
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
        mava.addObject("tipologie", tipologiaRepository.findAll());
        mava.addObject("tuttiglieventi", eventoRepository.findAll());
        return mava;
    }

    @RequestMapping({"/user/evento/{id}"}) //
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
    //carrello
    @GetMapping({"/user/carrello"})
    public ModelAndView carrelloUser(Model model){
        ModelAndView carr = new ModelAndView("/user/carrello");
        String username = null;
        BigDecimal totaledapagare = new BigDecimal(0);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()){
            Object principal = authentication.getPrincipal();
            if(principal instanceof UserDetails){
                UserDetails userDetails = (UserDetails) principal;
                username = userDetails.getUsername();
                Collection<? extends GrantedAuthority> collectionautorita = userDetails.getAuthorities();
                String primaAut = collectionautorita.iterator().next().getAuthority();
                model.addAttribute("autorita", primaAut);
                model.addAttribute("nomeutente", username);
            }
        }
        // cerco l'utente tramite l'username e poi provvedo a reperire tutti gli ordini
        Optional<Utente> utenteloggato = utenteRepository.findById(username);
        Utente utentelog = utenteloggato.get();

        //prelevo gli ordini, quelli non ancora pagati.
        Iterable<Ordine> ordininonpagati = ordineRepository.findByUtenteAndPagatoFalse(utentelog);

        for(Ordine ordine : ordininonpagati){
            totaledapagare = totaledapagare.add(BigDecimal.valueOf(ordine.getTotpagamento()));
        }

        model.addAttribute("ordiniutente", ordininonpagati);
        model.addAttribute("totaledapagare", totaledapagare);

        return carr;

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
        Iterable<Evento> eventiPerNome = eventoService.cercaPerNome(search);

        if (eventiPerNome == null) {
            // L'iterable è vuoto, aggiungo un messaggio al model
            model.addAttribute("messaggio", "Nessun evento trovato per la ricerca: " + search);
            eventiPerNome = Collections.emptyList();
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
        //setto pagato su false in modo da poter vedere l'ordine pendente nel carrello
        ordine.setPagato(false);
        //prelevo i biglietti e il prezzo e lo converto in float
        BigDecimal biglietti = new BigDecimal(ordine.getBiglietti());
        BigDecimal prezzo = ordine.getEvento().getPrezzo();
        BigDecimal importototale = prezzo.multiply(biglietti);
        float pagamento = importototale.floatValue();
        ordine.setTotpagamento(pagamento);

        ordineRepository.save(ordine);
        redirectAttributes.addFlashAttribute("messaggio", "Ordine effettuato con Successo!");


        return "redirect:/user/evento/"+eventId;
    }

    @RequestMapping(value = "/user/ordini")
    public ModelAndView listaordini(Model model){
        ModelAndView lpu = new ModelAndView("user/ordini");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        UserDetails userDetails= (UserDetails) principal;
        String username = userDetails.getUsername();
        model.addAttribute("nomeutente", username);
        Optional<Utente> utente = utenteRepository.findById(username);
        Iterable<Ordine> ordineUtente = ordineRepository.findByUtente(utente.get());
        Iterable<Ordine> ordinieffettuati = ordineRepository.findByUtenteAndPagatoTrue(utente.get());
        if (!ordineUtente.iterator().hasNext()) {
            // L'iterable è vuoto, aggiungo un messaggio al model
            model.addAttribute("messaggio", "Nessun ordine trovato ");
            return lpu;
        }
        lpu.addObject("ordiniutente", ordinieffettuati);

        return lpu;

    }
    //dettaglio evento
    @RequestMapping("/user/dettaglioordine/{id}")
    public ModelAndView dettaglioEvento(@PathVariable int id, Model model,RedirectAttributes redirectAttributes,
                                        HttpServletResponse response){
        ModelAndView dei = new ModelAndView("user/dettaglio");
        //recupero l'username
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        UserDetails userDetails= (UserDetails) principal;
        String username = userDetails.getUsername();
        model.addAttribute("nomeutente", username);

        //recupero l'ordine tramite l'id
        Optional<Ordine> ordineScelto = ordineRepository.findById(id);
        Ordine ordinescelto = ordineScelto.get();

        dei.addObject("ordinescelto", ordinescelto);

        // Aggiorna il contenuto HTML con i dettagli dell'ordine
        String htmlContent = "<html><body>"
                + "<img src='classpath:/static/images/logo.png' alt='Logo' width='210' height='80'>"
                + "<h2>Dettagli Ordine:</h2>"
                + "<p>Id Ordine: " + ordinescelto.getIdordine() + "</p>"
                + "<p>Ordine effettuato da: " + ordinescelto.getUtente().getUser() + "</p>"
                + "<p>Nome Evento: " + ordinescelto.getEvento().getNomeevento() + "</p>"
                + "<p>L'evento si svolgerà il: " + ordinescelto.getEvento().getLocalDate() + "</p>"
                + "<p>L'evento avrà luogo a: " + ordinescelto.getEvento().getLuogoevento() + "</p>"
                + "<p>Numero Biglietti: " + ordinescelto.getBiglietti() + "</p>"
                + "<p>Pagamento: " + ordinescelto.getTotpagamento() + "</p>"
                + "</body></html>";

        // Chiamata alla funzione di conversione HTML-to-PDF
        HtmlToPdfConverter.convertHtmlToPdf(htmlContent, response);
        try {
            response.getOutputStream().close();
        } catch (IOException e) {
            e.printStackTrace();
        }

       return dei;

    }
    @GetMapping("/user/filtra")
    public ModelAndView filtraUser(@RequestParam(name = "tipologia", required = false) String tipologia
            , Model model){
        ModelAndView fep = new ModelAndView("user/ricerca");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        UserDetails userDetails= (UserDetails) principal;
        String username = userDetails.getUsername();
        model.addAttribute("nomeutente", username);
        Collection<? extends GrantedAuthority> collectionautorita = userDetails.getAuthorities();
        String primaAut = collectionautorita.iterator().next().getAuthority();
        model.addAttribute("autorita", primaAut);

        int idtipologia = 0;

        if(tipologia != null && !tipologia.isEmpty()){
            idtipologia = Integer.parseInt(tipologia);
        }


        if(idtipologia != 0 && idtipologia <= 4){
            Iterable<Evento> tipologiaricerca = eventoRepository.findByTipologiaIdtipologia(idtipologia);
            fep.addObject("tuttiglieventi", tipologiaricerca);
        }else{
            Iterable<Evento> tutti = eventoRepository.findAll();
            fep.addObject("tuttiglieventi", tutti);
        }


        fep.addObject("tipologie", tipologiaRepository.findAll());
        return fep;
    }

    @PostMapping("/user/procedipagamento")
    public String procedipagamento(@RequestParam("nomeutente") String nomeutente, RedirectAttributes redirectAttributes){
        //prelevo l'oggetto utente
        Optional<Utente> utentepagante = utenteRepository.findById(nomeutente);
        Iterable<Ordine> ordinipendenti = ordineRepository.findByUtenteAndPagatoFalse(utentepagante.get());

        // totale da pagare
        for(Ordine ordine : ordinipendenti){
            ordine.setPagato(true);
            ordineRepository.save(ordine);
        }

        if(ordinipendenti.iterator().hasNext()){
            redirectAttributes.addFlashAttribute("messaggio", "Pagamento effettuato con successo!");
        }else {
            redirectAttributes.addFlashAttribute("messaggiored", "Impossibile procedere con il pagamento!");
        }

        return "redirect:/user/carrello";
    }

}
