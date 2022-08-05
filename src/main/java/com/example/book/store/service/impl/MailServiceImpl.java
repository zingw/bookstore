package com.example.book.store.service.impl;

import com.example.book.store.dto.mail.DataMailDTO;
import com.example.book.store.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Service
public class MailServiceImpl implements MailService {
    @Autowired
    JavaMailSender mailSender;
    @Autowired
    SpringTemplateEngine templateEngine;

    @Override
    public void sendHtmlMail(DataMailDTO dataMail, String templateName) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try{
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "utf-8");
            Context context = new Context();
            context.setVariables(dataMail.getProps());
            String html = templateEngine.process(templateName, context);
            message.setTo(dataMail.getTo());
            message.setSubject(dataMail.getSubject());
            message.setText(html,true);

            mailSender.send(mimeMessage);
        }catch (MailException | MessagingException e){
            System.out.println("SEND MAIL FAILED");
        }
    }
}
