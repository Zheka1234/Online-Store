package com.boss.repository.role;

import com.boss.domain.Role;
import com.boss.repository.CRUDRepository;

import java.util.List;

public interface RoleRepositoryInterface extends CRUDRepository<Long, Role> {

    List<Role> findRoleByUserId(Long userId);
}
