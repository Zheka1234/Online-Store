package com.boss.controller.springdata;


import com.boss.controller.request.roles.RoleCreatRequest;
import com.boss.domain.hibernate.HibernateRole;
import com.boss.repository.roles.RoleSpringDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/data/roles")
public class RoleController {

    private final RoleSpringDataRepository repository;

        @GetMapping
        public ResponseEntity<Object> findAllRolesWithCache() {

            return new ResponseEntity<>(
                    Collections.singletonMap("result", repository.findAllCustom()),
                    HttpStatus.OK
            );
        }
}
