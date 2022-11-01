package com.boss.converters.suppliers;

import com.boss.controller.request.suppliers.SuppliersChangeRequest;
import com.boss.domain.hibernate.HibernateSuppliers;
import com.boss.repository.suppliers.SuppliersSpringDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
@RequiredArgsConstructor
public class SuppliersEditConverter extends SuppliersBase<SuppliersChangeRequest, HibernateSuppliers> {

    private final SuppliersSpringDataRepository repository;


    @Override
    public HibernateSuppliers convert(SuppliersChangeRequest source) {

        HibernateSuppliers suppliers =
                repository.findById(source.getId()).orElseThrow(EntityNotFoundException::new);

        return doConvert(suppliers,source);
    }
}
