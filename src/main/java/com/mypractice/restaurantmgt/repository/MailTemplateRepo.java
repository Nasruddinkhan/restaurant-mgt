package com.mypractice.restaurantmgt.repository;

import com.mypractice.restaurantmgt.entity.MailTemplateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MailTemplateRepo extends JpaRepository<MailTemplateEntity, Integer> {
    List<MailTemplateEntity> findAllByIsActiveIsTrue();

    MailTemplateEntity findByIdAndIsActiveIsTrue(Integer id);
}
