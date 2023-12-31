package com.tirociniotriennale.sitoeventi.controller;

import com.tirociniotriennale.sitoeventi.repository.EventoRepository;
import com.tirociniotriennale.sitoeventi.repository.FaqRepository;
import com.tirociniotriennale.sitoeventi.repository.UtenteRepository;
import com.tirociniotriennale.sitoeventi.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.tirociniotriennale.sitoeventi.model.*;

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

    @GetMapping({"/public", "/public/index", "/public/"})
    public  ModelAndView getAllEventPublic(Model model){
        ModelAndView gae = new ModelAndView("public/index");
        model.addAttribute("messaggio", "");
        gae.addObject("tuttieventi", eventoRepo.findAll());
        return gae;
    }

    @GetMapping({"/public/eventi"})
    public ModelAndView getAllEventiPublic() {
        ModelAndView mava = new ModelAndView("public/eventi");
        mava.addObject("tuttiglieventi", eventoRepo.findAll());
        return mava;
    }


    @RequestMapping({"/public/evento/{id}"}) // @RequestMapping?
    public ModelAndView getEventoByIdPublic(@PathVariable int id) {
        try {
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
        mava.addObject("eventicercati", eventoRepo.findAll());
        model.addAttribute("messaggio","prova model");
        return mava;
    }



    @GetMapping({"/public/faq"})
    public ModelAndView getAllFaqPublic() {
        ModelAndView gaffa = new ModelAndView("public/faq");
        gaffa.addObject("tuttelefaq", faqRepo.findAll());
        return gaffa;
    }



    @RequestMapping(value = "/public/partner")
    public String listapartnerPublic(){

        return "public/partner";

    }

}
