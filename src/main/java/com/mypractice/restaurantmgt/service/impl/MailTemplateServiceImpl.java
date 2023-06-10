package com.mypractice.restaurantmgt.service.impl;

import com.mypractice.restaurantmgt.entity.MailTemplateEntity;
import com.mypractice.restaurantmgt.repository.MailTemplateRepo;
import com.mypractice.restaurantmgt.service.MailTemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MailTemplateServiceImpl implements MailTemplateService {



    private final MailTemplateRepo mailTemplateRepo;

    @Override
    public List<MailTemplateEntity> getAllTemplates() {
        return mailTemplateRepo.findAllByIsActiveIsTrue();
    }

    @Override
    public MailTemplateEntity getMailTemplateById(Integer id) {
        return mailTemplateRepo.findByIdAndIsActiveIsTrue(id);
    }

}