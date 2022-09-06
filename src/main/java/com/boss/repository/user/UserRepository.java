package com.boss.repository.user;

import com.boss.domain.User;
import com.boss.repository.CRUDRepository;

import java.util.Map;

public interface UserRepository extends CRUDRepository <Long, User> {

    Map<String, Object> getUserStats();
}
