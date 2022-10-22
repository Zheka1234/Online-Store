package com.boss.converters.phone;

import com.boss.controller.request.phone.PhoneCreatRequest;
import com.boss.domain.hibernate.HibernatePhone;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

@Component
public class PhoneCreateRequestConverter implements Converter<PhoneCreatRequest, HibernatePhone> {


    @Override
    public HibernatePhone convert(PhoneCreatRequest request) {
        HibernatePhone hibernatePhone = new HibernatePhone();
        hibernatePhone.setBrand(request.getBrand());
        hibernatePhone.setModel(request.getModel());
        hibernatePhone.setColor(request.getColor());
        hibernatePhone.setDescription(request.getDescription());
        hibernatePhone.setPrice(request.getPrice());
        hibernatePhone.setInStock(true);
        hibernatePhone.setCreationDate(new Timestamp(new Date().getTime()));
        hibernatePhone.setModificationDate(new Timestamp(new Date().getTime()));
        return hibernatePhone;
    }
}
