package com.tirociniotriennale.sitoeventi.controller;

import com.tirociniotriennale.sitoeventi.model.*;

import com.tirociniotriennale.sitoeventi.repository.AutorizzazioniRepository;
import com.tirociniotriennale.sitoeventi.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class SignupController {
    @Autowired
    private UtenteRepository utenteRepository;
    @Autowired
    private AutorizzazioniRepository autorizzazioniRepository;

    @GetMapping("/public/registrati")
    public ModelAndView registraUtente(Model model) {
        ModelAndView nur = new ModelAndView("public/registrati");
        Utente nuovoUtente = new Utente();
        nur.addObject("nuovoutente", nuovoUtente);
        return nur;
    }

    @RequestMapping(value = "/public/salvautente", method= RequestMethod.POST)
    public ModelAndView salvaUtente(@ModelAttribute Utente utente, Model model, RedirectAttributes redirectAttributes) {
        utente.setEnebled(true);
        Autorizzazioni autor = new Autorizzazioni();
        autor.setRuolo("user");
        autor.setUtenteAut(utente);
        Optional<Utente> cercaSeEsisteUser = utenteRepository.findById(utente.getUser());
        if (cercaSeEsisteUser.isPresent()){
            ModelAndView nur = new ModelAndView("redirect:/public/registrati");
            utente.setUser(null);
            redirectAttributes.addFlashAttribute("messaggiored","Nome utente già esistente," +
                    "impossibile completare l'operazione");
            return nur;

        }


        utenteRepository.save(utente);
        autorizzazioniRepository.save(autor);
        ModelAndView nur = new ModelAndView("redirect:/public/registrati");
        Utente nuovoUtente = new Utente();
        nur.addObject("nuovoutente", nuovoUtente);
        redirectAttributes.addFlashAttribute("messaggio", "Utente Salvato!");
        return nur;
    }

}
