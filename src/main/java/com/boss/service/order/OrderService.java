package com.boss.service.order;

import com.boss.domain.Order;


import java.util.List;
import java.util.Map;

public interface OrderService {

    List<Order> findAll();

    Map<String, Object> getMapOfOrder();

    Order create(Order object);
}
