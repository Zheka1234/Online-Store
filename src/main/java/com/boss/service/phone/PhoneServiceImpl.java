package com.boss.service.phone;

import com.boss.domain.Phone;
import com.boss.reposotory.jdbctemplate.phone.PhoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PhoneServiceImpl implements PhoneService{

    private final PhoneRepository phoneRepository;

    @Override
    public Phone update(Phone object) {
        return phoneRepository.update(object);
    }

    @Override
    public Phone findById(Long id) {
        return phoneRepository.findById(id);
    }

    @Override
    public List<Phone> findAll() {
        return phoneRepository.findAll();
    }

    @Override
    public Map<String, Object> getMapOfPhone() {
        return phoneRepository.getMapOfPhone();
    }

    @Override
    public Phone create(Phone object) {
        return phoneRepository.create(object);
    }

    @Override
    public Long delete(Long id) {
        return phoneRepository.delete(id);
    }
}
