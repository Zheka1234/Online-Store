package com.boss.repository.user;

import com.boss.domain.User;
import com.boss.repository.CRUDRepository;

import java.util.Map;
import java.util.Optional;

public interface UserRepository extends CRUDRepository <Long, User> {

    Map<String, Object> getUserStats();

    Optional<User> findByLogin(String login);
}
