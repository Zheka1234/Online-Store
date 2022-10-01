package com.boss.controller.springdata;


import com.boss.controller.request.phone.PhoneCreatRequest;
import com.boss.domain.hibernate.HibernatePhone;
import com.boss.repository.Phone.PhoneSpringDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/data/phone")
public class PhoneController {

    private final PhoneSpringDataRepository phoneSpringDataRepository;


    @GetMapping
    public ResponseEntity<Object> findAllPhone(){

        return new ResponseEntity<>(Collections.singletonMap("result", phoneSpringDataRepository.findAll()), HttpStatus.OK);

    }

    @PostMapping
    @Transactional
    public ResponseEntity<Object> createPhone(@RequestBody PhoneCreatRequest creatRequest){
        HibernatePhone phone = new HibernatePhone();

        phone.setBrand(creatRequest.getBrand());
        phone.setModel(creatRequest.getModel());
        phone.setColor(creatRequest.getColor());
        phone.setDescription(creatRequest.getDescription());
        phone.setPrice(creatRequest.getPrice());
        phone.setCreationDate(new Timestamp(new Date().getTime()));
        phone.setModificationDate(new Timestamp(new Date().getTime()));
        phone.setInStock(true);

        HibernatePhone createdPhone = phoneSpringDataRepository.save(phone);

        Map<String, Object> model = new HashMap<>();
        model.put("phone", createdPhone);

        return new ResponseEntity<>(model,HttpStatus.CREATED);
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Object> deletePhoneById(@PathVariable Long id){
//        return new ResponseEntity<>();
//    }
}
