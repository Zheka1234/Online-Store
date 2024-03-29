package com.boss.converters.user;

import com.boss.controller.request.user.UserCreateRequest;
import com.boss.domain.Credentials;
import com.boss.domain.hibernate.HibernateUser;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class UserCreateRequestConverter extends UserConverter<UserCreateRequest, HibernateUser> {

    private final PasswordEncoder passwordEncoder;
    @Override
    public HibernateUser convert(UserCreateRequest request) {

        HibernateUser hibernateUser = new HibernateUser();

        hibernateUser.setCreationDate(new Timestamp(new Date().getTime()));

//        Credentials credentials = new Credentials(
//                request.getLoginUser(),
//                passwordEncoder.encode(request.getPasswordUsers()));



        String simplePassword = RandomStringUtils.randomAlphabetic(10);
        System.out.println(simplePassword);

        Credentials credentials = new Credentials(
                RandomStringUtils.randomAlphabetic(10),
                passwordEncoder.encode(simplePassword)
        );

        hibernateUser.setCredentials(credentials);

        return doConvert(hibernateUser, request);
    }
}