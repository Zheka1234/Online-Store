package com.boss.repository.roles;

import com.boss.domain.hibernate.HibernateRole;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;


@Repository
public interface RoleSpringDataRepository extends  JpaRepository<HibernateRole, Integer> {

    List<HibernateRole> findHibernateRoleByIdRole(Long userId);

    @Query(value = "select * from phoneshop.roles " +
            "inner join phoneshop.l_role_users " +
            "on phoneshop.roles.id_role = phoneshop.l_role_users.id_role " +
            "where phoneshop.l_role_users.id_user = :userId"
            , nativeQuery = true)
    List<HibernateRole> findRolesByUserid(Long userId);

    @Cacheable("roles")
    @Query(value = "select r from HibernateRole r")
    List<HibernateRole> findAllCustom();


}
