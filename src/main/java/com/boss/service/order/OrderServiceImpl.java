package com.boss.service.order;

import com.boss.domain.Order;
import com.boss.reposotory.order.OrderRepositoryInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepositoryInterface orderRepository;


    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Map<String, Object> getMapOfOrder() {
        return orderRepository.getMapOfOrder();
    }

    @Override
    public Order create(Order object) {
        return orderRepository.create(object);
    }

    @Override
    public Long delete(Long id) {
        return orderRepository.delete(id);
    }


}
