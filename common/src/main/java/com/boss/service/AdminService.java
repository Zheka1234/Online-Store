package com.boss.service;

import com.boss.domain.hibernate.HibernateRole;
import com.boss.domain.hibernate.HibernateUser;
import com.boss.repository.roles.RoleSpringDataRepository;
import com.boss.repository.user.UserSpringDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AdminService {

    public final UserSpringDataRepository userSpringDataRepository;

    public final RoleSpringDataRepository roleSpringDataRepository;

    public HibernateUser setRoles(HibernateUser user, Integer roleId){

        Set<HibernateRole> roles = user.getRoles();

        Set<HibernateRole> updatedRoles = new HashSet<>();

        if (!CollectionUtils.isEmpty(roles)) {
            updatedRoles.addAll(roles);
        }
        updatedRoles.add(roleSpringDataRepository.findHibernateRoleByIdRole(1L).get(0));
        updatedRoles.add(roleSpringDataRepository.findHibernateRoleByIdRole(2L).get(0));

        user.setRoles(updatedRoles);

        return user;

    }
}