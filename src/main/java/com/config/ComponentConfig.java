package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class ComponentConfig {


    @Bean(name = "javaMailSender")
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = createMailSender();
        fillMailSenderProperty(mailSender);
        return mailSender;
    }

    private JavaMailSenderImpl createMailSender() {
        return new JavaMailSenderImpl();
    }

    private void fillMailSenderProperty(JavaMailSenderImpl sender) {
        String host = "smtp.163.com";
        String username = "w18800434802@163.com";
        String password = "VHXZHNESBVAKZCZG";
        String auth = "true";
        sender.setHost(host);
        sender.setDefaultEncoding("UTF-8");
        sender.setUsername(username);
        sender.setPassword(password);
        sender.setPort(465);
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.auth", auth);
        properties.setProperty("mail.smtp.ssl.enable", "true");
        sender.setJavaMailProperties(properties);
    }
}
