package com.mypractice.restaurantmgt.service.impl;


import com.mypractice.restaurantmgt.dto.MailDTO;
import com.mypractice.restaurantmgt.entity.MailTemplateEntity;
import com.mypractice.restaurantmgt.service.MailSenderService;
import com.mypractice.restaurantmgt.service.MailTemplateService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.nio.charset.StandardCharsets;

@Service
public class MailSenderServiceImpl implements MailSenderService {

    private final String mailFrom;



    private final JavaMailSender emailSender;


    private final SpringTemplateEngine templateEngine;

    private final MailTemplateService mailTemplateService;


    public MailSenderServiceImpl(@Value("${spring.mail.username}") String mailFrom, JavaMailSender emailSender, SpringTemplateEngine templateEngine,MailTemplateService mailTemplateService) {
        this.mailFrom = mailFrom;
        this.emailSender = emailSender;
        this.templateEngine = templateEngine;
        this.mailTemplateService = mailTemplateService;
    }

    @Override
    public void sendEmail(MailDTO mailDTO,  String mailTemplateName) throws MessagingException {
        System.out.println("MailSenderServiceImpl.sendEmail "+mailTemplateName);
        MailTemplateEntity mailTemplate = mailTemplateService.getMailTemplateByName(mailTemplateName);
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());
        Context context = new Context();
        context.setVariables(mailDTO.getRequiredData());
        String html = templateEngine.process(mailTemplate.getFileName(), context);
        helper.setTo(mailDTO.getMailTo());
        helper.setText(html, true);
        helper.setSubject(mailTemplate.getSubject());
        helper.setFrom(mailFrom);
        emailSender.send(message);
    }
}
