package com.boss.repository.order;

import com.boss.domain.hibernate.HibernateOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderSpringDataRepository extends JpaRepository<HibernateOrder, Long>{

}
