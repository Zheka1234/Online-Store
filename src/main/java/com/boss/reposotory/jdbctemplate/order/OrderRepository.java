package com.boss.reposotory.jdbctemplate.order;

import com.boss.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Primary
public class OrderRepository implements com.boss.reposotory.order.OrderRepository {
    @Override
    public Order findById(Long id) {
        return null;
    }

    @Override
    public Optional<Order> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Order> findAll() {
        return null;
    }

    @Override
    public List<Order> findAll(int limit, int offset) {
        return null;
    }

    @Override
    public Order create(Order object) {
        return null;
    }

    @Override
    public Order update(Order object) {
        return null;
    }

    @Override
    public Long delete(Long id) {
        return null;
    }

    @Override
    public Map<String, Object> getMapOfOrder() {
        return null;
    }
}
