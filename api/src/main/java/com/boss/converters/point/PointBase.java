package com.boss.converters.point;

import com.boss.controller.request.point.PointCreatRequest;
import com.boss.domain.hibernate.HibernatePoint;
import org.springframework.core.convert.converter.Converter;

import java.util.Date;
import java.sql.Timestamp;

public abstract class PointBase <S, T> implements Converter<S, T> {

    protected HibernatePoint doCovert(HibernatePoint point, PointCreatRequest request){

        point.setAddressPoint(request.getAddress());
        point.setHours(request.getHours());
        point.setModificationDate(new Timestamp(new Date().getTime()));

        return point;
    }

}
