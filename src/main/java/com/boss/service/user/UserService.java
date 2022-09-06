package com.boss.service.user;

import com.boss.domain.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<User> findAll();

    Map<String, Object> getUserStats();

    User create(User object);

    Long delete(Long id);

    List<User> search(int limit, int offset);

    User findById(Long userId);
}

