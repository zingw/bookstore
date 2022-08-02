package com.example.book.store.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {

    @Value("${mail-config.host}")
    private String host;
    @Value("${mail-config.port}")
    private Integer port;
    @Value("${mail-config.username}")
    private String username;
    @Value("${mail-config.password}")
    private String password;
    @Value("${mail-config.default-encoding}")
    private String encoding;



    @Bean
    public JavaMailSender getJavamailSender(){
        JavaMailSenderImpl mailSender =  new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(username);
        mailSender.setPassword(password);
        mailSender.setDefaultEncoding(encoding);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }
}
