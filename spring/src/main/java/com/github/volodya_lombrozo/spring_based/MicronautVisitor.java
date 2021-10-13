package com.github.volodya_lombrozo.spring_based;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

@Component
public class MicronautVisitor {

    private final RestTemplate template = new RestTemplate();
    private final Logger logger = Logger.getLogger("MicronautVisitor Scheduler");
    private String url = "http://micro:8080/";

    @Scheduled(fixedRate = 1000)
    public void goToMicronautServer() {
        logger.info(String.format("Try to call micronaut server by url %s", url));
        template.postForEntity(url, "", String.class);
    }

}
