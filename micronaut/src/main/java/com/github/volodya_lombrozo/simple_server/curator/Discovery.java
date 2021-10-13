package com.github.volodya_lombrozo.simple_server.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.x.discovery.*;

import java.net.InetAddress;

public class Discovery {

    CuratorFramework framework;


    public Discovery(final CuratorFramework framework) {
        this.framework = framework;
    }

    public void create() {
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            String hostAddress = localHost.getHostAddress();
            ServiceInstance<Object> serviceInstance = ServiceInstance.builder()
                    .address(hostAddress)
                    .port(8080)
                    .name("/micronaut")
                    .uriSpec(new UriSpec("{scheme}://{address}:{port}"))
                    .build();
            ServiceDiscovery<Object> discovery = ServiceDiscoveryBuilder.builder(Object.class)
                    .basePath("/services")
                    .client(framework)
                    .thisInstance(serviceInstance)
                    .build();
            discovery.registerService(serviceInstance);
            discovery.start();
            discovery.registerService(serviceInstance);
            discovery.queryForNames().forEach(service -> {
                System.out.printf("Other service %s%n", service);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
