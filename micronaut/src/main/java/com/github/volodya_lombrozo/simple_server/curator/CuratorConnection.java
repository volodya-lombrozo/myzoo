package com.github.volodya_lombrozo.simple_server.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class CuratorConnection {

    private final String host = "myzookeeper:2181";

    public CuratorFramework connection() {
        return CuratorFrameworkFactory.builder()
                .connectString(host)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .build();
    }

}
