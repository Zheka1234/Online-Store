package com.boss.controller.springdata;


import com.boss.repository.suppliers.SuppliersSpringDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/data/suppliers")
public class SuppliersController {

    private final SuppliersSpringDataRepository suppliersSpringDataRepository;

    @GetMapping
    public ResponseEntity<Object> findAllSuppliersWithCash() {
        return new ResponseEntity<>(
                Collections.singletonMap("result", suppliersSpringDataRepository.findAllCustom()),
                HttpStatus.OK
        );
    }
}