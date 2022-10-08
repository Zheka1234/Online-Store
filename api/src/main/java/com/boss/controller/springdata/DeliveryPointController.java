package com.boss.controller.springdata;


import com.boss.controller.request.phone.PhoneCreatRequest;
import com.boss.controller.request.point.PointCreatRequest;
import com.boss.domain.hibernate.HibernatePoint;
import com.boss.repository.point.PointSpringDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/data/point")
public class DeliveryPointController {

    private final PointSpringDataRepository pointSpringDataRepository;

    @GetMapping
    public ResponseEntity<Object> findAllPoint(){
        return new ResponseEntity<>(Collections.singletonMap("result", pointSpringDataRepository.findAll()), HttpStatus.OK);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Object> createPoint(@RequestBody PointCreatRequest creatRequest){
        HibernatePoint point = new HibernatePoint();

        point.setAddressPoint(creatRequest.getAddress());
        point.setAddressPoint(creatRequest.getHours());
        point.setCreationDate(new Timestamp(new Date().getTime()));
        point.setModificationDate(new Timestamp(new Date().getTime()));

        HibernatePoint createdPhone = pointSpringDataRepository.save(point);

        Map<String, Object> model = new HashMap<>();
        model.put("point", createdPhone);

        return new ResponseEntity<>(model,HttpStatus.CREATED);
    }


}