package com.boss.repository.order;

import com.boss.domain.hibernate.HibernateOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OrderSpringDataRepository extends CrudRepository<HibernateOrder, Long>,
        JpaRepository<HibernateOrder, Long>,
        PagingAndSortingRepository<HibernateOrder, Long> {
}
