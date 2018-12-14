package br.edu.ufcg.ccc.pharma.endpoint;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/hello")
public class HelloEndpoint {

    @GetMapping
    public String hello() {
        return "Hello World, I'm testing SpringBoot =)";
    }
}