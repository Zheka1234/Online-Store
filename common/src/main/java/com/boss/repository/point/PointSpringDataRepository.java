package com.boss.repository.point;

import com.boss.domain.hibernate.HibernatePoint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PointSpringDataRepository extends JpaRepository<HibernatePoint, Long> {

    Page<HibernatePoint> findAll(Pageable pageable);


}
