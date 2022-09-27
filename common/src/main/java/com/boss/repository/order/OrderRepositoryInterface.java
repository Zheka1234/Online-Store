package com.boss.repository.order;

import com.boss.domain.Order;
import com.boss.repository.CRUDRepository;

import java.util.Map;

public interface OrderRepositoryInterface extends CRUDRepository <Long, Order> {

    Map<String, Object> getMapOfOrder();
}
