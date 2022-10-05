package com.boss.repository.roles;

import com.boss.domain.hibernate.HibernateRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;


@Repository
public interface RoleSpringDataRepository extends  JpaRepository<HibernateRole, Integer> {

    List<HibernateRole> findHibernateRoleByIdRole(Long userId);




}
