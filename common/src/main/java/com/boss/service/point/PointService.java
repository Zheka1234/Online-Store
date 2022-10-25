package com.boss.service.point;

import com.boss.domain.hibernate.HibernatePoint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PointService {

    HibernatePoint create(HibernatePoint hibernatePoint);

    HibernatePoint delete(Long Id);

    HibernatePoint update(HibernatePoint hibernatePoint);

    Page<HibernatePoint> findAll(Pageable pageable);

    List<HibernatePoint> findAll();

    HibernatePoint findById(Long id);
}
