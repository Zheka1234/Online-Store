package com.boss.converters.point;


import com.boss.controller.request.point.PointCreatRequest;
import com.boss.domain.hibernate.HibernatePoint;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

@Component
public class PointCreateConverter extends PointBase<PointCreatRequest, HibernatePoint> {

    @Override
    public HibernatePoint convert(PointCreatRequest source) {

        HibernatePoint hibernatePoint = new HibernatePoint();

        hibernatePoint.setCreationDate(new Timestamp(new Date().getTime()));
        hibernatePoint.setModificationDate(new Timestamp(new Date().getTime()));

        return doCovert(hibernatePoint,source);
    }
}
