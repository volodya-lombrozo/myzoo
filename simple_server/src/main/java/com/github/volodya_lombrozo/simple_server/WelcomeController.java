package com.github.volodya_lombrozo.simple_server;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;

import java.util.Random;

@Controller
public class WelcomeController {

    private final long id = Math.abs(new Random().nextLong());

    @Get
    @Produces(MediaType.TEXT_PLAIN)
    public String welcome() {
        return String.format("Welcome to Micronaut Based Application%n" +
                "This is a server application%n" +
                "My id is %s%n", id);
    }
}
