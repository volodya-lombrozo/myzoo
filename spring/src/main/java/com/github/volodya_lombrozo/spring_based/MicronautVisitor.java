package com.github.volodya_lombrozo.spring_based;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

@Component
public class MicronautVisitor {

    private final RestTemplate template = new RestTemplate();
    private final Logger logger = Logger.getLogger("MicronautVisitor Scheduler");
//    private String url = "http://micro:8080/";

    private final DiscoveryClient client;

    @Autowired
    public MicronautVisitor(final DiscoveryClient client) {
        this.client = client;
    }

    @Scheduled(fixedRate = 1000)
    public void goToMicronautServer() {
        String url = serviceUrl();
        if (url == null) {
            logger.info("Url is null");
            return;
        }
        logger.info(String.format("Try to call micronaut server by url %s", url));
        template.postForEntity(url, "", String.class);
    }


    Random rand = new Random();
    public String serviceUrl() {
        client.getServices().forEach(service -> {
            System.out.printf("Service name %s%n", service);
        });
        List<ServiceInstance> list = client.getInstances("/micronaut");
        if (list != null && list.size() > 0) {
            return list.get(rand.nextInt(list.size())).getUri().toString();
        }
        return null;
    }
}
