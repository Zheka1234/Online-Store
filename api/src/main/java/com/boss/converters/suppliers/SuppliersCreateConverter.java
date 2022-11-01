package com.boss.converters.suppliers;

import com.boss.controller.request.suppliers.SuppliersCreat;
import com.boss.domain.hibernate.HibernateSuppliers;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

@Component
public class SuppliersCreateConverter extends SuppliersBase<SuppliersCreat, HibernateSuppliers> {

    @Override
    public HibernateSuppliers convert(SuppliersCreat source) {

        HibernateSuppliers suppliers = new HibernateSuppliers();

        suppliers.setModificationDate(new Timestamp(new Date().getTime()));

        return doConvert(suppliers, source);
    }
}
