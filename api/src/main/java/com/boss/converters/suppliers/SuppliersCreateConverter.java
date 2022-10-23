package com.boss.converters.suppliers;

import com.boss.controller.request.suppliers.SuppliersCreat;
import com.boss.domain.hibernate.HibernateSuppliers;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

@Component
public class SuppliersCreateConverter implements Converter<SuppliersCreat, HibernateSuppliers> {

    @Override
    public HibernateSuppliers convert(SuppliersCreat source) {

        HibernateSuppliers suppliers = new HibernateSuppliers();

        suppliers.setNameSuppliers(source.getNameSuppliers());
        suppliers.setAddressSuppliers(source.getAddressSuppliers());
        suppliers.setPhoneSuppliers(source.getPhoneSuppliers());
        suppliers.setCreationDate(new Timestamp(new Date().getTime()));
        suppliers.setModificationDate(new Timestamp(new Date().getTime()));

        return suppliers;
    }
}
