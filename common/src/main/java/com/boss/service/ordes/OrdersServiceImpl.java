package com.boss.service.ordes;

import com.boss.domain.hibernate.HibernateOrder;
import com.boss.domain.hibernate.HibernateUser;
import com.boss.repository.order.OrderSpringDataRepository;
import com.boss.repository.user.UserSpringDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService {

    private final OrderSpringDataRepository repository;


    private final HibernateOrder savaOrders(HibernateOrder hibernateOrder){
        return repository.save(hibernateOrder);
    }
    @Override
    public HibernateOrder create(HibernateOrder hibernateOrder) {
        return savaOrders(hibernateOrder);
    }

    @Override
    public HibernateOrder update(HibernateOrder hibernateOrder) {
        return savaOrders(hibernateOrder);
    }

    @Override
    public Page<HibernateOrder> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public List<HibernateOrder> findAll() {
        return repository.findAll();
    }

    @Override
    public HibernateOrder findById(Long ordersId) {
        Optional<HibernateOrder> result = repository.findById(ordersId);

        if (result.isPresent()) {
            return result.get();
        } else {
            throw new EntityNotFoundException(
                    String.format("Orders agreement with this id \"%s\" is not found", ordersId));
        }
    }

}
