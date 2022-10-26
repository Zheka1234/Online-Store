package com.boss.converters.orders;

import com.boss.controller.request.orders.OrdersCreatRequest;
import com.boss.domain.hibernate.HibernateOrder;
import org.springframework.core.convert.converter.Converter;

import java.sql.Timestamp;
import java.util.Date;

public abstract class OrdersBase <S, T> implements Converter<S, T> {

    protected HibernateOrder doConvert(HibernateOrder hibernateOrder, OrdersCreatRequest request){


        hibernateOrder.setPaymentType(request.getPaymentType());
        hibernateOrder.setTotalSum(request.getTotalSum());
        hibernateOrder.setModificationDate(new Timestamp(new Date().getTime()));

        return hibernateOrder;
    }
}
