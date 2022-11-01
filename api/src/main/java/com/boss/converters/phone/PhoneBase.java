package com.boss.converters.phone;

import com.boss.controller.request.phone.PhoneCreatRequest;
import com.boss.domain.hibernate.HibernatePhone;
import org.springframework.core.convert.converter.Converter;

import java.sql.Timestamp;
import java.util.Date;

public abstract class PhoneBase<S,T> implements Converter<S,T> {


   protected HibernatePhone doConvert(HibernatePhone hibernatePhone, PhoneCreatRequest request){

        hibernatePhone.setBrand(request.getBrand());
        hibernatePhone.setModel(request.getModel());
        hibernatePhone.setColor(request.getColor());
        hibernatePhone.setDescription(request.getDescription());
        hibernatePhone.setPrice(request.getPrice());
        hibernatePhone.setCreationDate(new Timestamp(new Date().getTime()));
        hibernatePhone.setModificationDate(new Timestamp(new Date().getTime()));
        return hibernatePhone;
    }
}
