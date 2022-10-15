package com.boss.converters;

import com.boss.controller.request.user.UserChangeRequest;
import com.boss.domain.hibernate.HibernateUser;
import com.boss.repository.user.UserSpringDataRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
public class UserEditRequestConverter extends EntityConverter<UserChangeRequest, HibernateUser> {

    private UserSpringDataRepository userRepository;

    public UserEditRequestConverter(UserSpringDataRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public HibernateUser convert(UserChangeRequest request) {

        HibernateUser hibernateUser = userRepository.findById(request.getId()).orElseThrow(EntityNotFoundException::new);
        return doConvert(hibernateUser, request);
    }
}