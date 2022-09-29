package com.boss.controller.springdata;


import com.boss.controller.request.UserCreateRequest;
import com.boss.domain.hibernate.HibernateUser;import com.boss.repository.springdata.RoleSpringDataRepository;
import com.boss.repository.springdata.UserSpringDataRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.data.domain.PageRequest;
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

@RequiredArgsConstructor
@RestController
@RequestMapping("/rest/data/users")
public class UserController {

    private final UserSpringDataRepository repository;

    private final RoleSpringDataRepository roleRepository;

    @GetMapping
    public ResponseEntity<Object> testEndpoint() {

        return new ResponseEntity<>(Collections.singletonMap("result",
                repository.findAll(PageRequest.of(0, 10))), HttpStatus.OK);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Object> createUser(@RequestBody UserCreateRequest createRequest) {

        HibernateUser user = new HibernateUser();
        user.setNameUsers(createRequest.getNameUsers());
        user.setSurnameUsers(createRequest.getSurnameUsers());
        user.setCreationDate(new Timestamp(new Date().getTime()));
        user.setModificationDate(new Timestamp(new Date().getTime()));
        user.setIsDeleted(false);
        user.setBuys(createRequest.getBuys());

        user.setLoginUser(RandomStringUtils.randomAlphabetic(10));
        user.setPasswordUsers(RandomStringUtils.randomAlphabetic(10));

        HibernateUser createdUser = repository.save(user);

        repository.createRoleRow(createdUser.getIdUser(), roleRepository.findHibernateRoleByIdRole(1L).get(0).getIdRole());

        Map<String, Object> model = new HashMap<>();
        model.put("user", createdUser);

        return new ResponseEntity<>(model, HttpStatus.CREATED);
    }


}
