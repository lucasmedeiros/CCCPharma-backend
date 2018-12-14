package br.edu.ufcg.ccc.pharma.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/hello")
public class HelloEndpoint {

    @GetMapping
    public String hello() {
        return "Testing SpringBoot JWT authorisation";
    }
}