package com.boss.controller;


import com.boss.controller.request.phone.PhoneChangeRequest;
import com.boss.controller.request.phone.PhoneCreatRequest;
import com.boss.controller.request.suppliers.SuppliersChangeRequest;
import com.boss.controller.request.suppliers.SuppliersCreat;
import com.boss.controller.request.user.UserChangeRequest;
import com.boss.domain.hibernate.HibernatePhone;
import com.boss.domain.hibernate.HibernateSuppliers;
import com.boss.domain.hibernate.HibernateUser;
import com.boss.service.phone.PhoneServiceImpl;
import com.boss.service.suppliers.SuppliersService;
import com.boss.service.user.UserImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
@Tag(name = "ADMIN/MODERATOR controller")
public class AdminController {

    private final ConversionService conversionService;

    private final SuppliersService service;

    private final UserImpl userService;


    private final PhoneServiceImpl phoneService;

    @PutMapping("/users/update")
    @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", required = true)
    @org.springframework.transaction.annotation.Transactional
    @ResponseStatus(HttpStatus.OK)
    @RequestBody(
            description = "This method allows update the user in DataBase.",
            required = true,
            content = @Content(schema = @Schema(implementation = UserChangeRequest.class)))
    public ResponseEntity<Object> updateUser(
            @Valid @org.springframework.web.bind.annotation.RequestBody
            UserChangeRequest userChangeRequest) {

        HibernateUser hibernateUser = conversionService.convert(userChangeRequest, HibernateUser.class);

        hibernateUser = userService.update(hibernateUser);

        Map<String, Object> model = new HashMap<>();
        model.put("user", userService.findById(hibernateUser.getIdUser()));

        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @GetMapping("/users/findAllPageable")
    @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", required = true)
    @Parameter(
            in = ParameterIn.QUERY,
            description =
                    "Sorting criteria in the format: property(,asc|desc). "
                            + "Default sort order is ascending. "
                            + "Multiple sort criteria are supported.",
            name = "sort",
            array = @ArraySchema(schema = @Schema(type = "string")))
    public ResponseEntity<Object> findAllPageable(@ParameterObject Pageable pageable) {

        return new ResponseEntity<>(userService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/users/findAll")
    @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", required = true)
    public ResponseEntity<Object> findAllUsers() {

        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/users/findById{id}")
    @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", required = true)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> findById(@PathVariable String id) {
        Long userId;
        try {
            userId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid user ID");
        }
        return new ResponseEntity<>(
                Collections.singletonMap("error", userService.findById(userId)), HttpStatus.OK);
    }


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

    @PutMapping("/suppliers/update")
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

    @DeleteMapping("/suppliers/delete{id}")
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

    @PostMapping("/phone/create")
    @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", required = true)
    @org.springframework.transaction.annotation.Transactional
    @ResponseStatus(HttpStatus.CREATED)
    @RequestBody(
            description = "This method allows create a new phone in DataBase.",
            required = true,
            content = @Content(schema = @Schema(implementation = PhoneCreatRequest.class)))
    public ResponseEntity<Object> createPhone(
            @Valid @org.springframework.web.bind.annotation.RequestBody
            PhoneCreatRequest phoneCreatRequest) {

        HibernatePhone hibernatePhone = conversionService.convert(phoneCreatRequest, HibernatePhone.class);

        hibernatePhone = phoneService.create(hibernatePhone);

        Map<String, Object> model = new HashMap<>();
        model.put("phone", phoneService.findById(hibernatePhone.getIdPhone()));

        return new ResponseEntity<>(model, HttpStatus.CREATED);
    }

    @PutMapping("/phone/update")
    @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", required = true)
    @org.springframework.transaction.annotation.Transactional
    @ResponseStatus(HttpStatus.OK)
    @RequestBody(
            description = "This method allows update the phone in DataBase.",
            required = true,
            content = @Content(schema = @Schema(implementation = PhoneChangeRequest.class)))
    public ResponseEntity<Object> updatePhone(
            @Valid @org.springframework.web.bind.annotation.RequestBody
            PhoneChangeRequest phoneChangeRequest) {

        HibernatePhone hibernatePhone = conversionService.convert(phoneChangeRequest, HibernatePhone.class);

        hibernatePhone = phoneService.update(hibernatePhone);

        Map<String, Object> model = new HashMap<>();
        model.put("phone", phoneService.findById(hibernatePhone.getIdPhone()));

        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @DeleteMapping("/phone/delete{id}")
    @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", required = true)
    @org.springframework.transaction.annotation.Transactional
    @Operation(description = "This method allows deactivate the phone in DataBase")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> deletePhone(@PathVariable String id) {

        Long phoneId = 0L;
        try {
            phoneId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid phone ID");
        }
        HibernatePhone hibernatePhone = phoneService.delete(phoneId);

        Map<String, Object> model = new HashMap<>();
        model.put("phone", phoneService.findById(hibernatePhone.getIdPhone()));

        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @GetMapping("/phone/findAll")
    @Parameter(in = ParameterIn.HEADER, name = "X-Auth-Token", required = true)
    public ResponseEntity<Object> findAllCars() {

        return new ResponseEntity<>(phoneService.findAll(), HttpStatus.OK);
    }



}
