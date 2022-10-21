package com.boss.converters;

import com.boss.controller.request.user.UserCreateRequest;
import com.boss.domain.Credentials;
import com.boss.domain.hibernate.HibernateUser;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.core.convert.converter.Converter;

import java.sql.Timestamp;
import java.util.Date;

public abstract class EntityConverter<S, T> implements Converter<S, T> {
    protected HibernateUser doConvert(HibernateUser user, UserCreateRequest request) {

        user.setNameUsers(request.getNameUsers());
        user.setSurnameUsers(request.getSurnameUsers());
        user.setBuys(request.getBuys());
        user.setIsDeleted(false);

        user.setModificationDate(new Timestamp(new Date().getTime()));



        return user;
    }
}