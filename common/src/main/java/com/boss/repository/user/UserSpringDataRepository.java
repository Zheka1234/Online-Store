package com.boss.repository.user;

import com.boss.domain.hibernate.HibernateUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserSpringDataRepository extends JpaRepository<HibernateUser, Long>
{
    @Modifying
    @Query(value = "insert into phoneshop.l_role_users(id_role, id_user) values (:id_role, :id_user)", nativeQuery = true)
    int createRoleRow(@Param("id_user") Long isUser, @Param("id_role") Long idRole);

    Optional<HibernateUser> findByCredentialsLogin(String login);
}
