package com.boss.converters.roles;

import com.boss.controller.request.roles.RoleChangeRequest;
import com.boss.controller.request.roles.RoleCreateRequest;
import com.boss.domain.SystemRoles;
import com.boss.domain.hibernate.HibernateRole;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;



@Component
public class RoleCreateRequestConverter implements Converter<RoleCreateRequest, HibernateRole> {

    @Override
    public HibernateRole convert(RoleCreateRequest request) {

        HibernateRole role = new HibernateRole();

        role.setRoleName(SystemRoles.valueOf(request.getRoleName()));
        role.setCreationDate(new Timestamp(new Date().getTime()));
        role.setModificationDate(new Timestamp(new Date().getTime()));


        return role;
    }
}