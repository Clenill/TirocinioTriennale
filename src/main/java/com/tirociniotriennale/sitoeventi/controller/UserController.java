package com.tirociniotriennale.sitoeventi.controller;

import com.tirociniotriennale.sitoeventi.model.Evento;
import com.tirociniotriennale.sitoeventi.repository.EventoRepository;
import com.tirociniotriennale.sitoeventi.repository.FaqRepository;
import com.tirociniotriennale.sitoeventi.repository.UtenteRepository;
import com.tirociniotriennale.sitoeventi.service.EventoService;
import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
    //--------------------------------------------
    public UserController(EventoService eventoService){// aggiunto costruttore
        this.eventoService = eventoService;
    }

    @RequestMapping(value = "/user/index", method= RequestMethod.GET)
    public ModelAndView getEventiUser(){
        ModelAndView gae = new ModelAndView("user/index");
        gae.addObject("tuttieventi", eventoRepository.findAll());
        return gae;
    }

    @GetMapping({"/user/eventi"})
    public ModelAndView getAllEventiUser() {
        ModelAndView mava = new ModelAndView("user/eventi");
        mava.addObject("tuttiglieventi", eventoRepository.findAll());
        return mava;
    }

    @RequestMapping({"/user/evento/{id}"}) // @RequestMapping?
    public ModelAndView getEventoByIdUser(@PathVariable int id) {
        try {
            ModelAndView geif = new ModelAndView("user/evento");
            Optional<Evento> optionalEvento = eventoService.findById(id);
            optionalEvento.ifPresent(evento -> geif.addObject("eventoselezionato", evento));
            return geif;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }


    @GetMapping({"/user/faq"})
    public ModelAndView getAllFaqUser() {
        ModelAndView gaffa = new ModelAndView("user/faq");
        gaffa.addObject("tuttelefaq", faqRepository.findAll());
        return gaffa;
    }

    @RequestMapping(value = "/user/partner")
    public String listapartnerUser(){

        return "public/partner";

    }
}
