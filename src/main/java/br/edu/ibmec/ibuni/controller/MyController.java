package br.edu.ibmec.ibuni.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/myresource")
public class MyController {

    @GetMapping
    public String getIt() {
        return "Got it!";
    }
}
