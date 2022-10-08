package com.boss.repository.point;

import com.boss.domain.hibernate.HibernatePoint;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PointSpringDataRepository extends JpaRepository<HibernatePoint, Long> {


}
