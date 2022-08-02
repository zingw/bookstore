package com.example.book.store.service;

import com.example.book.store.dto.mail.DataMailDTO;
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
public class MailServiceImpl implements  MailService{
    @Autowired
    JavaMailSender mailSender;
    @Autowired
    SpringTemplateEngine templateEngine;

    @Override
    public void sendHtmlMail(DataMailDTO dataMail, String templateName) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try{
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "utf-8");
            Context context = new Context();
            context.setVariables(dataMail.getProps());
            String mailContent = templateEngine.process(templateName, context);
            message.setTo(dataMail.getTo());
            message.setSubject(dataMail.getSubject());
            message.setText(mailContent);

            mailSender.send(mimeMessage);
        }catch (MailException | MessagingException e){
            System.out.println("SEND MAIL FAILED");
        }
    }
}