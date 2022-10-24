package com.boss.service.role;

import com.boss.domain.hibernate.HibernateRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RolesService {

    HibernateRole create(HibernateRole hibernateRole);

    HibernateRole delete(Integer userId);

    HibernateRole update(HibernateRole hibernateRole);

    Page<HibernateRole> findAll(Pageable pageable);

    List<HibernateRole> findAll();

    HibernateRole findById(Integer id);

    List<Object[]> findOnlyRoles();
}
