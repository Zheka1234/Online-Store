package com.boss.service.user;

import com.boss.domain.hibernate.HibernateRole;
import com.boss.domain.hibernate.HibernateUser;
import com.boss.repository.user.UserSpringDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserImpl implements UserService {

    private final UserSpringDataRepository userSpringDataRepository;

    @Transactional
    @Override
    public Long delete(Long id) {
        userSpringDataRepository.deleteById(id);
        return id;
    }
}