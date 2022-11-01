package com.boss.service.phone;

import com.boss.domain.hibernate.HibernatePhone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PhoneService {

    HibernatePhone create(HibernatePhone hibernatePhone);

    Long delete(Long phoneId);

    HibernatePhone update(HibernatePhone hibernatePhone);

    Page<HibernatePhone> findAll(Pageable pageable);

    List<HibernatePhone> findAll();

    HibernatePhone findById(Long phoneId);
}
