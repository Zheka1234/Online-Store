package com.boss.service.user;

import com.boss.repository.user.UserSpringDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
