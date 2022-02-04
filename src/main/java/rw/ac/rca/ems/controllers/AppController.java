package rw.ac.rca.ems.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @GetMapping("/")
    public String welcome(){
        return "Welcome to the app here";
    }
}