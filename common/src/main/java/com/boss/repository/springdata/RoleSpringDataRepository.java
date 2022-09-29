package com.boss.repository.springdata;

import com.boss.domain.hibernate.HibernateRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface RoleSpringDataRepository extends CrudRepository<HibernateRole, Integer> ,
        JpaRepository<HibernateRole, Integer>,
        PagingAndSortingRepository<HibernateRole, Integer> {

    List<HibernateRole> findHibernateRoleByIdRole(Long userId);
}
