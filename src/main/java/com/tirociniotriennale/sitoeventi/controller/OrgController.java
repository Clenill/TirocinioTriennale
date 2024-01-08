package com.tirociniotriennale.sitoeventi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import com.tirociniotriennale.sitoeventi.model.*;
import com.tirociniotriennale.sitoeventi.repository.*;
@Controller
public class OrgController {
    @Autowired
    EventoRepository eventoRepository;
    @Autowired
    FaqRepository faqRepository;

    @GetMapping({"/org/index", "/org", "/org/" })
    public ModelAndView getOrgIndex(){
        ModelAndView goi = new ModelAndView("org/index");
        goi.addObject("tuttieventi", eventoRepository.findAll());
        return goi;
    }


    @GetMapping({"/org/faq"})
    public ModelAndView getAllFaqAdmi() {
        ModelAndView gaffad = new ModelAndView("org/faq");
        gaffad.addObject("tuttelefaq", faqRepository.findAll());
        return gaffad;
    }
}
