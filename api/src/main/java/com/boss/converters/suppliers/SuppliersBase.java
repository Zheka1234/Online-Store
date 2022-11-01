package com.boss.converters.suppliers;

import com.boss.controller.request.suppliers.SuppliersCreat;
import com.boss.controller.request.user.UserCreateRequest;
import com.boss.domain.hibernate.HibernateSuppliers;
import com.boss.domain.hibernate.HibernateUser;
import org.springframework.core.convert.converter.Converter;

import java.sql.Timestamp;
import java.util.Date;

public abstract class SuppliersBase <S, T> implements Converter<S, T> {

    protected HibernateSuppliers doConvert(HibernateSuppliers suppliers, SuppliersCreat request) {

        suppliers.setNameSuppliers(request.getNameSuppliers());
        suppliers.setAddressSuppliers(request.getAddressSuppliers());
        suppliers.setPhoneSuppliers(request.getPhoneSuppliers());
        suppliers.setCreationDate(new Timestamp(new Date().getTime()));
        suppliers.setModificationDate(new Timestamp(new Date().getTime()));

        return suppliers;
    }
}
