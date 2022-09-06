package com.boss.controller;


import com.boss.controller.request.UserCreateRequest;
import com.boss.controller.request.UserSearchRequest;
import com.boss.domain.User;
import com.boss.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/rest/users")
public class UserRestController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<Object> findAllUsers(){

        return new ResponseEntity<>(Collections.singletonMap("result", userService.findAll()), HttpStatus.OK);

    }
    @GetMapping("/search")
    public ResponseEntity<Object> findAllUsersWithParams(@ModelAttribute UserSearchRequest userSearchRequest){
        int verifiedLimit = Integer.parseInt(userSearchRequest.getLimit());
        int verifiedOffset = Integer.parseInt(userSearchRequest.getOffset());

        List<User> users = userService.search(verifiedLimit, verifiedOffset);

        Map<String, Object> model = new HashMap<>();
        model.put("user", "zhenya");
        model.put("users", users);

        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> findUserById(@PathVariable String id) {

        //We have added id parsing and number format checking
        long userId = Long.parseLong(id);

        return new ResponseEntity<>(Collections.singletonMap("user", userService.findById(userId)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody UserCreateRequest createRequest) {

        User user = new User();
        user.setNameUsers(createRequest.getNameUsers());
        user.setSurnameUsers(createRequest.getSurnameUsers());
        user.setBuys(createRequest.getBuys());
        user.setCreationDate(new Timestamp(new Date().getTime()));
        user.setModificationDate(new Timestamp(new Date().getTime()));
        user.setIsDeleted(false);
        user.setLoginUser("sdfd");
        user.setPasswordUsers("dsffds");


        userService.create(user);

        List<User> users = userService.findAll();

        Map<String, Object> model = new HashMap<>();
        model.put("user", user.getNameUsers());
        model.put("users", users);

        return new ResponseEntity<>(model, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteUsers(@PathVariable String id){
        long userid = Long.parseLong(id);
        return new ResponseEntity<>(Collections.singletonMap("user", userService.delete(userid)), HttpStatus.OK);
    }


}
