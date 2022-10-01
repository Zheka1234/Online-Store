package com.boss.repository.suppliers;

import com.boss.domain.hibernate.HibernateSuppliers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface SuppliersSpringDataRepository extends CrudRepository<HibernateSuppliers, Long>,
        JpaRepository<HibernateSuppliers, Long>,
        PagingAndSortingRepository<HibernateSuppliers, Long> {

    List<HibernateSuppliers> findHibernateSuppliersByIdSuppliers(Long suppliersId);
}
