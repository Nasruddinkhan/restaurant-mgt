package com.mypractice.restaurantmgt.service;

import com.mypractice.restaurantmgt.dto.MailDTO;
import com.mypractice.restaurantmgt.entity.MailTemplateEntity;
import jakarta.mail.MessagingException;

public interface MailSenderService {
     void sendEmail(MailDTO mailDTO, MailTemplateEntity mailTemplate) throws MessagingException;

}
