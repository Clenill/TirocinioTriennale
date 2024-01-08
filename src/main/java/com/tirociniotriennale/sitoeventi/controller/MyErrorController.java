package com.tirociniotriennale.sitoeventi.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MyErrorController implements ErrorController{

    @RequestMapping("/error")
    public String gestioneErrori(Model model) {
        model.addAttribute("validationErrors", "");
        return "error";
    }
}