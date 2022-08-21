package com.boss.reposotory.user;

import com.boss.domain.User;
import com.boss.reposotory.CRUDRepository;

import java.util.Map;

public interface UserRepository extends CRUDRepository <Long, User> {

    Map<String, Object> getUserStats();
}
