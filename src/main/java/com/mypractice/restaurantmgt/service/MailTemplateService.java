package com.mypractice.restaurantmgt.service;

import com.mypractice.restaurantmgt.entity.MailTemplateEntity;

import java.util.List;

public interface MailTemplateService {
     List<MailTemplateEntity> getAllTemplates();

     MailTemplateEntity getMailTemplateById(Integer id);
     MailTemplateEntity getMailTemplateByName(String id);
}
