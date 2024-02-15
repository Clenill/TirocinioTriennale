package com.tirociniotriennale.sitoeventi.controller;

import com.tirociniotriennale.sitoeventi.repository.*;
import com.tirociniotriennale.sitoeventi.service.EventoService;
import com.tirociniotriennale.sitoeventi.service.UserService;
import com.tirociniotriennale.sitoeventi.util.HtmlToPdfConverter;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
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
    private final UserService userService;
    @Autowired
    private TipologiaRepository tipologiaRepository;

    //--------------------------------------------
    public UserController(EventoService eventoService, UserService userService){// aggiunto costruttore

        this.userService = userService;
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
        BigDecimal biglietti = new BigDecimal(ordine.getBiglietti());
        BigDecimal prezzo = ordine.getEvento().getPrezzo();
        BigDecimal pagamento = biglietti.multiply(prezzo);


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
        if (!ordineUtente.iterator().hasNext()) {
            // L'iterable è vuoto, aggiungo un messaggio al model
            model.addAttribute("messaggio", "Nessun ordine trovato ");
            return lpu;
        }
        lpu.addObject("ordiniutente", ordineUtente);

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
        BigDecimal biglietti = new BigDecimal(ordinescelto.getBiglietti());
        BigDecimal prezzo = ordinescelto.getEvento().getPrezzo();
        BigDecimal pagamento = biglietti.multiply(prezzo);
        dei.addObject("pagamento", pagamento);
        dei.addObject("ordinescelto", ordinescelto);

        // Aggiorna il contenuto HTML con i dettagli dell'ordine
        String htmlContent = "<html><body>"
                + "<h2>Dettagli Ordine:</h2>"
                + "<p>Id Ordine: " + ordinescelto.getIdordine() + "</p>"
                + "<p>Nome Evento: " + ordinescelto.getEvento().getNomeevento() + "</p>"
                + "<p>Numero Biglietti: " + ordinescelto.getBiglietti() + "</p>"
                + "<p>Pagamento: " + pagamento + "</p>"
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

}
