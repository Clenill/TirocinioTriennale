package com.tirociniotriennale.sitoeventi.controller;

import com.tirociniotriennale.sitoeventi.model.Evento;
import com.tirociniotriennale.sitoeventi.repository.EventoRepository;
import com.tirociniotriennale.sitoeventi.repository.FaqRepository;
import com.tirociniotriennale.sitoeventi.repository.UtenteRepository;
import com.tirociniotriennale.sitoeventi.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

    @GetMapping({"/admin/eventi"})
    public ModelAndView getAllEventiAdmin() {
        ModelAndView mavaa = new ModelAndView("admin/eventi");
        mavaa.addObject("tuttiglieventi", eventoReposi.findAll());
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

    @RequestMapping(value = "/admin/partner")
    public String listapartnerAdmin(){

        return "admin/partner";

    }

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



}
