package com.boss.converters;

import com.boss.controller.request.user.UserCreateRequest;
import com.boss.domain.Credentials;
import com.boss.domain.hibernate.HibernateUser;
import org.springframework.core.convert.converter.Converter;

import java.sql.Timestamp;
import java.util.Date;

public abstract class EntityConverter<S, T> implements Converter<S, T> {
    protected HibernateUser doConvert(HibernateUser user, UserCreateRequest request) {

        user.setNameUsers(request.getNameUsers());
        user.setSurnameUsers(request.getSurnameUsers());
        user.setBuys(request.getBuys());
        user.setIsDeleted(false);


        Credentials credentials = new Credentials();
        credentials.setLogin(request.getLoginUser());
        credentials.setPassword(request.getPasswordUsers());

        user.setCredentials(credentials);

        user.setModificationDate(new Timestamp(new Date().getTime()));



        return user;
    }
}