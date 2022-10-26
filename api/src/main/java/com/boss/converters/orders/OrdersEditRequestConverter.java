package com.boss.converters.orders;

import com.boss.controller.request.orders.OrdersChangeRequest;
import com.boss.domain.hibernate.HibernateOrder;
import com.boss.repository.order.OrderSpringDataRepository;
import com.boss.service.phone.PhoneServiceImpl;
import com.boss.service.point.PointServiceImpl;
import com.boss.service.user.UserImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
@RequiredArgsConstructor
public class OrdersEditRequestConverter extends OrdersBase<OrdersChangeRequest, HibernateOrder> {

    private final OrderSpringDataRepository orderSpringDataRepository;

    private final UserImpl userImpl;

    private final PhoneServiceImpl phoneService;

    private final PointServiceImpl pointService;


    @Override
    public HibernateOrder convert(OrdersChangeRequest source) {

        HibernateOrder hibernateOrder =
                orderSpringDataRepository.findById(source.getId()).orElseThrow(EntityNotFoundException::new);

        hibernateOrder.setIdUser(userImpl.findById(source.getIdUser()));
        hibernateOrder.setPhone(phoneService.findById(source.getIdPhone()));
        hibernateOrder.setPoint(pointService.findById(source.getIdPoint()));
        return doConvert(hibernateOrder, source);
    }
}
