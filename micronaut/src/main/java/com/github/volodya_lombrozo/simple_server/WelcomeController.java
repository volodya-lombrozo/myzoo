package com.github.volodya_lombrozo.simple_server;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

@Controller
public class WelcomeController {

    private final long id = Math.abs(new Random().nextLong());
    private final AtomicLong visitors = new AtomicLong(0);

    @Get
    @Produces(MediaType.TEXT_PLAIN)
    public String welcome() {
        return String.format("Welcome to Micronaut Based Application%n" +
                "This is a server application%n" +
                "My id is %s%n" +
                "My POST method 'increment' was called %s times", id, visitors.get());
    }


    @Post
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String increment() {
        return String.valueOf(visitors.incrementAndGet());
    }
}
