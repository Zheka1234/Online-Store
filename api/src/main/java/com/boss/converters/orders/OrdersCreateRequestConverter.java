package com.boss.converters.orders;

import com.boss.controller.request.orders.OrdersCreatRequest;
import com.boss.domain.hibernate.HibernateOrder;
import com.boss.service.phone.PhoneServiceImpl;
import com.boss.service.point.PointServiceImpl;
import com.boss.service.user.UserImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;


@Component
@RequiredArgsConstructor
public class OrdersCreateRequestConverter extends OrdersBase<OrdersCreatRequest, HibernateOrder> {

    private final UserImpl userImpl;

    private final PhoneServiceImpl phoneService;

    private final PointServiceImpl pointService;


    @Override
    public HibernateOrder convert(OrdersCreatRequest source) {

        HibernateOrder hibernateOrder = new HibernateOrder();

        hibernateOrder.setIdUser(userImpl.findById(source.getIdUser()));
        hibernateOrder.setPhone(phoneService.findById(source.getIdPhone()));
        hibernateOrder.setPoint(pointService.findById(source.getIdPoint()));

        hibernateOrder.setCreationDate(new Timestamp(new Date().getTime()));


        return doConvert(hibernateOrder, source);
    }
}
