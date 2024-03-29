package com.boss.controller.springdata;


import com.boss.domain.hibernate.HibernatePhone;
import com.boss.service.phone.PhoneServiceImpl;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;


@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/data/phone")
@Tag(name = "Phone controller")
public class PhoneController {

    private final PhoneServiceImpl phoneService;


    @GetMapping
    @Parameter(
            in = ParameterIn.QUERY,
            description =
                    "Sorting criteria in the format: property(,asc|desc). "
                            + "Default sort order is ascending. "
                            + "Multiple sort criteria are supported.",
            name = "sort",
            array = @ArraySchema(schema = @Schema(type = "string")))
    public ResponseEntity<Object> findAllPageable(@ParameterObject Pageable pageable) {

        return new ResponseEntity<>(phoneService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> findById(@PathVariable String id) {
        Long phoneId;
        try {
            phoneId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid phone ID");
        }

        HibernatePhone phone = phoneService.findById(phoneId);
        return new ResponseEntity<>(
                Collections.singletonMap("result", phone), HttpStatus.OK);
    }

}
