package com.boss.converters.point;

import com.boss.controller.request.point.PointChangeRequest;
import com.boss.domain.hibernate.HibernatePoint;
import com.boss.repository.point.PointSpringDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;


@Component
@RequiredArgsConstructor
public class PointEditConverter extends PointBase<PointChangeRequest, HibernatePoint> {

    private final PointSpringDataRepository pointSpringDataRepository;


    @Override
    public HibernatePoint convert(PointChangeRequest source) {

        HibernatePoint hibernatePoint =
                pointSpringDataRepository.findById(source.getId()).orElseThrow(EntityNotFoundException::new);

        return doCovert(hibernatePoint, source);
    }
}
