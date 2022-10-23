package com.boss.repository.suppliers;

import com.boss.domain.hibernate.HibernateSuppliers;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SuppliersSpringDataRepository extends JpaRepository<HibernateSuppliers, Long> {
    @Cacheable("suppliers")
    @Query(value = "select r from HibernateSuppliers r")
    List<HibernateSuppliers> findAllCustom();
}
