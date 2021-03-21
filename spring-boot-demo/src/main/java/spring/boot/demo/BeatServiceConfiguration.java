/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package spring.boot.demo;

import erik.study.net.netty.heartbeat.server.BeatServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yueyi
 * @version : BeatServiceConfiguration.java, v 0.1 2021年03月21日 7:27 下午 yueyi Exp $
 */
@Configuration
public class BeatServiceConfiguration {

    @Bean
    public BeatServer getBeatServer() {
        return new BeatServer();
    }
}