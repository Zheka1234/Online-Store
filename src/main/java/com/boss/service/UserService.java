package com.boss.service;

import com.boss.domain.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<User> findAll();

    Map<String, Object> getUserStats();

    User create(User object);
}

