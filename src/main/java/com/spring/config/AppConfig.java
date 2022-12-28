package com.spring.config;

import com.spring.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * description
 *
 * @version 1.0
 * @Auto: yulong_wang
 * @since 2022-09-10
 **/
@Configuration
public class AppConfig {

    @Bean
    public UserService userService() {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程启动");
            }
        });
        t1.start();
        return new UserService();
    }
}
