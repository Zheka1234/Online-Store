package com.boss.controller;


import com.boss.controller.request.suppliers.SuppliersChangeRequest;
import com.boss.controller.request.suppliers.SuppliersCreat;
import com.boss.domain.hibernate.HibernateSuppliers;
import com.boss.service.suppliers.SuppliersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
@Tag(name = "ADMIN/MODERATOR controller")
public class AdminController {

    public final ConversionService conversionService;

    private final SuppliersService service;


    @PostMapping("/suppliers/create")
    @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", required = true)
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> createSuppliers(@Valid
                                              @org.springframework.web.bind.annotation.RequestBody SuppliersCreat suppliersCreat) {

        HibernateSuppliers hibernateSuppliers = conversionService.convert(suppliersCreat, HibernateSuppliers.class);

        hibernateSuppliers = service.create(hibernateSuppliers);

        Map<String, Object> model = new HashMap<>();
        model.put("Suppliers", service.findById(hibernateSuppliers.getIdSuppliers()));

        return new ResponseEntity<>(model, HttpStatus.CREATED);
    }

    @PostMapping("/suppliers/update")
    @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", required = true)
    @Transactional
    @ResponseStatus(HttpStatus.OK)

    public ResponseEntity<Object> updateSuppliers(@Valid
                                              @org.springframework.web.bind.annotation.RequestBody SuppliersChangeRequest suppliersChangeRequest) {

        HibernateSuppliers hibernateSuppliers = conversionService.convert(suppliersChangeRequest, HibernateSuppliers.class);

        hibernateSuppliers = service.update(hibernateSuppliers);

        Map<String, Object> model = new HashMap<>();
        model.put("Suppliers", service.findById(hibernateSuppliers.getIdSuppliers()));

        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @PostMapping("/suppliers/delete{id}")
    @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", required = true)
    @Transactional
    @Operation(description = "This method allows deactivate the brand type in DataBase")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> deleteSuppliers(@PathVariable String id) {

        Integer suppliersId= 0;
        try {
            suppliersId = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid suppliers id");
        }
        HibernateSuppliers hibernateSuppliers = service.delete(Long.valueOf(suppliersId));

        Map<String, Object> model = new HashMap<>();
        model.put("Suppliers", service.findById(hibernateSuppliers.getIdSuppliers()));

        return new ResponseEntity<>(model, HttpStatus.OK);
    }



}
