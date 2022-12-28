package com;

import com.spring.config.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * description
 *
 * @version 1.0
 * @Auto: 启动类
 * @since 2022-08-07
 **/

@SpringBootApplication
public class CodingApplication {
    
    private static final AppConfig appConfig = null;

    public static void main(String[] args) {
        // 调用SpringApplication的静态方法run()进行启动
        SpringApplication.run(CodingApplication.class);
    }
}
