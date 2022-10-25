package com.boss.controller.springdata;


import com.boss.controller.request.point.PointCreatRequest;
import com.boss.domain.hibernate.HibernatePoint;
import com.boss.repository.point.PointSpringDataRepository;
import com.boss.service.point.PointServiceImpl;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/data/point")
@Tag(name = "Point Controller")
public class DeliveryPointController {

    private final PointServiceImpl service;


    @GetMapping("/findAllPageable")
    @Parameter(
            in = ParameterIn.QUERY,
            description =
                    "Sorting criteria in the format: property(,asc|desc). "
                            + "Default sort order is ascending. "
                            + "Multiple sort criteria are supported.",
            name = "sort",
            array = @ArraySchema(schema = @Schema(type = "string")))
    public ResponseEntity<Object> findAllPageable(@ParameterObject Pageable pageable) {

        return new ResponseEntity<>(service.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/findById{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> findById(@PathVariable String id) {
        Integer pointId = 0;
        try {
            pointId = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid point id");
        }

        return new ResponseEntity<>(
                Collections.singletonMap("result", service.findById(Long.valueOf(pointId))), HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public ResponseEntity<Object> findAll() {

        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }



}