package com.tirociniotriennale.sitoeventi.controller;

import com.tirociniotriennale.sitoeventi.model.Utente;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
/*errori gestiti da me
@RequestParam(name = "error", required = false) String error, Model model, HttpServletRequest request
 model.addAttribute("error", error);

        HttpSession session = request.getSession(false);
        String errorMessage = null;
        if (session != null) {
            AuthenticationException ex = (AuthenticationException) session
                    .getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
            if (ex != null) {
                errorMessage = ex.getMessage();
            }
        }
        */



        return "login";
    }


    @GetMapping("/logout")
    public String logout(){
        return "logout";
    }


}