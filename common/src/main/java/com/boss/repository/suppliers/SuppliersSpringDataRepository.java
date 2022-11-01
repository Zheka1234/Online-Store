package com.boss.repository.suppliers;

import com.boss.domain.hibernate.HibernateSuppliers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuppliersSpringDataRepository extends JpaRepository<HibernateSuppliers, Long> {

}
