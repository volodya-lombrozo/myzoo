package com.github.volodya_lombrozo.spring_based;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class MainController {

    private final long id = Math.abs(new Random().nextLong());

    @GetMapping(produces = "text/plain")
    public String welcome() {
        return String.format("Welcome to Spring Based Application%n" +
                "This is a server application%n" +
                "My id is %s%n", id);
    }

}
