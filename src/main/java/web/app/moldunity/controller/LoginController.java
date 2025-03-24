package web.app.moldunity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import web.app.moldunity.util.SecurityUtil;

@RestController
public class LoginController {
    @GetMapping(value = "/login")
    public ModelAndView login(){
        return new ModelAndView("login");
    }

    @GetMapping(value = "/authenticate")
    public Boolean authenticate(){
        return !SecurityUtil.getUsername().equals("anonymousUser");
    }
}











