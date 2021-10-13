package com.github.volodya_lombrozo.simple_server;

import com.github.volodya_lombrozo.simple_server.curator.CuratorConnection;
import com.github.volodya_lombrozo.simple_server.curator.Discovery;
import io.micronaut.runtime.Micronaut;
import org.apache.curator.framework.CuratorFramework;

import java.util.logging.Logger;

public class Application {

    private static final Logger logger = Logger.getLogger("Micronaut Application");

    public static void main(String[] args) throws InterruptedException {
        CuratorFramework connect = new CuratorConnection().connection();
        connect.start();
        connect.blockUntilConnected();
        new Discovery(connect).create();
        Micronaut.run(Application.class, args);
    }

}
