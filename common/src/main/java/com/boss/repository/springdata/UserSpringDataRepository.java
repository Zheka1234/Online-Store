package com.boss.repository.springdata;

import com.boss.domain.Gender;
import com.boss.domain.hibernate.HibernateUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserSpringDataRepository extends CrudRepository<HibernateUser, Long>, JpaRepository<HibernateUser, Long>,
        PagingAndSortingRepository<HibernateUser, Long> {




    @Modifying
    @Query(value = "insert into phoneshop.l_role_users(id_role, id_user) values (:id_role, :id_user)", nativeQuery = true)
    int createRoleRow(@Param("id_user") Long isUser, @Param("id_role") Long idRole);

    Optional<HibernateUser> findHibernateUserByLoginUser(String login);
}
