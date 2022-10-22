package com.boss.converters.roles;

import com.boss.controller.request.roles.RoleChangeRequest;
import com.boss.domain.SystemRoles;
import com.boss.domain.hibernate.HibernateRole;
import com.boss.repository.roles.RoleSpringDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class RoleEditRequestConverter implements Converter<RoleChangeRequest, HibernateRole> {
    private final RoleSpringDataRepository roleRepository;


    @Override
    public HibernateRole convert(RoleChangeRequest request) {

        HibernateRole role =
                roleRepository.findById(request.getId()).orElseThrow(EntityNotFoundException::new);

        role.setRoleName(SystemRoles.valueOf(request.getRoleName()));
        role.setModificationDate(new Timestamp(new Date().getTime()));

        return role;
    }
}