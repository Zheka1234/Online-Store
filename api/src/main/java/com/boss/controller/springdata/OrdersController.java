package com.boss.controller.springdata;


import com.boss.repository.order.OrderSpringDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/data/orders")
public class OrdersController {

    private final OrderSpringDataRepository orderSpringDataRepository;

    @GetMapping
    public ResponseEntity<Object> findAllOrders(){
        return new ResponseEntity<>(Collections.singletonMap("result", orderSpringDataRepository.findAll()), HttpStatus.OK);
    }
}
