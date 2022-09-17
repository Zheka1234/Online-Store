package com.boss.controller;

import com.boss.controller.request.UserCreateRequest;
import com.boss.controller.request.UserSearchRequest;
import com.boss.domain.User;
import com.boss.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserMVCController {

    private final UserService userService;

    @GetMapping
    public ModelAndView findAllUsers() {
        List<User> users = userService.findAll();

        ModelAndView model = new ModelAndView();
        model.addObject("user", "Slava");
        model.addObject("users", users);

        model.setViewName("users");

        return model;
    }

    @GetMapping("/search")
    public ModelAndView findAllUsersWithParams(@ModelAttribute UserSearchRequest userSearchRequest) {

        int verifiedLimit = Integer.parseInt(userSearchRequest.getLimit());
        int verifiedOffset = Integer.parseInt(userSearchRequest.getOffset());

        List<User> users = userService.search(verifiedLimit, verifiedOffset);

        ModelAndView model = new ModelAndView();
        model.addObject("user", "Slava");
        model.addObject("users", users);

        model.setViewName("users");

        return model;
    }

    @GetMapping("/{id}")
    public ModelAndView findUserById(@PathVariable String id) {

        //We have added id parsing and number format checking
        long userId = Long.parseLong(id);
        User user = userService.findById(userId);

        ModelAndView model = new ModelAndView();
        model.addObject("userName", user.getNameUsers());
        model.addObject("user", user);

        model.setViewName("user");

        return model;
    }

    @PostMapping
    //Jackson
    public ModelAndView createUser(@RequestBody UserCreateRequest createRequest) {

        User user = new User();
        user.setNameUsers(createRequest.getNameUsers());
        user.setSurnameUsers(createRequest.getSurnameUsers());
        user.setBuys(createRequest.getBuys());
        user.setCreationDate(new Timestamp(new Date().getTime()));
        user.setModificationDate(new Timestamp(new Date().getTime()));
        user.setIsDeleted(false);


        userService.create(user);

        List<User> users = userService.findAll();

        ModelAndView model = new ModelAndView();
        model.addObject("user", "Zhenya");
        model.addObject("users", users);

        model.setViewName("users");

        return model;
    }
}
