package com.boss.reposotory.jdbctemplate.phone;


import com.boss.domain.Phone;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Primary
public class PhoneRepository implements com.boss.reposotory.phone.PhoneRepository {
    @Override
    public Phone findById(Long id) {
        return null;
    }

    @Override
    public Optional<Phone> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Phone> findAll() {
        return null;
    }

    @Override
    public List<Phone> findAll(int limit, int offset) {
        return null;
    }

    @Override
    public Phone create(Phone object) {
        return null;
    }

    @Override
    public Phone update(Phone object) {
        return null;
    }

    @Override
    public Long delete(Long id) {
        return null;
    }

    @Override
    public Map<String, Object> getMapOfPhone() {
        return null;
    }
}
