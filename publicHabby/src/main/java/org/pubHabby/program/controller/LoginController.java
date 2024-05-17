package org.pubHabby.program.controller;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
public class LoginController {

    @GetMapping("/login")
    public String cutstomLogin(Model model){

        return "";
    }
}
