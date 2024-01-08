package com.tirociniotriennale.sitoeventi.controller;

import com.tirociniotriennale.sitoeventi.repository.AutorizzazioniRepository;
import com.tirociniotriennale.sitoeventi.repository.EventoRepository;
import com.tirociniotriennale.sitoeventi.repository.FaqRepository;
import com.tirociniotriennale.sitoeventi.repository.UtenteRepository;
import com.tirociniotriennale.sitoeventi.service.EventoService;
import com.tirociniotriennale.sitoeventi.service.UserService;
import com.tirociniotriennale.sitoeventi.service.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.tirociniotriennale.sitoeventi.model.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;


@Controller
public class AdminController {
    @Autowired
    private EventoRepository eventoReposi;
    @Autowired
    private UtenteRepository utenteReposi;
    @Autowired
    private FaqRepository faqReposi;
    @Autowired
    private final EventoService eventoServi;
    @Autowired
    private AutorizzazioniRepository autorizzazioniRepository;
    @Autowired
    private UserServiceImpl userService;

    //--------------------------------------------
    public AdminController(EventoService eventoServi){// aggiunto costruttore
        this.eventoServi = eventoServi;
    }

    @GetMapping({"/admin", "/admin/index", "/admin/"})
    public ModelAndView getEventiAdmin(){
        ModelAndView gaea = new ModelAndView("admin/index");
        gaea.addObject("tuttieventi", eventoReposi.findAll());
        return gaea;
    }

    //PAGINA GESTIONE UTENTI AMMINISTRATORE
    @GetMapping("/admin/gestisciutenti")
    public ModelAndView gestisciUtentiAdmin(RedirectAttributes redirectAttributes){
        ModelAndView nur = new ModelAndView("admin/gestisciutenti");
        nur.addObject("tuttiutenti", utenteReposi.findAll());
        redirectAttributes.addFlashAttribute("messaggio", "");
        redirectAttributes.addFlashAttribute("messaggiored", "");
        return nur;
    }
    //ASSOCIATO ALLA MODAL PER CANCELLARE L'UTENTE, CON CONTROLLO
    @PostMapping("/admin/cancellauser")
    public String cancellaUtente(@RequestParam("username") String username, RedirectAttributes redirectAttributes){
        Utente utente1 = new Utente();
        Optional<Utente> utenteSelezionato = utenteReposi.findByUser(username);
        if(utenteSelezionato.isPresent()){
            utente1 = utenteSelezionato.get();
        }
        else {
            redirectAttributes.addFlashAttribute("messaggiored","L'utente non trovato!");
            return "redirect:/admin/gestisciutenti";
        }
        Optional<Evento> eventiDellUtente = eventoReposi.findFirstByUtenteevento(utente1);
        //se sono presenti eventi associati non avviene la cancellazione
        if(eventiDellUtente.isPresent()){
            redirectAttributes.addFlashAttribute("messaggiored","L'utente ha eventi in corso!");
            return "redirect:/admin/gestisciutenti";
        }
        //Se non ci sono eventi allora si procede alla cancellazione
        utenteReposi.deleteById(username);
        redirectAttributes.addFlashAttribute("messaggio","Utente Cancellato!");
        return "redirect:/admin/gestisciutenti";
    }


    @GetMapping({"/admin/eventi"})
    public ModelAndView getAllEventiAdmin(RedirectAttributes redirectAttributes) {
        ModelAndView mavaa = new ModelAndView("admin/eventi");
        mavaa.addObject("tuttiglieventi", eventoReposi.findAll());
        redirectAttributes.addFlashAttribute("messaggio", "");
        redirectAttributes.addFlashAttribute("messaggiored", "");
        return mavaa;
    }

    @RequestMapping({"/admin/evento/{id}"}) // @RequestMapping?
    public ModelAndView getEventoByIdAdmin(@PathVariable int id) {
        try {
            ModelAndView geifa = new ModelAndView("admin/evento");
            Optional<Evento> optionalEvento = eventoServi.findById(id);
            optionalEvento.ifPresent(evento -> geifa.addObject("eventoselezionato", evento));
            return geifa;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }


    @GetMapping({"/admin/faq"})
    public ModelAndView getAllFaqAdmi() {
        ModelAndView gaffad = new ModelAndView("admin/faq");
        gaffad.addObject("tuttelefaq", faqReposi.findAll());
        return gaffad;
    }

    @GetMapping("/admin/registrautente")
    public ModelAndView registraUtenteAdmin(RedirectAttributes redirectAttributes) {
        ModelAndView nura = new ModelAndView("admin/registrautente");
        Utente nuovoUtente = new Utente();
        Autorizzazioni autorizzazioni = new Autorizzazioni();
        nura.addObject("nuovouser", nuovoUtente);
        nura.addObject("autoutente", autorizzazioni);
        redirectAttributes.addFlashAttribute("messaggio", "");
        return nura;
    }

    @RequestMapping(value = "/admin/registrautente", method= RequestMethod.POST)
    public ModelAndView salvaUtenteAdmin(@ModelAttribute Utente utente, BindingResult resultUser, @ModelAttribute Autorizzazioni autor, BindingResult resultAutor,
                                         RedirectAttributes redirectAttributes) {
        utente.setEnebled(true);
        autor.setUtenteAut(utente);

        utenteReposi.save(utente);
        autorizzazioniRepository.save(autor);

        ModelAndView nur = new ModelAndView("redirect:/admin/registrautente");
        Utente nuovoUtente = new Utente();
        Autorizzazioni autorizzazioni = new Autorizzazioni();
        nur.addObject("nuovoutente", nuovoUtente);
        nur.addObject("autoutente", autorizzazioni);
        redirectAttributes.addFlashAttribute("messaggio", "Utente Salvato!");
        return nur;
    }
    //Mappato alla tabella gestioneutente

    @PostMapping("/admin/cancellaevento")
    public String cancellaEvento(@RequestParam("eventId") int eventId, RedirectAttributes redirectAttributes) {
        // Logica per cancellare l'evento con l'ID specificato
        // ...
       Optional<Evento> eventoSelezionato = eventoReposi.findById(eventId);
        if(eventoSelezionato.isPresent()){
            eventoReposi.deleteById(eventId);
        }
        else{
            redirectAttributes.addFlashAttribute("messaggiored", "Evento non Trovato");
            return "redirect:/admin/eventi";
        }
        // Dopo la cancellazione, puoi reindirizzare l'utente alla pagina dell'elenco degli eventi
        redirectAttributes.addFlashAttribute("messaggio", "Evento Cancellato!");

        return "redirect:/admin/eventi";
    }

    //Prendo un evento per modificarlo
    @GetMapping("/admin/modificautente")// se funziona posso provare un post perché più sicuro
    public ModelAndView paginaModificaUtente(@RequestParam ("user") String user, RedirectAttributes redirectAttributes){
        Utente utenteDaModificare = new Utente();
        Optional<Utente> provaUtente = utenteReposi.findByUser(user);
        if(provaUtente.isPresent()){
            utenteDaModificare = provaUtente.get();
        }
        ModelAndView mdu = new ModelAndView("admin/modificautente");
        mdu.addObject("utentedamod", utenteDaModificare);
        redirectAttributes.addFlashAttribute("messaggio", "");
        return mdu;
    }

    @PostMapping("/admin/salvautentemodificato")
    public String modificaUtenteForm(@Valid @ModelAttribute("utentedamod") Utente utente, BindingResult bindingResult,
                                     RedirectAttributes redirectAttributes){
        boolean modifica = false;

        if(bindingResult.hasErrors()) {//Nel caso di errore faccio un redirect e stampo gli errori
            return "redirect:/admin/modificautente";
        }
        // La modifica devo gestirla nel servizio
        modifica = userService.salvaModificheUtente(utente);
        if(modifica){
            redirectAttributes.addFlashAttribute("messaggio", "Utente Modificato con successo!");
            redirectAttributes.addAttribute("user", utente.getUser());
            return "redirect:/admin/modificautente";
        }
        redirectAttributes.addFlashAttribute("messaggiored", "Impossibile Modificare Utente");
        return "redirect:/admin/modificautente";

    }

    /*
    @GetMapping({"/admin/addevento"}) //Cattura l'url può venire anche da un bottone! con th:href=@{/addevento}
    // al suo interno tipo <a th:href.. class=..></a> io l'ho fatto con link per ora
    public ModelAndView aggiungiEventoAdmin(){
        try {
            ModelAndView neva = new ModelAndView("admin/aggiungievento");
            Evento nuovoEvento = new Evento();
            neva.addObject("eventissimo", nuovoEvento);
            return neva;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "/admin/salvaevento", method= RequestMethod.POST)
    public String salvaEventoAdmin(@ModelAttribute Evento evento){

        System.out.println("INIZIO METODO SALVAEVENTO");
        eventoReposi.save(evento);
        return "admin/eventi";
    }
*/


}
