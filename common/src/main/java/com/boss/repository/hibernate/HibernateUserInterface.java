package com.boss.repository.hibernate;

import com.boss.domain.User;
import com.boss.domain.hibernate.HibernateUser;
import com.boss.repository.CRUDRepository;

import java.util.Map;
import java.util.Optional;

public interface HibernateUserInterface extends CRUDRepository<Long, HibernateUser> {

    Map<String, Object> getUserStats();

    Optional<User> findByLogin(String login);
}