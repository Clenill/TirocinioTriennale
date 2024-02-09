package com.tirociniotriennale.sitoeventi.controller;

import com.tirociniotriennale.sitoeventi.model.*;

import com.tirociniotriennale.sitoeventi.repository.AutorizzazioniRepository;
import com.tirociniotriennale.sitoeventi.repository.UtenteRepository;
import com.tirociniotriennale.sitoeventi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    @Autowired
    private UserService userService;

    @GetMapping("/public/registrati")
    public ModelAndView registraUtente(Model model) {
        ModelAndView nur = new ModelAndView("public/registrati");
        Utente nuovoUtente = new Utente();
        nur.addObject("nuovoutente", nuovoUtente);
        return nur;
    }

    @RequestMapping(value = "/public/salvautente", method= RequestMethod.POST)
    public String salvaUtente(@Valid @ModelAttribute Utente utente, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        Boolean registrato = false;

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("messaggiored", "La password deve avere almeno 3 caratteri");
            return "redirect:/public/registrati";
        }

        registrato = userService.salvaNuovoUtente(utente);

        if(!registrato){
            redirectAttributes.addFlashAttribute("messaggiored","Nome utente già esistente," +
                    "impossibile completare l'operazione");
            return "redirect:/public/registrati";
        }

        redirectAttributes.addFlashAttribute("messaggio", "Utente Salvato!");
        return "redirect:/public/index";
    }

}
