package com.boss.service.phone;

import com.boss.repository.Phone.PhoneSpringDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PhoneServisImpl implements PhoneServis {

    private final PhoneSpringDataRepository phoneSpringDataRepository;

    @Transactional
    @Override
    public Long delete(Long id) {
         phoneSpringDataRepository.deleteById(id);
         return id;
    }
}
