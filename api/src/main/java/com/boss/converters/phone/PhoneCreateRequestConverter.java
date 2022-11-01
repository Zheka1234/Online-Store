package com.boss.converters.phone;

import com.boss.controller.request.phone.PhoneCreatRequest;
import com.boss.domain.hibernate.HibernatePhone;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

@Component
public class PhoneCreateRequestConverter extends PhoneBase<PhoneCreatRequest, HibernatePhone> {


    @Override
    public HibernatePhone convert(PhoneCreatRequest request) {
        HibernatePhone hibernatePhone = new HibernatePhone();
        hibernatePhone.setInStock(true);
        hibernatePhone.setCreationDate(new Timestamp(new Date().getTime()));
        hibernatePhone.setModificationDate(new Timestamp(new Date().getTime()));
        return doConvert(hibernatePhone,request);
    }
}
