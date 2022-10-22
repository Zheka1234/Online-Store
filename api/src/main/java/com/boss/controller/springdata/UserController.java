package com.boss.controller.springdata;


import com.boss.controller.request.user.UserCreateRequest;
import com.boss.domain.hibernate.HibernateRole;
import com.boss.domain.hibernate.HibernateUser;
import com.boss.repository.roles.RoleSpringDataRepository;
import com.boss.repository.user.UserSpringDataRepository;
import com.boss.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/rest/data/users")
public class UserController {

    private final UserSpringDataRepository repository;

    private final RoleSpringDataRepository roleSpringDataRepository;

    private final UserService userService;

    private final ConversionService converter;

    @GetMapping
    public ResponseEntity<Object> testEndpoint() {

        return new ResponseEntity<>(Collections.singletonMap("result",
                repository.findAll(PageRequest.of(0, 10))), HttpStatus.OK);
    }


    @PostMapping
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, timeout = 100,
            rollbackFor = Exception.class)
    @ResponseStatus(HttpStatus.CREATED)
    public HibernateUser savingUser(@RequestBody UserCreateRequest userCreateRequest) {

        HibernateUser hibernateUser = converter.convert(userCreateRequest, HibernateUser.class);

        HibernateUser createdUser = repository.save(setRoles(hibernateUser));

        Map<String, Object> model = new HashMap<>();
        model.put("user", repository.findById(createdUser.getIdUser()).get());

        return repository.save(hibernateUser);


    }

    private HibernateUser setRoles(HibernateUser user) {
        Set<HibernateRole> roles = user.getRoles();

        Set<HibernateRole> updatedRoles = new HashSet<>();

        if (!CollectionUtils.isEmpty(roles)) {
            updatedRoles.addAll(roles);
        }
        updatedRoles.add(roleSpringDataRepository.findHibernateRoleByIdRole(1l).get(0));
        updatedRoles.add(roleSpringDataRepository.findHibernateRoleByIdRole(2l).get(0));

        user.setRoles(updatedRoles);

        return user;
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUsersById(@PathVariable Long id) {

        userService.delete(id);

        Map<String, Object> model = new HashMap<>();
        model.put("id", id);
        return new ResponseEntity<>(model, HttpStatus.OK);
    }


}