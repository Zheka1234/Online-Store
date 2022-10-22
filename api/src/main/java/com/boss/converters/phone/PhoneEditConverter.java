package com.boss.converters.phone;

import com.boss.controller.request.phone.PhoneChangeRequest;
import com.boss.domain.hibernate.HibernatePhone;
import com.boss.repository.Phone.PhoneSpringDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
@RequiredArgsConstructor
public class PhoneEditConverter implements Converter<PhoneChangeRequest, HibernatePhone> {

    private PhoneSpringDataRepository phoneSpringDataRepository;

    @Override
    public HibernatePhone convert(PhoneChangeRequest request) {
        HibernatePhone phone =
                phoneSpringDataRepository.findById(request.getId()).orElseThrow(EntityNotFoundException::new);
        return phone;
    }
}
