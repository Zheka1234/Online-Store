package com.boss.service.suppliers;

import com.boss.domain.hibernate.HibernateSuppliers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SuppliersService {

    HibernateSuppliers create(HibernateSuppliers hibernateSuppliers);

    HibernateSuppliers delete(Long Id);

    HibernateSuppliers update(HibernateSuppliers hibernateSuppliers);

    Page<HibernateSuppliers> findAll(Pageable pageable);

    List<HibernateSuppliers> findAll();

    HibernateSuppliers findById(Long id);

}