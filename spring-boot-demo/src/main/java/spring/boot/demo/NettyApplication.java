/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package spring.boot.demo;

import erik.study.net.netty.heartbeat.server.BeatServer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * @author yueyi
 * @version : NettyApplication.java, v 0.1 2021年03月21日 7:11 下午 yueyi Exp $
 */

public class NettyApplication {

    @Resource
    private BeatServer beatServer;

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                beatServer.start();
                Runtime.getRuntime().addShutdownHook(
                        new Thread() {
                            @Override
                            public void run() {
                                beatServer.close();
                            }
                        }
                );
            }
        };
    }

}