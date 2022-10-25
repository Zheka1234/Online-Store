package com.boss.service.ordes;

import com.boss.domain.hibernate.HibernateOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrdersService {

    HibernateOrder create(HibernateOrder  hibernateOrder);

    HibernateOrder  update(HibernateOrder  hibernateOrder);

    Page<HibernateOrder > findAll(Pageable pageable);

    List<HibernateOrder > findAll();

    HibernateOrder  findById(Long id);

    HibernateOrder findByUserId(Long userId);
}
