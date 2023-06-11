package com.mypractice.restaurantmgt.service;

import com.mypractice.restaurantmgt.dto.MailDTO;
import jakarta.mail.MessagingException;

public interface MailSenderService {
     void sendEmail(MailDTO mailDTO, String mailTemplateName) throws MessagingException;

}
