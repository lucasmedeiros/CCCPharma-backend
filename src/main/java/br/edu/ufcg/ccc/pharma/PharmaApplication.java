package br.edu.ufcg.ccc.pharma;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PharmaApplication {

    public static void main(String[] args) {
        SpringApplication.run(PharmaApplication.class, args);
    }

    @Bean
    ObjectMapper myObjectMapper() {
        Hibernate5Module m = new Hibernate5Module();
        m.disable(Hibernate5Module.Feature.USE_TRANSIENT_ANNOTATION);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(m);
        return mapper;
    }
}
